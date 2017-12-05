//
//  DateRangeViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class DateRangeViewController: UIViewController {
    
    var startDate:UITextField!
    var endDate:UITextField!
    var doneBtn:UIButton!
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    override func viewDidLoad() {
        
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        self.view.backgroundColor = UIColor.white
        
        startDate = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, width: screenWidth, height: screenHeight / 20))
        startDate.text = "Start"
        self.view.addSubview(startDate)
        endDate = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, width: screenWidth, height: screenHeight / 20))
        endDate.text = "End"
        self.view.addSubview(endDate)
        doneBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, title: "Done", vc: self)
        doneBtn.addTarget(self, action: #selector(onDone), for: .touchUpInside)
    }
    
    @objc
    func onDone() {
        if (Model.viewToGoTo == "Map") {
            SightingManager.startMapDate = AppDate(data: startDate.text!)
            SightingManager.endMapDate = AppDate(data: endDate.text!)
            self.present(MapViewController(), animated: true, completion: nil)
        }
        if (Model.viewToGoTo == "Graph") {
            SightingManager.startGraphDate = AppDate(data: startDate.text!)
            SightingManager.endGraphDate = AppDate(data: endDate.text!)
            self.present(GraphViewController(), animated: true, completion: nil)
        }
    }
}
