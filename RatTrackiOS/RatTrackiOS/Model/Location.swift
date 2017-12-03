//
//  Location.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

class Location {
    var zip: String
    var typeLocation: LocationType
    var address: String
    var city: String
    var borough: Borough
    var latitude: String
    var longitude: String
    
    init(typeLocation: LocationType, address: String, city: String, zip: String, borough: Borough, latitude: String, longitude: String) {
        self.zip = zip;
        self.typeLocation = typeLocation;
        self.address = address;
        self.city = city;
        self.borough = borough;
        self.latitude = latitude;
        self.longitude = longitude;
    }
    
    func CharSequence() -> String { //CharSequence return type (in java)
        return zip
    }
    
    func setZip(zip: String) {
        self.zip = zip
    }
    
    func getTypeLocation() -> LocationType {
        return typeLocation
    }
    
    func setTypeLocation(typeLocation: LocationType) {
        self.typeLocation = typeLocation
    }
    
    func getAddress() -> String { //CharSequence return type (in java)
        return address
    }
    
    func setAddress(address: String) {
        self.address = address
    }
    
    func getCity() -> String { //CharSequence return type (in java)
        return city
    }
    
    func setCity(city: String) {
        self.city = city
    }
    
    func getBorough() -> Borough {
        return borough
    }
    
    func setBorough(borough: Borough) {
        self.borough = borough
    }
    
    func getLatitude() -> String {
        return latitude
    }
    
    func setLatitude(latitude: String) {
        self.latitude = latitude
    }
    
    func getLongitude() -> String {
        return longitude
    }
    
    func setLongitude(longitude: String) {
        self.longitude = longitude
    }
    
    //parcelable stuff here (in original java class)
}
