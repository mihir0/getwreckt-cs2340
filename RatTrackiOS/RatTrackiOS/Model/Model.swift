//
//  Model.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

class Model {
    static func setCurrentUser(u:AppUser) {
        
    }
    
    static func getCurrentUser() -> AppUser {
        return AppUser(fullName: "", email: "", userType: "")
    }
}
