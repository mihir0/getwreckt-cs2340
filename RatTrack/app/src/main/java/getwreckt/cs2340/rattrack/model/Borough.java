package getwreckt.cs2340.rattrack.model;

/**
 * Borough enum for all 5 NYC boroughs and options for unknown and N/A
 */

public enum Borough {
    BRONX ("Bronx"),
    BROOKLYN ("Brooklyn"),
    MANHATTAN ("Manhattan"),
    QUEENS ("Queens"),
    STATEN_ISLAND ("Staten Island"),
    UNKNOWN ("Unknown"),
    NA ("N/A"),
    NULL ("");

    private final String name;

    Borough(String name) {
        this.name = name;
    }

    /**
     * getter for name of borough enum
     * @return String version of Borough name
     */
    public String getName() {
        return this.name;
    }

    /**
     * searches for borough matching name of param
     * @param name of borough to find
     * @return borough with matching name or null if match was not found
     */
    public static Borough get(String name) {
        for (Borough borough : values()) {
            if (borough.getName().toUpperCase().equals(name.toUpperCase())) {
                return borough;
            }
        }

        return NULL;
    }
}
