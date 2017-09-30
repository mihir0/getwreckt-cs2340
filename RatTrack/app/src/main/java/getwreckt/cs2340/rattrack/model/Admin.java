package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/28/2017.
 */

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    public Admin(String fullName, String username, String password) {
        super(fullName, username, password);
    }
}
