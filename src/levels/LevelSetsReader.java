package levels;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelSetsReader {
    /**
     * this method gets a reader object and returns a list of
     * LevelSet objects.
     * @param reader a reader to read from.
     * @return a list of LevelInformation objects.
     */
    public static List<LevelSet> fromReader(Reader reader) {
        List<LevelSet> returnVal = new ArrayList<LevelSet>();
        LineNumberReader lineNumberReader = new LineNumberReader(reader);
        String line;
        List<LevelInformation> levelSet;
        try {
            String key = null;
            String message = null;
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 != 0) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        key = parts[0];
                        message = parts[1];
                    }
                }
                else {
                    InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                    LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
                    levelSet = levelSpecificationReader.fromReader(new InputStreamReader(inputStream));
                    returnVal.add(new LevelSet(key, message, levelSet));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnVal;
    }
}
