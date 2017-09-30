package getwreckt.cs2340.rattrack.model;
import java.util.HashMap;
import java.lang.Long;

import java.util.Map;

/**
 * Created by Patel on 9/21/2017.
            */

    public class UserList {
        private static Map<String, User> userList = new HashMap<>();

        public static void addUser(User user) {
            if (!userIsTaken(user.getUserName())) {

                userList.put(user.getUserName(), user);
            } else {
                throw new IllegalArgumentException("The username you provided has already been taken.");
            }
        }

        public static User getUser (String username) {
            return userList.get(username);
        }

        public static boolean userPassMatch(String user, String pass) {
            return userList.containsKey(user) && (userList.get(user).getPass().equals(pass));
        }
    public static boolean userIsTaken(String user) {
        return userList.containsKey(user);
    }

}
