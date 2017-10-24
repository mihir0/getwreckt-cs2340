package getwreckt.cs2340.rattrack.model;

/**
 * Created by maya v on 10/20/2017.
 */

public enum LocationType {
    FAM_1_2__DWELLING ("1-2 Family Dwelling"),
    FAM_1_2_MIXED ("1-2 Family Mixed Use Building"),
    FAM_3_APT ("3+ Family Apt. Building"),
    FAM_3_MIXED ("3+ Family Mixed Use Building"),
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
    UNKNOWN ("Unknown");

    private String name;

    LocationType(String name) {
        this.name = name;
    }
}
