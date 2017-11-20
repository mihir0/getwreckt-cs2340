package getwreckt.cs2340.rattrack.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aguy on 10/8/17.
 */

public class Model {
    //private Model _model = new Model();
    private static User currentUser;

    private static DatabaseReference mDataRef;
    private static FirebaseAuth mAuth;
    public static boolean persistenceEnabled = true;

    /**
     * constructor for a model
     */
    private Model() {
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * returns the logged in user
     * @return Logged in user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * setter for the current user
     * @param user user of the system
     * @return new user
     */
    public static void setCurrentUser(User user) { currentUser = user; }

    public static String viewToGoTo = null;


}
