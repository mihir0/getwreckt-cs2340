package getwreckt.cs2340.rattrack.model;
import java.util.HashMap;
import java.lang.Long;

import java.util.Map;

/**
 * Created by Patel on 9/21/2017.
 */

public class UserList {
    private static Map<String, Long> userList = new HashMap<>();

    public static void addUser(String user, Long pass) {
        if (!userIsTaken(user)) {
            userList.put(user, pass);
        } else {
            throw new IllegalArgumentException("The username you provided has already been taken.");
        }
    }


    public static boolean userPassMatch(String user, String pass) {
        return userList.containsKey(user) && (userList.get(user).equals(CryptHash.hash(pass)));
    }
    public static boolean userIsTaken(String user) {
        return userList.containsKey(user);
    }

}
