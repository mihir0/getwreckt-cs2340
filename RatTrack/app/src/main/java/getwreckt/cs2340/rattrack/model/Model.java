package getwreckt.cs2340.rattrack.model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Singleton class that is a Facade of Model
 * holds instances of the current User and firebase database and author
 * Author: Akhil Kikkeri
 */

public class Model {
    private Model _model = new Model();
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
