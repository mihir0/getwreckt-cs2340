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

class ListViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
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

        scrollView = UIScrollView(frame: CGRect(x: 0, y: 0, width: screenWidth, height: screenHeight))
        self.view.addSubview(scrollView)
        
        ref.child("ratsightings").observe(.value, with: {(snapshot) in
            for rs in snapshot.children {
                var uniqueKey:String = rs as! String
                if uniqueKey != nil {
                    self.data.append(uniqueKey)
                }
            }
        })
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MyCell", for: indexPath as IndexPath)
        cell.textLabel?.text = data[indexPath.row]
        return cell
    }
}
