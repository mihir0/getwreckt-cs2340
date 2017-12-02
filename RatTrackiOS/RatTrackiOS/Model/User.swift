//
//  User.swift
//  RatTrackiOS
//
//  Created by Akhil Kikkeri on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation

class User { //(JAVA) implements Parcelable
    var fullName: String
    var userName: String
    var userType: Bool
    var signedIn: Bool
    var isLocked: Bool
    var sightings: Int = 0
    init(userName: String) {
        self.init(fullName: "Default Name", userName: userName)
    }
    init(fullName: String, userName: String) {
        self.fullName = fullName
        self.userName = userName
        self.userType = "User"
        self.signedIn = true
    }
    init(fullName:String, email:String, userType:String) {
        self.fullName = fullName
        self.userName = userName
        self.userType = userType
        self.signedIn = signedIn
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
        return self.getSignedIn
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
    
    func isLocked() -> Bool {
        return self.isLocked
    }
    
    func setLocked(locked: bool) {
        self.isLocked = locked
    }
    
    func sightingMade() {
        self.sightings++
    }
    
    //made by AK: (not in original java class)
    func getEmail() -> String {
        return ""
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
