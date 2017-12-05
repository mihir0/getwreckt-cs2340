//
//  ListViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class ListViewController: UITableViewController {
    
    var scrollView:UIScrollView!
    var table:UITableView!
    var data:[String]!
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    var ref:DatabaseReference!
    
    override func viewDidLoad() {
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        ref = Database.database().reference()
        
        data = []
        
        scrollView = UIScrollView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenHeight))
        self.view.addSubview(scrollView)
        
        table = UITableView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenHeight))
        table.register(UITableViewCell.self, forCellReuseIdentifier: "MyCell")
        table.dataSource = self
        table.delegate = self
        scrollView.addSubview(table)

        
        ref.child("ratsightings").observe(.value, with: {(snapshot) in
            self.data = []
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
                SightingManager.addSighting(sighting: rats)
                self.data.append(uniqueKey)
            }
            self.table.reloadData()
        })
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        Model.sighting = SightingManager.ratSightings[indexPath.row]
        self.present(SightingDetailViewController(), animated: true, completion: nil)
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(frame: CGRect(x: 0, y: 0, width: 50, height: 50))
        cell.textLabel!.text = data[indexPath.row]
        return cell
    }
}
