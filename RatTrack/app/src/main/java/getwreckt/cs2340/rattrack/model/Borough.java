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
    NA ("N/A");

    private String name;

    Borough(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
