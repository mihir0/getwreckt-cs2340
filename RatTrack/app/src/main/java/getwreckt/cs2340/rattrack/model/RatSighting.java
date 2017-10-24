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

    public RatSighting() { }

    /**
     * Creates a new RatSighting with
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
                       String address, String city, String zip, String borough, String latitude,
                       String longitude) {
        this.uniqueKey = uniqueKey;
        this.date = new Date(date);
        this.location = new Location(LocationType.valueOf(typeLocation), address, city, zip, Borough.valueOf(borough), latitude, longitude);
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
        this.location = new Location(LocationType.valueOf(typeLocation), address, city, zip, Borough.valueOf(borough), latitude, longitude);
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
        this.location = new Location(LocationType.valueOf(typeLocation), address, "N/A", "N/A",
                Borough.valueOf(borough), latitude, longitude);
        this.owner = Model.getCurrentUser();
    }

    private RatSighting(Parcel in) {
        uniqueKey = in.readString();
        date = in.readParcelable(Date.class.getClassLoader());
        location = in.readParcelable(Location.class.getClassLoader());
        //owner = in.readParcelable(User.class.getClassLoader());
        isFlagged = in.readInt() == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniqueKey);
        dest.writeParcelable(date, flags);
        dest.writeParcelable(location, flags);
        //dest.writeParcelable(owner, flags);
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

    private User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    private String generateUniqueKey() {
        return getOwner().getUserName() + this.date + getOwner().getSightings();
    }

    @Override
    public String toString() {
        return date + " " + uniqueKey;
    }
}
