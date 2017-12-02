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
        
        ref = Database.database().reference()
        
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        
        self.view.backgroundColor = UIColor.white
        
        emailField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5, width: screenWidth, height: screenHeight / 20))
        self.view.addSubview(emailField)
        emailField.text = "Email"
        passField = UITextField(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 2, width: screenWidth, height: screenHeight / 20))
        passField.text = "Password"
        self.view.addSubview(passField)
        loginBtn = UIButton(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 4, width: screenWidth, height: screenHeight / 20))
        loginBtn.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        loginBtn.setTitleColor(UIColor.black, for: .normal)
        loginBtn.setTitle("Login", for: .normal)
        loginBtn.addTarget(self, action: #selector(onLoginPressed), for: .touchUpInside)
        self.view.addSubview(loginBtn)
        cancelBtn = UIButton(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 5 + screenHeight / 20 * 6, width: screenWidth, height: screenHeight / 20))
        cancelBtn.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        cancelBtn.setTitleColor(UIColor.black, for: .normal)
        cancelBtn.setTitle("Cancel", for: .normal)
        cancelBtn.addTarget(self, action: #selector(onCancelPressed), for: .touchUpInside)
        self.view.addSubview(cancelBtn)
    }
    
    @objc
    func onLoginPressed() {
        if emailField.text != nil && passField.text != nil {
            Auth.auth().signIn(withEmail: emailField.text!, password: passField.text!, completion: { (user, error) in
                if (error == nil) {
                    var map:[String:Any] = [:]
                    map["signedIn"] = true
                    self.ref.child("users").child(Auth.auth().currentUser!.uid).updateChildValues(map)
                    self.present(InAppViewController(), animated: true, completion: nil)
                }
            })
        }
    }
    
    @objc
    func onCancelPressed() {
        self.present(ViewController(), animated: true, completion: nil)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
