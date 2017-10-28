package getwreckt.cs2340.rattrack.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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
    private User owner;

    public RatSighting() { }

    /**
     * Creates a new RatSighting with
     * username {@code username} and password {@code password}.
     * @param uniqueKey the unique id of the new sighting
     * @param date the date of the sighting
     * @param typeLocation the type of location
     * @param zip the zip of location
     * @param address the address of th sighting
     * @param city the city of the sighting
     * @param borough the borough of the sighting
     * @param latitude the latitude of the sighting
     * @param longitude the longitude of the sighting
     */
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
    }

    /**
     * Creates a new RatSighting with
     * username {@code username} and password {@code password}.
     * @param date the date of the sighting
     * @param typeLocation the type of location
     * @param zip the zip of location
     * @param address the address of th sighting
     * @param city the city of the sighting
     * @param borough the borough of the sighting
     * @param latitude the latitude of the sighting
     * @param longitude the longitude of the sighting
     */
    public RatSighting(User owner, String date, String typeLocation, String zip, String address,
                       String city, String borough, String latitude, String longitude) {
        this.owner = owner;
        Log.d("UserNull RatSighting", owner.getUserName());
        this.date = date;
        this.typeLocation = typeLocation;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
        this.uniqueKey = generateUniqueKey();
    }

    /**
     * Creates new RatSighting without city or zip known
     * @param uniqueKey the unique id of the new sighting
     * @param date the date of the sighting
     * @param typeLocation the type of location
     * @param address the address of th sighting
     * @param borough the borough of the sighting
     * @param latitude the latitude of the sighting
     * @param longitude the longitude of the sighting
     */
    public RatSighting(String date, String address,
                                   String borough, String typeLocation,
                                   String latitude, String longitude, String uniqueKey) {
        this.date = date;
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
    }

    public static final Parcelable.Creator<RatSighting> CREATOR
            = new Parcelable.Creator<RatSighting>() {
        public RatSighting createFromParcel (Parcel in) {return new RatSighting(in);}

        public RatSighting[] newArray(int size) {return new RatSighting[size];}
    };

    @Override
    public int describeContents() {return 0;}

    /**
     * Gets the unique key of a sighting
     * @return the unique key of a sighting
     */
    public String getUniqueKey() {
        return this.uniqueKey;
    }
    /**
     * Sets the uniqueKey to the given key.
     * @param uniqueKey the uniqueKey to set
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * Gets the date of a sighting
     * @return the date of a sighting
     */
    public String getDate() {
        return this.date;
    }
    /**
     * Sets the date to the given date.
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the type of location of a sighting
     * @return the type of location of a sighting
     */
    public String getTypeLocation() {
        return this.typeLocation;
    }
    /**
     * Sets the type of location to the given typeLocation.
     * @param typeLocation the type of location to set
     */
    public void setTypeLocation(String typeLocation) {
        this.typeLocation = typeLocation;
    }

    /**
     * Gets the addressof a sighting
     * @return the address of a sighting
     */
    public String getAddress() {
        return this.address;
    }
    /**
     * Sets the address to the given address.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city of a sighting
     * @return the city of a sighting
     */
    public String getCity() {return this.city;}
    /**
     * Sets the city to the given city.
     * @param city the city to set
     */
    public void setCity(String city) {this.city = city;}

    /**
     * Gets the borough of a sighting
     * @return the borough of a sighting
     */
    public String getBorough() {
        return this.borough;
    }
    /**
     * Sets the borough to the given borough.
     * @param borough the date to set
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * Gets the latitude of a sighting
     * @return the latitude of a sighting
     */
    public String getLatitude() {
        return this.latitude;
    }
    /**
     * Sets the latitude to the given latitude.
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of a sighting
     * @return the longitude of a sighting
     */
    public String getLongitude() {
        return this.longitude;
    }
    /**
     * Sets the longitude to the given longitude.
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the zip of a sighting
     * @return the zip of a sighting
     */
    public String getZip() {
        return this.zip;
    }
    /**
     * Sets the zip to the given zip.
     * @param zip the zip to set
     */
    public void setZip(String zip){
        this.zip = zip;
    }

    private User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    private String generateUniqueKey() {
        String uname = Model.getCurrentUser().getUserName();
        String user = uname.substring(0, uname.indexOf('.')) + uname.substring(uname.indexOf('.')
                + 1);
        owner.sightingMade();
        return user + this.date + getOwner().getSightings();
    }

    @Override
    public String toString() {
        return date + " " + uniqueKey;
    }
}
