package getwreckt.cs2340.rattrack.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import getwreckt.cs2340.rattrack.R;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    private static ArrayList<RatSighting> ratSightings = new ArrayList<>();

    /**
     * The private Model instance.
     * @return the private Model instance
     */
    public static Model getInstance() {return _instance;}

    /**
     * Creates a Model with dummy data.
     */
    private Model() {
        loadDummyData();
    }

    private User currentUser;

    /**
     * Adds a sample user to the Model.
     */
    private void loadDummyData() {
        UserList.addUser(new User("user", "password"));
    }

    /**
     * Current user in the system.
     * @return current user
     */
    public User getCurrentUser() {return this.currentUser;}

    /**
     * Sets the current user to {@code currentUser}.
     * @param currentUser the user to set as the current user
     */
    public void setCurrentUser(User currentUser) {this.currentUser = currentUser;}

    public void readCSVFile(InputStream is) {

        try {
            //InputStream is = getResources().openRawResource(R.raw.Rat_Sightings);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while((line = br.readLine()) != null) {
                Log.d("Model", line);
                String[] sightData = line.split(",");

                //add new Sighting to list of sightings
                ratSightings.add(new RatSighting(sightData[0], sightData[1], sightData[7], sightData[8],
                        sightData[15], sightData[23], sightData[24], sightData[25]));

            }
            br.close();

        } catch (IOException e) {
            Log.e("Model", "error reading csv data");
        }
    }

}
