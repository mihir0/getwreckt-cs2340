//
//  LocationType.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
enum LocationTypeEnum {
    case FAM_1_2__DWELLING// ("1-2 Family Dwelling"),
    case FAM_1_2_MIXED// ("1-2 Family Mixed Use Building"),
    case FAM_3_APT// ("3+ Family Apt. Building"),
    case FAM_3_MIXED// ("3+ Family Mixed Use Building"),
    case CATCH_BASIN_SEWER// ("Catch Basin/Sewer"),
    case COMMERCIAL_BLDNG// ("Commercial Building"),
    case CONSTRUCTION_SITE// ("Construction Site"),
    case NURSERY// ("Day Care/Nursery"),
    case GOV_BLDNG// ("Government Building"),
    case HOSPITAL// ("Hospital"),
    case OFFICE_BLDNG// ("Office Building"),
    case OTHER// ("Other (Explain Below)"),
    case PARKING_LOT// ("Parking Lot/Garage"),
    case PUBLIC_GARDEN// ("Public Garden"),
    case PUBLIC_STAIRS// ("Public Stairs"),
    case SCHOOL// ("School/Pre-School"),
    case SRO// ("Single Room Occupancy (SRO)"),
    case SUMMER_CAMP// ("Summer Camp"),
    case VACANT_BLDNG// ("Vacant Building"),
    case VACANT_LOT// ("Vacant Lot"),
    case UNKNOWN// ("Unknown"),
    case NULL// ("");
}

class LocationType {
    var loc: LocationTypeEnum
    init(name: String) {
        switch name {
        case "1-2 Family Dwelling":
            loc = LocationTypeEnum.FAM_1_2__DWELLING
        case "1-2 Family Mixed Use Building":
            loc = LocationTypeEnum.FAM_1_2_MIXED
        case "3+ Family Apt. Building":
            loc = LocationTypeEnum.FAM_3_APT
        case "3+ Family Mixed Use Building":
            loc = LocationTypeEnum.FAM_3_MIXED
        case "Catch Basin/Sewer":
            loc = LocationTypeEnum.CATCH_BASIN_SEWER
        case "Commercial Building":
            loc = LocationTypeEnum.COMMERCIAL_BLDNG
        case "Construction Site":
            loc = LocationTypeEnum.CONSTRUCTION_SITE
        case "Day Care/Nursery":
            loc = LocationTypeEnum.NURSERY
        case "Government Building":
            loc = LocationTypeEnum.GOV_BLDNG
        case "Hospital":
            loc = LocationTypeEnum.HOSPITAL
        case "Office Building":
            loc = LocationTypeEnum.OFFICE_BLDNG
        case "Other (Explain Below)":
            loc = LocationTypeEnum.OTHER
        case "Parking Lot/Garage":
            loc = LocationTypeEnum.PARKING_LOT
        case "Public Garden":
            loc = LocationTypeEnum.PUBLIC_GARDEN
        case "Public Stairs":
            loc = LocationTypeEnum.PUBLIC_STAIRS
        case "School/Pre-School":
            loc = LocationTypeEnum.SCHOOL
        case "Single Room Occupancy (SRO)":
            loc = LocationTypeEnum.SRO
        case "Summer Camp":
            loc = LocationTypeEnum.SUMMER_CAMP
        case "Vacant Building":
            loc = LocationTypeEnum.VACANT_BLDNG
        case "Vacant Lot":
            loc = LocationTypeEnum.VACANT_LOT
        case "Unknown":
            loc = LocationTypeEnum.UNKNOWN
        default:
            loc = LocationTypeEnum.NULL
        }
    }
    
    func get() -> LocationTypeEnum {
        return loc
    }
}
