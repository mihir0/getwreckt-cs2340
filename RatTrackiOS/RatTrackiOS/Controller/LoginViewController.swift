//
//  LoginViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/1/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import UIKit
import Firebase

class LoginViewController: UIViewController {
    
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!
    
    var emailField:UITextField!
    var passField:UITextField!
    var loginBtn:UIButton!
    var cancelBtn:UIButton!
    
    var ref:DatabaseReference!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        FirebaseApp.configure()
        
        ref = Database.database().reference()
        
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        emailField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(emailField)
        passField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(passField)
        loginBtn = UIButton(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 10, width: screenWidth, height: screenHeight / 20))
        loginBtn.addTarget(self, action: Selector(("onLoginPressed")), for: .touchUpInside)
        self.view.addSubview(loginBtn)
        cancelBtn = UIButton(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 3, width: screenWidth, height: screenHeight / 20))
        cancelBtn.addTarget(self, action: Selector(("onCancelPressed")), for: .touchUpInside)
        self.view.addSubview(cancelBtn)
    }
    
    func onLoginPressed() {
        Auth.auth().signIn(withEmail: emailField.text!, password: passField.text!, completion: { (user, error) in
            if (error == nil) {
                var map:[String:Any] = [:]
                map["signedIn"] = true
                self.ref.child("users").child(Auth.auth().currentUser!.uid).updateChildValues(map)
                self.navigationController!.pushViewController(InAppViewController(), animated: true)
            }
        })
    }
    
    func onCancelPressed() {
        self.navigationController!.pushViewController(ViewController(), animated: true)
    }
}
