package getwreckt.cs2340.rattrack.model;
import java.util.HashMap;

import java.util.Map;

/**
 * Created by Patel on 9/21/2017.
 */

public class UserList {
    private static Map<String, String> userList = new HashMap<>();

    public static void addUser(String user, String pass) {
        if (!userIsTaken(user)) {
            userList.put(user, pass);
        } else {
            throw new IllegalArgumentException("The username you provided has already been taken.");
        }
    }

    public static boolean loginCheck(String user, String pass) {
        return userList.containsKey(user) && (userList.get(user).equals(pass));
    }
    public static boolean userIsTaken(String user) {
        return userList.containsKey(user);
    }

}
