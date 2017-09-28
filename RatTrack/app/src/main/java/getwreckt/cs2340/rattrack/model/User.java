package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String fullName;
    private String userName;
    private String pass;
    public User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public User(String fullName, String userName, String pass) {
        this.fullName = fullName;
        this.userName = userName;
        this.pass = pass;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

