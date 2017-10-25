package getwreckt.cs2340.rattrack.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by aguy on 10/8/17.
 */

public class Model {
    private Model _model = new Model();
    public static ArrayList<RatSighting> ratSightings = new ArrayList<>();

    private static DatabaseReference mDataRef;
    private static FirebaseAuth mAuth;

    /**
     * constructor for a model
     */
    private Model() {
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private static User currentUser;
    /**
     * get the current user
     * @return user currently logged in on a particular device
     */
    public static User getCurrentUser() { return currentUser; }

    /**
     * Set the current user
     * @param user the current user logged in on a particular device
     */
    public static void setCurrentUser(User user) { currentUser = user; }

    public static boolean persistenceEnabled = true;
}
