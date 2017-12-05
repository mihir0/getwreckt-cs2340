//
//  MapViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase
import GoogleMaps
//API key: AIzaSyB4JRU9kwLkYOA6AjXDgZ59lEZWBhv4dzY
class MapViewController: UIViewController {
    
    var ref:DatabaseReference!
    
    override func viewDidLoad() {
        // Create a GMSCameraPosition that tells the map to display the
        // coordinate -33.86,151.20 at zoom level 6.
        let camera = GMSCameraPosition.camera(withLatitude: -33.86, longitude: 151.20, zoom: 6.0)
        let mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        view = mapView
        
        ref = Database.database().reference()
        
        ref.child("ratsightings").observe(.value, with: {(snapshot) in
            for rs in snapshot.children {
                
                let snaprs = rs as! DataSnapshot
                let dictrs = snaprs.value as! [String:Any]
                let uniqueKey:String = dictrs["uniqueKey"] as! String
                var datedict:[String:Any] = dictrs["date"] as! [String:Any]
                var hour:Int = datedict["hour"]! as! Int
                let dateStr:String = "\(datedict["month"]! as! Int)/\(datedict["date"]! as! Int)/\(datedict["year"]! as! Int) \(hour == 0 ? 12 : hour > 12 ? hour - 12 : hour):\(datedict["minute"]! as! Int):\(datedict["second"]! as! Int) \(datedict["isPM"]! as! Bool ? "PM" : "AM")"
                var date:AppDate? = AppDate(data: dateStr)
                var locdict:[String:Any] = dictrs["location"] as! [String:Any]
                let location:Location = Location(typeLocation: LocationType.init(name: locdict["typeLocation"]! as! String), address: locdict["address"] as! String, city: locdict["city"] as! String, zip: locdict["zip"] as! String, borough: Borough.init(name: locdict["borough"]! as! String), latitude: locdict["latitude"] as! String, longitude: locdict["longitude"] as! String)
                let u:AppUser? = dictrs["owner"] as? AppUser
                let flagged:Bool = dictrs["flagged"] as! Bool
                var rats:RatSighting = RatSighting("", "", "", "", "", "", "", "", "")
                rats.setDate(date: date)
                rats.setOwner(owner: u)
                rats.setFlagged(flagged: flagged)
                rats.setLocation(location: location)
                rats.setUniqueKey(uniqueKey: uniqueKey)
                
                if (rats.getDate()! > SightingManager.startMapDate || rats.getDate()! == SightingManager.startMapDate) &&
                    (rats.getDate()! < SightingManager.endMapDate || rats.getDate()! == SightingManager.endMapDate) {
                    let marker = GMSMarker()
                    marker.title = rats.toString()
                    marker.position = CLLocationCoordinate2D(latitude: Double(rats.getLocation()!.getLatitude())!, longitude: Double(rats.getLocation()!.getLongitude())!)
                }
            }
        })
        
        // Creates a marker in the center of the map.
        let marker = GMSMarker()
        marker.position = CLLocationCoordinate2D(latitude: -33.86, longitude: 151.20)
        marker.title = "Sydney"
        marker.snippet = "Australia"
        marker.map = mapView
    }
}
