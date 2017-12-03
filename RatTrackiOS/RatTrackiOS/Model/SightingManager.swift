//
//  SightingManager.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
class SightingManager {
    static var ratSightings: [RatSighting] = []
    static var startMapDate: Date = Date(data: "01/01/1000 12:00:00 AM")
    static var endMapDate: Date = Date(data: "12/31/3999 11:59:00 PM")
    
    static var startGraphDate: Date = Date(data: "01/01/1000 12:00:00 AM")
    static var endGraphDate: Date = Date(data: "12/31/3999 11:59:00 PM")

    static func addSighting(sighting: RatSighting) {
        ratSightings.append(sighting)
    }
}
