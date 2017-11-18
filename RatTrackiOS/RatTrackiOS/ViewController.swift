//
//  ViewController.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 11/9/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var screenWidth:CGFloat = UIScreen.main.bounds.size.width
    var screenHeight:CGFloat = UIScreen.main.bounds.size.height

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let loginButton = UIButton(frame: CGRect(x: 0, y: screenHeight / 5, width: screenWidth, height: screenHeight / 20))
        loginButton.setTitleColor(UIColor.black, for: .normal)
        loginButton.backgroundColor = UIColor(red: 46/255, green: 204/255, blue: 113/255, alpha: 1)
        loginButton.setTitle("Login", for: .normal)
        self.view.addSubview(loginButton)
        
        let registerButton = UIButton(frame: CGRect(x: 0, y: screenHeight / 5 + screenHeight / 20 + screenHeight / 20, width: screenWidth, height: screenHeight / 20))
        registerButton.setTitleColor(UIColor.black, for: .normal)
        registerButton.backgroundColor = UIColor(red: 46/255, green: 204/255, blue: 113/255, alpha: 1)
        registerButton.setTitle("Register", for: .normal)
        self.view.addSubview(registerButton)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

