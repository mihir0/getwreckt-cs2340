package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String fullName;
    private String userName;
    private String pass;

    /**
     * Create a User with username {@code userName} and password {@code pass}.
     * @param userName the username for the user
     * @param pass the password for the user
     */
    public User(String userName, String pass) {
        this.fullName = "Default Name";
        this.userName = userName;
        this.pass = pass;
    }

    /**
     * Creates a user with full name {@code fullName}, username {@userName}, and password {@code pass}.
     * @param fullName the first and last name of the new user
     * @param userName the username of the new user
     * @param pass the password of the new user
     */
    public User(String fullName, String userName, String pass) {
        this.fullName = fullName;
        this.userName = userName;
        this.pass = pass;
    }

    /**
     * Username of the user
     * @return username of the user
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Set username of User to {@code userName}
     * @param userName the username to change
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Password of the user
     * @return username of the user
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Set password of User to {@code pass}
     * @param pass the password to change
     */
    public void setPass(String pass) {
        this.pass = pass;
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
}

