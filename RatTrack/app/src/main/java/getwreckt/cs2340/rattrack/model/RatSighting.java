package getwreckt.cs2340.rattrack.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Patel on 10/5/2017.
 */

public class RatSighting implements Parcelable {
    private String uniqueKey;
    private String date;
    private String zip;
    private String typeLocation;
    private String address;
    private String city;
    private String borough;
    private String latitude;
    private String longitude;
    private String time;

    public RatSighting(String uniqueKey, String date, String typeLocation,
                       String zip, String address, String city, String borough, String latitude,
                       String longitude) {
        this.uniqueKey = uniqueKey;
        this.date = date;
        this.typeLocation = typeLocation;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = "n/a";
    }

    public RatSighting(String date, String time, String address,
                                   String borough, String typeLocation,
                                   String latitude, String longitude, String uniqueKey) {
        this.date = date;
        this.time = time;
        this.address = address;
        this.borough = borough;
        this.typeLocation = typeLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = "n/a";
        this.zip = "n/a";
        this.uniqueKey = uniqueKey;
    }

    private RatSighting(Parcel in) {
        uniqueKey = in.readString();
        date = in.readString();
        typeLocation = in.readString();
        zip = in.readString();
        address = in.readString();
        city = in.readString();
        borough = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniqueKey);
        dest.writeString(date);
        dest.writeString(typeLocation);
        dest.writeString(zip);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(borough);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(time);
    }

    public static final Parcelable.Creator<RatSighting> CREATOR
            = new Parcelable.Creator<RatSighting>() {
        public RatSighting createFromParcel (Parcel in) {return new RatSighting(in);}

        public RatSighting[] newArray(int size) {return new RatSighting[size];}
    };

    @Override
    public int describeContents() {return 0;}

    public String getUniqueKey() {
        return this.uniqueKey;
    }
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeLocation() {
        return this.typeLocation;
    }
    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public String getBorough() {
        return this.borough;
    }
    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getZip() {
        return this.zip;
    }
    public void setZip(String zip){
        this.zip = zip;
    }

    @Override
    public String toString() {
        return date + " " + uniqueKey;
    }
}
