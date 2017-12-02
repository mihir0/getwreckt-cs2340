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
        let loginButton = UIButton(frame: CGRect(x: 0, y: screenHeight / 2 - screenHeight / 10, width: screenWidth, height: screenHeight / 20))
        loginButton.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        loginButton.setTitleColor(UIColor.black, for: .normal)
        loginButton.setTitle("Login", for: .normal)
        loginButton.addTarget(self, action: #selector(loginPressed), for: .touchUpInside)
        self.view.addSubview(loginButton)
    }
    
    @objc
    func registerPressed() {
        self.present(RegisterViewController(), animated: true, completion: nil)
    }
    
    func initRegister() {
        let registerButton = UIButton(frame: CGRect(x: 0, y: screenHeight / 2, width: screenWidth, height: screenHeight / 20))
        registerButton.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        registerButton.setTitleColor(UIColor.black, for: .normal)
        registerButton.setTitle("Register", for: .normal)
        registerButton.addTarget(self, action: #selector(registerPressed), for: .touchUpInside)
        self.view.addSubview(registerButton)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

