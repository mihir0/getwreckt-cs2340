//
//  Model.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
import UIKit

class Model {
    //NOTE: code pertaining to the firebase has not been added in yet
    
    static var currentUser:AppUser? = nil
    static var persistenceEnabled = true
    static var viewToGoTo = ""
    static var sighting:RatSighting? = nil
    
    //TODO: add java firebase code
    //private static DatabaseReference mDataRef;
    //private static FirebaseAuth mAuth;
    
    init() {
        //more firebase stuff (Java)
        //mDataRef = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();
    }
    
    static func setCurrentUser(user: AppUser?) {
        self.currentUser = user
    }
    
    static func getCurrentUser() -> AppUser? {
        return currentUser
    }
    
    static func addButton(y:CGFloat, title:String, s:Selector, vc:UIViewController) {
        let screenWidth = UIScreen.main.bounds.size.width
        let screenHeight = UIScreen.main.bounds.size.height
        var btn:UIButton = UIButton()
        btn = UIButton(frame: CGRect(x: 0, y: y, width: screenWidth, height: screenHeight / 20))
        btn.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        btn.setTitleColor(UIColor.black, for: .normal)
        btn.setTitle(title, for: .normal)
        btn.layer.borderColor = UIColor.black.cgColor
        btn.layer.borderWidth = 1.0
        btn.addTarget(self, action: s, for: .touchUpInside)
        vc.view.addSubview(btn)
    }
    
    static func addTextView(y:CGFloat, vc:UIViewController) -> UITextView {
        let screenWidth = UIScreen.main.bounds.size.width
        let screenHeight = UIScreen.main.bounds.size.height
        var txtvw:UITextView = UITextView()
        txtvw = UITextView(frame: CGRect(x: 0, y: y, width: screenWidth, height: screenHeight / 20))
        txtvw.backgroundColor = UIColor.init(red: 39/255, green: 174/255, blue: 96/255, alpha: 1)
        vc.view.addSubview(txtvw)
        return txtvw
    }
}
