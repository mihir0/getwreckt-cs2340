package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 10/20/2017.
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

    private String name;

    Borough(String name) {
        this.name = name;
    }

    /**
     * Getter method for name
     * @return name of an admin in string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter method for name
     * @param name name of an admin
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Borough in UpperCase
     * @param name name of borough
     * @return borough in UpperCase or null
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
