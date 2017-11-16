package getwreckt.cs2340.rattrack.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Patel on 10/5/2017.
 */

public class RatSighting implements Parcelable {

    private String uniqueKey;
    private Date date;
    private Location location;
    private User owner;
    private boolean isFlagged;

    /**
     * No argument constructor
     */
    public RatSighting() { }

    /**
     * Creates a new RatSighting using current user as the owner
     * username {@code username} and password {@code password}.
     * @param uniqueKey the unique id of the new sighting
     * @param date the string date of the sighting
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
        this.date = new Date(date);
        this.location = new Location(LocationType.get(typeLocation), address, city, zip, Borough.get(borough), latitude, longitude);
        this.owner = Model.getCurrentUser();
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
    public RatSighting(String date, String typeLocation, String zip, String address, String city,
                       String borough, String latitude, String longitude) {
        this.date = new Date(date);
        this.location = new Location(LocationType.get(typeLocation), address, city, zip, Borough.get(borough), latitude, longitude);
        this.uniqueKey = this.generateUniqueKey();
        this.owner = Model.getCurrentUser();
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
        this.uniqueKey = uniqueKey;
        this.date = new Date(date);
        this.location = new Location(LocationType.get(typeLocation), address, "N/A", "N/A",
                Borough.get(borough), latitude, longitude);
        this.owner = Model.getCurrentUser();
    }

    private RatSighting(Parcel in) {
        uniqueKey = in.readString();
        date = in.readParcelable(Date.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        owner = in.readParcelable(User.class.getClassLoader());
        isFlagged = in.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniqueKey);
        dest.writeParcelable(date, flags);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(owner, flags);
        dest.writeInt(isFlagged ? 1 : 0);
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
    public Date getDate() {
        return this.date;
    }
    /**
     * Sets the date to the given date.
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Getter method of user who made sighting
     * @return user who made sighting
     */
    private User getOwner() {
        return owner;
    }

    /**
     * Sets the user to the given owner
     * @param owner user of system
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Getter method of location
     * @return location of sighting
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setter method of location
     * @param location location of sighting
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * used to flag users
     * @return boolean value of flagged users
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Setter method used to flag users
     * @param flagged boolean value of flagged users
     */
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    /**
     * Generates unique key for every user who made sighting
     * @return string representation of the unique key
     */
    private String generateUniqueKey() {
        String uname = Model.getCurrentUser().getUserName();
        String user = uname.substring(0, uname.indexOf('.')) + uname.substring(uname.indexOf('.')
                + 1);
        this.setOwner(Model.getCurrentUser());
        owner.sightingMade();
        return user + this.date + getOwner().getSightings();
    }

    @Override
    public String toString() {
        return date + " " + uniqueKey;
    }
}
