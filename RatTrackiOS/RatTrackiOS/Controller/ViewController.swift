//
//  ViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 11/9/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import UIKit
import Firebase

class ViewController: UIViewController {
    
    var screenHeight:CGFloat!
    var screenWidth:CGFloat!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        screenWidth = UIScreen.main.bounds.size.width
        screenHeight = UIScreen.main.bounds.size.height
        initLogin()
        initRegister()
        FirebaseApp.configure()
        
        if Auth.auth().currentUser != nil {
            self.present(InAppViewController(), animated: true, completion: nil)
        }
    }
    
    @objc
    func loginPressed() {
        self.present(LoginViewController(), animated: true, completion: nil)
    }
    
    func initLogin() {
        Model.addButton(y: screenHeight / 2 - screenHeight / 10, title: "Login", s: #selector(loginPressed), vc: self)
    }
    
    @objc
    func registerPressed() {
        self.present(RegisterViewController(), animated: true, completion: nil)
    }
    
    func initRegister() {
        Model.addButton(y: screenHeight / 2, title: "Register", s: #selector(registerPressed), vc: self)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

