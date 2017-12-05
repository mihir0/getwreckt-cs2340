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
    var listBtn:UIButton!
    var mapBtn:UIButton!
    var graphBtn:UIButton!
    var makeSightingBtn:UIButton!
    
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
        
        listBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 0, title: "List", vc: self)
        listBtn.addTarget(self, action: #selector(btnList), for: .touchUpInside)
        mapBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, title: "Map", vc: self)
        mapBtn.addTarget(self, action: #selector(btnMap), for: .touchUpInside)
        graphBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, title: "Graph", vc: self)
        graphBtn.addTarget(self, action: #selector(btnGraph), for: .touchUpInside)
        makeSightingBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, title: "Make Sighting", vc: self)
        makeSightingBtn.addTarget(self, action: #selector(btnMakeSighting), for: .touchUpInside)
        logoutBtn = Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 8, title: "Logout", vc: self)
        logoutBtn.addTarget(self, action: #selector(btnLogout), for: .touchUpInside)
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
