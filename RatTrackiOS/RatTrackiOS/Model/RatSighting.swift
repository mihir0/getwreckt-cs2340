//
//  RatSighting.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
class RatSighting {
    var uniqueKey: String
    var date: Date
    var location: Location
    var owner: AppUser?
    var isFlagged: Bool
    
    convenience init(date: String, typeLocation: String, zip: String, address: String, city: String, borough: String, latitude: String, longitude: String) {
        self.init("", date, typeLocation, zip, address, city, borough, latitude, longitude)
        setUniqueKey(uniqueKey: generateUniqueKey())
    }
    
    init(_ uniqueKey: String, _ date: String, _ typeLocation: String, _ zip: String, _ address: String, _ city: String, _ borough: String, _ latitude: String, _ longitude: String) {
        self.uniqueKey = uniqueKey
        self.date = Date(data: date)
        self.location = Location(typeLocation: LocationType(name: typeLocation), address: address, city: city, zip: zip, borough: Borough(name: borough), latitude: latitude, longitude: longitude)
        self.owner = Model.getCurrentUser()
        self.isFlagged = false // is this variable even doing anything?
    }
    
    func getUniqueKey() -> String {
        return uniqueKey
    }
    
    func setUniqueKey(uniqueKey: String) {
        self.uniqueKey = uniqueKey
    }
    
    func getDate() -> Date {
        return self.date
    }
    
    func setDate(date: Date) {
        self.date = date
    }
    
    func getOwner() -> AppUser? {
        return owner
    }
    
    func setOwner(owner: AppUser?) {
        self.owner = owner
    }
    
    func getLocation() -> Location {
        return location
    }
    
    func setLocation(location: Location) {
        self.location = location
    }
    
    func flagged() -> Bool {
        return isFlagged
    }
    
    func setFlagged(flagged: Bool) {
        isFlagged = flagged
    }
    
    func generateUniqueKey() -> String {
        self.owner = Model.getCurrentUser()
        if self.owner != nil {
            self.owner!.sightingMade()
            let userName = self.owner!.getUserName()
            let user = userName.split(separator: ".")[0] + userName.split(separator: ".")[1].prefix(1)
            return String(user) + date.toString() + String(owner!.getSightings())
        }
        return ""
    }
    
    func toString() -> String {
        return date.toString() + " " + uniqueKey
    }
}
