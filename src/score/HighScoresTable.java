package score;

import java.io.*;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HighScoresTable implements Serializable{
    private List<ScoreInfo> highScores;
    private int size;
    private static final long serialVersionUID = 1L;

    // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<>();
        this.size = size;
    }

    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable emptyTable = new HighScoresTable(5);
        try {
            if (!filename.exists()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            return new HighScoresTable(5);
        }
        return emptyTable;
    }

    // Add a high-score.
    public void add(ScoreInfo score) {
        if (getRank(score.getScore()) > this.size) {
            return;
        }
        this.highScores.add(getRank(score.getScore()) - 1, score);
        if (getHighScores().size() > this.size) {
            this.highScores.remove(this.highScores.size() - 1);
        }
    }

    // Return table size.
    public int getSize() {
        return this.size;
    }

    // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.highScores.size(); i++) {
            if (score > this.highScores.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }

    // Clears the table
    public void clear() {
        this.highScores.clear();
    }

    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        HighScoresTable highScoresTable = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename.getName());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            highScoresTable = (HighScoresTable) in.readObject();
            in.close();
            fileIn.close();
            this.highScores = highScoresTable.highScores;
            this.size = highScoresTable.size;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("HighScoresTable class not found");
            c.printStackTrace();
            return;
        }

    }

    // Save table data to the specified file.
    public void save(File filename) throws IOException {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(filename.getName());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}



