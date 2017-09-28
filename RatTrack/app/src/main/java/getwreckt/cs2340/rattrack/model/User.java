package getwreckt.cs2340.rattrack.model;

/**
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String pass;

    public User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public User(String firstName, String lastName, String userName, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {return this.firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return this.lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}
}
