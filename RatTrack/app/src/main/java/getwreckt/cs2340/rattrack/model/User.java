package getwreckt.cs2340.rattrack.model;

import java.security.SecureRandom;

/**
 * The general object for the app's User. Holds all information about User.
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String fullName;
    private String userName;
    private String userType;
    private Boolean signedIn;
    private int sightings = 0;

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
     * @param userName the unique email of the new user
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
     * @param userName the unique email of the new user
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

    /**
     * Get number of sightings of user
     * @return number of sightings user has made
     */
    public int getSightings() {
        return sightings;
    }
    /**
     * Set number of sightings of user
     * @param sightings number sightings user has made
     */
    public void setSightings(int sightings) {
        this.sightings = sightings;
    }

    /**
     * increments number of sightings user has made
     *
     */
    public void sightingMade() {
        this.sightings++;
    }
}

