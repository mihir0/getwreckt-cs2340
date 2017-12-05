//
//  Date.swift
//  RatTrackiOS
//
//  Created by Mihir Parshionikar on 12/2/17.
//  Copyright © 2017 getwreckt. All rights reserved.
//

import Foundation
class AppDate : Comparable { //TODO: Write a compareTo method that allows Date objects to be compared (not quite sure how to "implement comparable" in Swift).
    
    var month: Int
    var date: Int
    var year: Int
    var hour: Int
    var minute: Int
    var second: Int
    var isPM: Bool
    var meridiem: String
    var systemString: String
    var tostring: String?
    
    init(month: Int, day: Int, year: Int, hour: Int, minute: Int, isPM: Bool) {
        self.date = day
        self.month = month
        self.year = year
        self.hour = hour
        self.minute = minute
        self.second = 0
        self.isPM = false
        self.systemString = ""
        self.tostring = nil
        
        if (isPM) {
            self.meridiem = "PM"
        } else {
            self.meridiem = "AM"
        }
        self.generateSystemString()
    }
    
    init(data: String) {
        var newData = data
        if newData == "" {
            newData = "1/1/2000 12:00:00 AM"
        }
        //example: "9/5/2012 12:00:00 AM"
        self.tostring = newData
        
        let arr = newData.split(separator: " ")
        let arr_dates = arr[0].split(separator: "/")
        let arr_times = arr[1].split(separator: ":")
    
        self.isPM = arr[2] == "PM" ? true : false
        self.month = (arr_dates[0] as NSString).integerValue
        self.date = (arr_dates[1] as NSString).integerValue
        self.year = (arr_dates[2] as NSString).integerValue
        self.hour = (arr_times[0] as NSString).integerValue
        self.minute = (arr_times[1] as NSString).integerValue
        self.second = (arr_times[2] as NSString).integerValue
        
        self.meridiem = ""
        self.systemString = ""
        
        self.setMeridiem()
        self.generateSystemString()
    }
    
    func getMonth() -> Int {
        return self.month
    }
    
    func setMonth(month: Int) {
        self.month = month
        self.generateSystemString()
    }
    
    func getDate() -> Int {
        return self.date
    }
    
    func setDate(date: Int) {
        self.date = date
        self.generateSystemString()
    }
    
    func getYear() -> Int {
        return self.year
    }
    
    func setYear(year: Int) {
        self.year = year
        self.generateSystemString()
    }
    
    func getHour() -> Int {
        return self.hour
    }
    //sets hour in military time
    func setHour(hour: Int) {
        if (isPM && (hour != 12)) {
            self.hour = hour + 12
        } else if (!isPM && (hour == 12)){
            self.hour = 0
        } else {
            self.hour = hour
        }
        self.generateSystemString();
    }
    
    func getMinute() -> Int {
        return self.minute
    }
    
    func setMinute(minute: Int) {
        self.minute = minute
        self.generateSystemString()
    }
    
    func getSecond() -> Int {
        return self.second
    }
    
    func setSecond(second: Int) {
        self.second = second
        self.generateSystemString()
    }
    
    func getIsPM() -> Bool {
        return self.isPM
    }
    
    func setIsPM(value: Bool) {
        self.isPM = value
        self.generateSystemString()
    }
    
    func getMeridiem() -> String {
        return self.meridiem
    }
    
    func setMeridiem() {
        self.meridiem = isPM ? "PM" : "AM"
        self.generateSystemString()
    }
    
    func digitToString(digit: Int) -> String {
        return ("\(digit < 10 ? "0" : "")\(digit)")
    }
    
    func getSystemString() -> String {
        return self.systemString
    }
    
    func generateSystemString() {
        self.systemString = "\(self.year)-\(digitToString(digit: self.month))-\(digitToString(digit: self.date)) \(digitToString(digit: self.hour)):\(digitToString(digit: self.minute)):\(digitToString(digit: self.second))"
    }
    
    //TODO: add a compareTo method
    // .
    // .
    // .
    // .
    // .
    
    func getMeridiemHour() -> Int {
        if (isPM) {
            if (self.hour == 12) {
                return 12
            }
            return self.hour - 12
        } else {
            if (self.hour == 0) {
                return 12
            }
        }
        return self.hour
    }
    
    func getTime() -> String {
        return "\(digitToString(digit: getMeridiemHour())):\(digitToString(digit: self.minute)):\(digitToString(digit: self.second)) \(self.meridiem)"
    }
    
    func getCalendarDate() -> String {
        return "\(digitToString(digit: self.month))-\(digitToString(digit: self.date))\(digitToString(digit: self.year))"
    }
    
    func toString() -> String { //does this need to override?
        return (tostring == nil) ? ("\(getCalendarDate()) \(getTime())") : tostring!
    }
    
    static func < (lhs:AppDate, rhs:AppDate) -> Bool {
        if (lhs.year != rhs.year) {
            return lhs.year < rhs.year
        } else if lhs.month != rhs.month {
            return lhs.month < rhs.month
        } else if lhs.date != rhs.date {
            return lhs.date < rhs.date
        } else if lhs.hour != rhs.hour {
            return lhs.hour < rhs.hour
        } else if lhs.minute != rhs.minute {
            return lhs.minute < rhs.minute
        } else if lhs.second != rhs.second {
            return lhs.second < rhs.second
        }
        return false
    }
    
    static func > (lhs:AppDate, rhs:AppDate) -> Bool {
        if (lhs.year != rhs.year) {
            return lhs.year < rhs.year
        } else if lhs.month != rhs.month {
            return lhs.month < rhs.month
        } else if lhs.date != rhs.date {
            return lhs.date < rhs.date
        } else if lhs.hour != rhs.hour {
            return lhs.hour < rhs.hour
        } else if lhs.minute != rhs.minute {
            return lhs.minute < rhs.minute
        } else if lhs.second != rhs.second {
            return lhs.second < rhs.second
        }
        return false
    }
    
    static func == (lhs:AppDate, rhs:AppDate) -> Bool {
        return lhs.year == rhs.year && lhs.month == rhs.month && lhs.date == rhs.date && lhs.hour == rhs.hour && lhs.minute == rhs.minute && lhs.second == rhs.second
    }
    
    //Other java code
    /*
    //Parcelable implementation
    private Date(Parcel in) {
    month = in.readInt();
    date = in.readInt();
    year = in.readInt();
    hour = in.readInt();
    minute = in.readInt();
    second = in.readInt();
    isPM = in.readByte() != 0;
    meridiem = in.readString();
    systemString = in.readString();
    }
     
    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(month);
    dest.writeInt(date);
    dest.writeInt(year);
    dest.writeInt(hour);
    dest.writeInt(minute);
    dest.writeInt(second);
    dest.writeInt(isPM ? 1 : 0);
    dest.writeString(meridiem);
    dest.writeString(systemString);
    }
    
    public static final Parcelable.Creator<Date> CREATOR = new Parcelable.Creator<Date>() {
    @Override
    public Date createFromParcel (Parcel in) {return new Date(in);}
    
    @Override
    public Date[] newArray(int size) {return new Date[size];}
    };
    
    @Override
    public int describeContents() {return 0;} */
}
