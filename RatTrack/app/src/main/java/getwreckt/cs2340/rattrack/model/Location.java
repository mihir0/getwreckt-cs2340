package getwreckt.cs2340.rattrack.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maya v on 10/20/2017.
 */

public class Location implements Parcelable {
    private String zip;
    private LocationType typeLocation;
    private String address;
    private String city;
    private Borough borough;
    private String latitude;
    private String longitude;

    public Location(String zip, LocationType typeLocation, String address, String city,
                    Borough borough, String latitude, String longitude) {
        this.zip = zip;
        this.typeLocation = typeLocation;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * public Location(...)
     * CONSTRUCTOR USING CURRENT LOCATION OPTION
     */

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public LocationType getTypeLocation() {
        return typeLocation;
    }

    public void setTypeLocation(LocationType typeLocation) {
        this.typeLocation = typeLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    private Location(Parcel in) {
        zip = in.readString();
        typeLocation = (LocationType) in.readSerializable();
        address = in.readString();
        city = in.readString();
        borough = (Borough) in.readSerializable();
        latitude = in.readString();
        longitude = in.readString();
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(zip);
        dest.writeSerializable(typeLocation);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeSerializable(borough);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel (Parcel in) {return new Location(in);}

        public Location[] newArray(int size) {return new Location[size];}
    };

    @Override
    public int describeContents() { return 0; }
}
