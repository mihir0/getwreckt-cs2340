//
//  SightingDetailViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class SightingDetailViewController: UIViewController {
    
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    var uniqueKeyField:UITextView!
    var dateField:UITextView!
    var addrField:UITextView!
    var cityField:UITextView!
    var zipField:UITextView!
    var boroughField:UITextView!
    var coordinateField:UITextView!
    var doneBtn:UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        uniqueKeyField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 0, vc: self)
        dateField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 1, vc: self)
        addrField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, vc: self)
        cityField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 3, vc: self)
        zipField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, vc: self)
        boroughField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 5, vc: self)
        coordinateField = Model.addTextView(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, vc: self)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 7, title: "Done", s: #selector(btnDone), vc: self)
        
        uniqueKeyField.text = Model.sighting!.getUniqueKey()
        dateField.text = "Date: \(Model.sighting!.getDate())"
        if Model.sighting!.getLocation().getAddress() == "" {
            addrField.text = "N/A"
        } else {
            addrField.text = Model.sighting!.getLocation().getAddress()
        }
        cityField.text = Model.sighting!.getLocation().getCity()
        zipField.text = Model.sighting!.getLocation().getZip()
        boroughField.text = Model.sighting!.getLocation().getBorough().getName()
        coordinateField.text = "Coordinates: (\(String(Model.sighting!.getLocation().getLatitude())), \(String(Model.sighting!.getLocation().getLongitude()))"
    }
    
    @objc
    func btnDone() {
        self.present(ListViewController(), animated: true, completion: nil)
    }
}
