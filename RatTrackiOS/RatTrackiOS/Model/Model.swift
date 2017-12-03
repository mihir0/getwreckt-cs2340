//
//  Model.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

class Model {
    //NOTE: code pertaining to the firebase has not been added in yet
    
    static var currentUser = AppUser()
    static var persistenceEnabled = true
    static var viewToGoTo = ""
    
    //TODO: add java firebase code
    //private static DatabaseReference mDataRef;
    //private static FirebaseAuth mAuth;
    
    init() {
        //more firebase stuff (Java)
        //mDataRef = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();
    }
    
    static func setCurrentUser(user: AppUser) {
        self.currentUser = user
    }
    
    static func getCurrentUser() -> AppUser {
        return currentUser
    }
}
