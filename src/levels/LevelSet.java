package levels;

import java.util.List;

public class LevelSet {
    private String key;
    private String message;
    private List<LevelInformation> levels;

    /**
     * Constructor.
     * @param key symbol of set.
     * @param message name of set.
     * @param levels list of levels in set.
     */
    public LevelSet(String key, String message, List<LevelInformation> levels) {
        this.key = key;
        this.message = message;
        this.levels = levels;
    }

    /**
     * Return name of set.
     * @return name of set.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Return symbol of set.
     * @return symbol of set.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Return list of levels in set.
     * @return levels in set
     */
    public List<LevelInformation> getLevelsForSet() {
        return this.levels;
    }

}
