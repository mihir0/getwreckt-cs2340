package getwreckt.cs2340.rattrack.model;
import java.util.HashMap;

import java.util.Map;

/**
 * Created by Patel on 9/21/2017.
 */

public class UserList {
    private Map<String, String> userList = new HashMap<>();

    public void addUser(String user, String pass) {
        if (!userIsTaken(user)) {
            userList.put(user, pass);
        } else {
            throw new IllegalArgumentException("The username you provided has already been taken.");
        }
    }
    public boolean loginCheck(String user, String pass) {
        return userList.containsKey(user) && (userList.get(user).equals(pass));
    }
    public boolean userIsTaken(String user) {
        return userList.containsKey(user);
    }
}
