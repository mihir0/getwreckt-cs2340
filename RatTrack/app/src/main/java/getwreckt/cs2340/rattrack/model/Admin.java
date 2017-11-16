package getwreckt.cs2340.rattrack.model;

/**
 * Admin is User that can add, remove, and unlock users.
 * Created by Patel on 9/28/2017.
 */

public class Admin extends User {

    /**
     * no arg constructor required for firebase
     */
    public Admin() {}

    /**
     * Creates a new Admin, which is a User with special privileges, with
     * username {@code username} and password {@code password}.
     * @param username the username of the new Admin
     */
    public Admin(String username) {
        super(username);
    }

    /**
     * Creates a new Admin, which is a User with special privileges, with
     * full name {@code fullName}, username {@code username}, and password {@code password}.
     * @param fullName Admin's full name
     * @param username Admin's unique email
     */
    public Admin(String fullName, String username) {
        super(fullName, username, "Admin");
    }
}
