package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/28/2017.
 */

public class Admin extends User {

    /**
     * Creates a new Admin, which is a User with special privileges, with
     * username {@code username} and password {@code password}.
     * @param username the username of the new Admin
     * @param password the password of the new Admin
     */
    public Admin(String username, String password) {
        super(username, password);
    }

    /**
     * Creates a new Admin, which is a User with special privileges, with
     * full name {@code fullName}, username {@code username}, and password {@code password}.
     * @param fullName
     * @param username
     * @param password
     */
    public Admin(String fullName, String username, String password) {
        super(fullName, username, password);
    }
}
