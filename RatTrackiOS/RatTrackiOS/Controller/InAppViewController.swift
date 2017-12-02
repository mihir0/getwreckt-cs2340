//
//  InAppViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/1/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class InAppViewController: UIViewController {
    
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    var txt:UITextView!
    var ref:DatabaseReference!
    var auth:Auth!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.auth = Auth.auth()
        self.ref = Database.database().reference()
        
        self.ref.child("users").observe(.value, with: {(snapshot) in
            let value = snapshot.childSnapshot(forPath: self.auth.currentUser!.uid).value as! AppUser
        })
    }
    
    func updateUI(fbu:User) {
        
    }
    
}
