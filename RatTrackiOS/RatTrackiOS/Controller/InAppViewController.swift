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
    
    var logoutBtn:UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.auth = Auth.auth()
        self.ref = Database.database().reference()
        
        self.view.backgroundColor = UIColor.white
        
        self.ref.child("users").observe(.value, with: {(snapshot) in
            let valueGet:[String:Any] = snapshot.childSnapshot(forPath: self.auth.currentUser!.uid).value as! [String:Any]
            let u = AppUser(fullName: valueGet["fullName"] as! String, userName: valueGet["userName"] as! String, userType: valueGet["userType"] as! String)
            Model.setCurrentUser(user: u)
            self.updateUI(fbu: self.auth.currentUser)
        })
        
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 0, title: "List", s: #selector(btnList), vc: self)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, title: "Map", s: #selector(btnMap), vc: self)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, title: "Graph", s: #selector(btnGraph), vc: self)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, title: "Make Sighting", s: #selector(btnMakeSighting), vc: self)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 8, title: "Logout", s: #selector(btnLogout), vc: self)
    }
    
    func updateUI(fbu:User?) {
        if Model.getCurrentUser() == nil {
            logout()
        } else {
            txt.text = "Hello, \(Model.getCurrentUser()!.getFullName())!"
        }
    }
    
    @objc
    func btnMakeSighting() {
        self.present(MakeSightingViewController(), animated: true, completion: nil)
    }
    
    @objc
    func btnMap() {
        Model.viewToGoTo = "Map"
        self.present(DateRangeViewController(), animated: true, completion: nil)
    }
    
    @objc
    func btnList() {
        self.present(ListViewController(), animated: true, completion: nil)
    }
    
    @objc
    func btnGraph() {
        Model.viewToGoTo = "Graph"
        self.present(DateRangeViewController(), animated: true, completion: nil)
    }
    
    @objc
    func btnLogout() {
        logout()
    }
    
    func logout() {
        let map:[String:Any] = ["signedIn":false]
        ref.child("users").child(Auth.auth().currentUser!.uid).updateChildValues(map)
        do {
            try Auth.auth().signOut()
        } catch {
            return
        }
        Model.setCurrentUser(user: nil)
        self.present(ViewController(), animated: true, completion: nil)
    }
    
}
