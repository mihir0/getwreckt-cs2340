package getwreckt.cs2340.rattrack.model;

import java.security.SecureRandom;

/**
 * Created by Patel on 9/21/2017.
 */

public class User {
    private String fullName;
    private String userName;
    private String pass;
    private String salt;

    /**
     * Create a User with username {@code userName} and password {@code pass}.
     * @param userName the username for the user
     * @param pass the password for the user
     */
    public User(String userName, String pass) {
        this("Default Name", userName, pass);
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

        SecureRandom rand = new SecureRandom();
        byte[] bytes = new byte[20];
        rand.nextBytes(bytes);
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        this.salt = hexString.toString();
        this.pass = CryptHash.hash(pass + this.salt);
    }

    /**
     * Salt of the user
     * @return salt of the user
     */
    public String getSalt() {
        return this.salt;
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

