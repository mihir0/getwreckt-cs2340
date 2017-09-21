package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String userName;
    private String pass;
    public User(String userName, String pass) {
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
}
