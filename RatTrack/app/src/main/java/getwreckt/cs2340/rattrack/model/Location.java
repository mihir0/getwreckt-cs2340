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

    /**
     * Empty Constructor for Firebase
     */
    public Location() { }

    /**
     * Parametrized Constructor
     * @param typeLocation type Location
     * @param address address of sighting
     * @param city city of sighting
     * @param zip zip oof sighting
     * @param borough borough of sighting
     * @param latitude latitude of sighting
     * @param longitude longitude of sighting
     */
    public Location(LocationType typeLocation, String address, String city, String zip,
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
     * Getter method for zip
     * @return zip in string
     */
    public CharSequence getZip() {
        return zip;
    }

    /**
     * Setter method for zip
     * @param zip zip in string
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Getter method for type location
     * @return type location
     */
    public LocationType getTypeLocation() {
        return typeLocation;
    }

    /**
     * Setter method for location
     * @param typeLocation location type
     */
    public void setTypeLocation(LocationType typeLocation) {
        this.typeLocation = typeLocation;
    }

    /**
     * Getter method for address
     * @return string representation of address
     */
    public CharSequence getAddress() {
        return address;
    }

    /**
     * Setter method for address
     * @param address address of sighting
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter method for city
     * @return city of sighting
     */
    public CharSequence getCity() {
        return city;
    }

    /**
     * Setter method for city
     * @param city city of sighting
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter method for borough
     * @return borough borough of the sighting
     */
    public Borough getBorough() {
        return borough;
    }

    /**
     * Setter method for borough
     * @param borough borough of sighting
     */
    public void setBorough(Borough borough) {
        this.borough = borough;
    }

    /**
     * Getter method for latitude
     * @return latitude of sighting
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Setter method for latitude
     * @param latitude latitude of sighting
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter method for longitude
     * @return longitude longitude of sighting
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Setter method for longitude
     * @param longitude longitude of sighting
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Parcelable implementation
     * @param in input data
     */
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

    /**
     *
     */
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel (Parcel in) {return new Location(in);}

        @Override
        public Location[] newArray(int size) {return new Location[size];}
    };

    @Override
    public int describeContents() { return 0; }
}
