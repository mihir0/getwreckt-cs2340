package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 10/20/2017.
 */

public enum LocationType {
    FAM_1_2__DWELLING ("1-2 Family Dwelling"),
    FAM_1_2_MIXED ("1-2 Family Mixed Use Building"),
    FAM_3_APT ("3+ Family Apt. Building"),
    FAM_3_MIXED ("3+ Family Mixed Use Building"),
    CATCH_BASIN_SEWER ("Catch Basin/Sewer"),
    COMMERCIAL_BLDNG ("Commercial Building"),
    CONSTRUCTION_SITE ("Construction Site"),
    NURSERY ("Day Care/Nursery"),
    GOV_BLDNG ("Government Building"),
    HOSPITAL ("Hospital"),
    OFFICE_BLDNG ("Office Building"),
    OTHER ("Other (Explain Below)"),
    PARKING_LOT ("Parking Lot/Garage"),
    PUBLIC_GARDEN ("Public Garden"),
    PUBLIC_STAIRS ("Public Stairs"),
    SCHOOL ("School/Pre-School"),
    SRO ("Single Room Occupancy (SRO)"),
    SUMMER_CAMP ("Summer Camp"),
    VACANT_BLDNG ("Vacant Building"),
    VACANT_LOT ("Vacant Lot"),
    UNKNOWN ("Unknown"),
    NULL ("");

    private String name;

    /**
     * Parametrized constructor of location type
     * @param name name of location
     */
    LocationType(String name) {
        this.name = name;
    }

    /**
     * Getter method for name
     * @return name of location
     */
    public String getName() {
        return name;
    }

    /**
     * Selects location type using name of location
     * @param name name of location
     * @return location type
     */
    public static LocationType get(String name) {
        for (LocationType locationType : values()) {
            if (locationType.getName().equals(name)) {
                return locationType;
            }
        }

        return NULL;
    }
}
