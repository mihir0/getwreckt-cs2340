//
//  MakeSightingViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class MakeSightingViewController: UIViewController {
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    //TODO: add to firebase once sighting is created
    
    var dateField:UITextField!
    var timeField:UITextField!
    var addrField:UITextField!
    var cityField:UITextField!
    var zipField:UITextField!
    //TODO: need to make a borough Spinner
    //TODO: need to make a location type Spinner
    var coordinateTitle:UITextField!
    var latField:UITextField!
    var longField:UITextField!
    @objc var makeBtn:UIButton!

    var yPad:CGFloat = 20
    var xPad:CGFloat = 10
    var yRows:CGFloat = 11
    
    override func viewDidLoad() {
        super.viewDidLoad()
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        self.view.backgroundColor = UIColor.white

        dateField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 0, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(dateField)
        dateField.text = "Date: mm/dd/yy"
        
        timeField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 1, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(timeField)
        timeField.text = "Time: 12:00:00 AM"
        
        addrField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 2, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(addrField)
        addrField.text = "Address"
        
        cityField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 3, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(cityField)
        addrField.text = "City"
        
        zipField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 4, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(zipField)
        zipField.text = "Zipcode"
        
        //TODO: make spinner for borough
        //TODO: make spinner for location type
        
        latField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 7, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(latField)
        latField.text = "Latitude"
        
        longField = UITextField(frame: CGRect(x: xPad/2, y: (yPad + screenHeight) / yRows * 8, width: screenWidth - xPad, height: (screenHeight - yPad) / yRows))
        self.view.addSubview(longField)
        longField.text = "Longitude"
        
        makeBtn = Model.addButton(y: (yPad + screenHeight) / yRows * 9, title: "Make Sighting", vc: self)
        makeBtn.addTarget(self, action: #selector(setter: makeBtn), for: .touchUpInside)
    }
    
    func EmptySighting(date:String, address:String, city:String, zip:String, borough:String, typeLocation:String, lat:String, long:String) -> Bool {
        return (("" == date) || ("" == address) || ("" == city) || ("" == zip) || ("" == borough) || ("" == typeLocation) || ("" == lat) || ("" == long))
    }
    
    @objc
    func btnDone() {
        var date = dateField.text
        var time = timeField.text
        var address = addrField.text
        var city = cityField.text
        var zip = zipField.text
        var lat = latField.text
        var long = longField.text
        
        var borough = ""
        var typeLocation = ""
        //var borough = spinner.getSelectedItem().text
        //var typeLocation = spinner.getSelectedItem().text
        
        if (EmptySighting(date: date, address: address, city: city, zip: zip, borough: borough, typeLocation: typeLocation, lat: lat, long: long)) {
            dateField.text = "Must fill all fields with valid sighting details."
        } else {
            var sighting = RatSighting(date + " " + time, typeLocation, zip, address, city, borough, lat, long)
            SightingManager.addSighting(sighting: sighting)
            
        }
    }
}
