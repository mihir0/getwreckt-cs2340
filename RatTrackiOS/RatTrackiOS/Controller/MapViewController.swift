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
    override func viewDidLoad() {
        // Create a GMSCameraPosition that tells the map to display the
        // coordinate -33.86,151.20 at zoom level 6.
        let camera = GMSCameraPosition.camera(withLatitude: -33.86, longitude: 151.20, zoom: 6.0)
        let mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        view = mapView
        
        // Creates a marker in the center of the map.
        let marker = GMSMarker()
        marker.position = CLLocationCoordinate2D(latitude: -33.86, longitude: 151.20)
        marker.title = "Sydney"
        marker.snippet = "Australia"
        marker.map = mapView
    }
}
