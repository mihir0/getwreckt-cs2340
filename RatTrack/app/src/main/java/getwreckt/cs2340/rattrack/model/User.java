package getwreckt.cs2340.rattrack.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.security.SecureRandom;

/**
 * Created by Patel on 9/21/2017.
 */

public class User implements Parcelable {
    private String fullName;
    private String userName;
    private String userType;
    private boolean signedIn;
    private boolean isLocked;
    private int sightings = 0;

    public User() {}

    /**
     * Create a User with username {@code userName} and password {@code pass}.
     * @param userName the username for the user
     */
    public User(String userName) {

        this("Default Name", userName);
    }

    /**
     * Creates a user with full name {@code fullName}, username {@userName}, and password {@code pass}.
     * @param fullName the first and last name of the new user
     * @param userName the username of the new user
     */
    public User(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
        this.userType = "User";
        this.signedIn = true;
    }

    /**
     * Creates a user with full name {@code fullName}, username {@userName}, and password {@code pass}.
     * @param fullName the first and last name of the new user
     * @param userName the username of the new user
     */
    public User(String fullName, String userName, String userType) {
        this.fullName = fullName;
        this.userName = userName;
        this.userType = userType;
        this.signedIn = true;
    }

    /**
     * Username of the user
     * @return username of the user
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Set type of User to {@code userType}
     * @param userType the user type to change
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Username of the user
     * @return username of the user
     */
    public String getUserType() {
        return this.userType;
    }

    /**
     * Set username of User to {@code userName}
     * @param userName the username to change
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Username of the user
     * @return username of the user
     */
    public Boolean getSignedIn() {

        return this.signedIn;
    }

    /**
     * Set username of User to {@code userName}
     * @param signedIn the username to change
     */
    public void setSignedIn(Boolean signedIn) {
        this.signedIn = signedIn;
    }

    /**
     * Full name of the user
     * @return full name of the user
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Set full name of User to {@code fullName}
     * @param fullName the full name to change
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getSightings() {
        return sightings;
    }

    public void setSightings(int sightings) {
        this.sightings = sightings;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void sightingMade() {
        this.sightings++;
    }

    private User(Parcel in) {
        fullName = in.readString();
        userName = in.readString();
        userType = in.readString();
        signedIn = in.readInt() == 1;
        isLocked = in.readInt() == 1;
        sightings = in.readInt();
    }

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
        public User createFromParcel (Parcel in) {return new User(in);}

        public User[] newArray(int size) {return new User[size];}
    };

    @Override
    public int describeContents() {return 0;}
}

