package getwreckt.cs2340.rattrack.model;
import java.util.HashMap;
import java.lang.Long;

import java.util.Map;

/**
 * Created by Patel on 9/21/2017.
 */

public class UserList {
    private static Map<String, User> userList = new HashMap<>();

    /**
     * Adds a user to the internal user list
     * @param user the user to be added
     */
    public static void addUser(User user) {
        if (!userIsTaken(user.getUserName())) {
            userList.put(user.getUserName(), user);
        } else {
            throw new IllegalArgumentException("The username you provided has already been taken.");
        }
    }

    /**
     * Returns the User object associated with the username {@code username}.
     * @param username the username that the User object is associated with.
     * @return the User object associated with the username
     */
    public static User getUser (String username) {
            return userList.get(username);
        }

    /**
     * Checks whether the password associated with the user with username {@code user} equals {@code pass}.
     * @param user the username of the user to check
     * @param pass the password to check whether it is equal to the password stored with the user {@code user}
     * @return whether the password in the User object associated with the username {@code user} in {@code userList}
     *         is {@code pass}
     */
    public static boolean userPassMatch(String user, String pass) {
        return userList.containsKey(user)
                && (userList.get(user).getPass().equals(CryptHash.hash(pass + userList.get(user).getSalt())));
    }

    /**
     * Checks whether a user associated with the username {@code user} is already stored in {@code userList}.
     * @param user the username to check
     * @return {@code true} is the username is associated with a value in {@code userList} and {@code false} otherwise
     */
    public static boolean userIsTaken(String user) {
        return userList.containsKey(user);
    }

}
