//
//  RegisterViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/1/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import UIKit
import Firebase

class RegisterViewController: UIViewController {
    
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    var fullNameField:UITextField!
    var emailField:UITextField!
    var passField:UITextField!
    
    var adminCheck:UITextField!
    
    var regBtn:UIButton!
    
    var ref:DatabaseReference!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        ref = Database.database().reference()
        
        fullNameField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(fullNameField)
        emailField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(emailField)
        passField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(passField)
        adminCheck = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(adminCheck)
        Model.addButton(y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 8, title: "Register", s: #selector(onRegPressed), vc: self)
    }
    
    @objc
    func onRegPressed() {
        let fullName:String = fullNameField.text!
        let email:String = emailField.text!
        let pass:String = passField.text!
        let userType:String = adminCheck.text == nil ? "User" : "Admin"
        let u:AppUser = AppUser(fullName: fullName, userName: email, userType: userType)
        createUser(u: u, pass: pass)
    }
    
    func createUser(u:AppUser, pass:String) {
        Auth.auth().createUser(withEmail: u.getUserName(), password: pass, completion: {(user, error) in
            if (error == nil) {
                Model.setCurrentUser(user: u)
                var newU:[String:Any] = [:]
                newU["fullName"] = u.getFullName()
                newU["userName"] = u.getUserName()
                newU["userType"] = u.getUserType()
                newU["sightings"] = u.getSightings()
                newU["signedIn"] = u.getSignedIn()
                self.ref.child("users").child(Auth.auth().currentUser!.uid).setValue(newU)
                self.present(InAppViewController(), animated: true, completion: nil)
            }
        })
    }
    
    func isValidEmailPass(email:String, pass:String) -> (Bool) {
        return !(email == "") && !(pass == "")
    }
    
    func onCancelPressed() {
        self.navigationController!.pushViewController(ViewController(), animated: true)
    }
}