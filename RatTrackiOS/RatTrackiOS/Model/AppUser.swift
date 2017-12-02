//
//  User.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

class AppUser { //(JAVA) implements Parcelable
    var fullName: String
    var userName: String
    var userType: String
    var signedIn: Bool
    var isLocked: Bool
    var sightings: Int = 0
    
    convenience init() {
        self.init(fullName: "", userName: "", userType: "")
    }
    
    convenience init(userName: String) {
        self.init(fullName: "Default Name", userName: userName)
    }
    
    convenience init(fullName: String, userName: String) {
        self.init(fullName: fullName, userName: userName, userType: "User")
    }
    
    init(fullName: String, userName: String, userType:String) {
        self.fullName = fullName
        self.userName = userName
        self.userType = userType
        self.signedIn = true
        self.isLocked = false
        self.sightings = 0
    }
    
    func getUserName() -> String {
        return self.userName
    }
    
    func setUserType(userType: String) {
        self.userType = userType
    }
    
    func getUserType() -> String {
        return self.userType
    }
    
    func setUserName(userName: String) {
        self.userName = userName
    }
    
    func getSignedIn() -> Bool {
        return self.signedIn
    }
    
    func getFullName() -> String {
        return self.fullName
    }
    
    func setFullName(fullName: String) {
        self.fullName = fullName
    }
    
    func getSightings() -> Int {
        return self.sightings
    }
    
    func setSightings(sightings: Int) {
        self.sightings = sightings
    }
    
    
    func getIsLocked() -> Bool {
        return self.isLocked
    }
    
    func setLocked(locked: Bool) {
        self.isLocked = locked
    }
    
    
    func sightingMade() {
        self.sightings += 1
    }
    
    //(JAVA) :
    /*
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(fullName);
    dest.writeString(userName);
    dest.writeString(userType);
    dest.writeInt(signedIn ? 1 : 0);
    dest.writeInt(isLocked ? 1 : 0);
    dest.writeInt(sightings);
    }
    
    public static final Parcelable.Creator<User> CREATOR
    = new Parcelable.Creator<User>() {
    
    @Override
    public User createFromParcel (Parcel in) {return new User(in);}
    
    @Override
    public User[] newArray(int size) {return new User[size];}
    };
    
    @Override
    public int describeContents() {return 0;} */
}
