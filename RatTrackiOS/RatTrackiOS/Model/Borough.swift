//
//  Borough.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

//NOTE: this will require a different implementation from how we used this in Java.  I did not know how to convert the java code into swift.

enum BoroughEnum {
    case BRONX
    case BROOKLYN
    case MANHATTAN
    case QUEENS
    case STATEN_ISLAND
    case UNKNOWN
    case NA
    case NULL
}
//An object that holds the enum
class Borough {
    var bor : BoroughEnum
    init(name: String) {
        if (name == "Bronx") {
            bor = BoroughEnum.BRONX
        } else if (name == "Brooklyn") {
            bor = BoroughEnum.BROOKLYN
        } else if (name == "Manhattan") {
            bor = BoroughEnum.MANHATTAN
        } else if (name == "Queens") {
            bor = BoroughEnum.QUEENS
        } else if (name == "Staten Island") {
            bor = BoroughEnum.STATEN_ISLAND
        } else if (name == "Unknown") {
            bor = BoroughEnum.UNKNOWN
        } else if (name == "N/A") {
            bor = BoroughEnum.NA
        } else {
            bor = BoroughEnum.NULL
        }
    }
    
    func get() -> BoroughEnum {
        return bor
    }
}
