package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 10/5/2017.
 */

public class RatSighting {
    private String uniqueKey;
    private String date;
    private String zip;
    private String typeLocation;
    private String address;
    private String borough;
    private String latitude;
    private String longitude;

    public RatSighting(String uniqueKey, String date, String typeLocation,
                       String zip, String address, String borough, String latitude,
                       String longitude) {
        this.uniqueKey = uniqueKey;
        this.date = date;
        this.typeLocation = typeLocation;
        this.zip = zip;
        this.address = address;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

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
}
