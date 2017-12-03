//
//  Admin.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
class Admin : AppUser {
    init(username: String) {
        super.init(fullName: "Default Name", userName: username, userType: "Admin")
    }
    init(fullName: String, username: String) {
        super.init(fullName: fullName, userName: username, userType: "Admin")
    }
}
