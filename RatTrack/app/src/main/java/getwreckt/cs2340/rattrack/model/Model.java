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

    private Model() {
        mDataRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    private static User currentUser;

    public static User getCurrentUser() { return currentUser; }
    public static User setCurrentUser(User user) { return currentUser = user; }

    public static void readCSVFile(InputStream is) {
        //mDataRef = FirebaseDatabase.getInstance().getReference();
        //mAuth = FirebaseAuth.getInstance();
        Log.d("Model", "READING CSV FILE");
        Log.println(Log.INFO, "Starting", "READING CSV FILE");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            br.readLine(); //get rid of header line
            while(((line = br.readLine()) != null) && line.length() != 0) {
                String[] sightData = line.split(",");
                //add new Sighting to list of sightings
                RatSighting rs = new RatSighting(sightData[0], sightData[1], sightData[7], sightData[8],
                        sightData[9], sightData[16], sightData[23], sightData[24], sightData[25]);
                ratSightings.add(rs);
                //mDataRef.child("ratsightings").child(rs.getUniqueKey()).setValue(rs);
            }
            br.close();
            Log.d("Model", ratSightings.get(0).getUniqueKey());
            Log.d("Model", ratSightings.get(ratSightings.size() - 1).getUniqueKey());
        } catch (IOException e) {
            Log.e("Model", "error reading csv data");
        }
    }
}
