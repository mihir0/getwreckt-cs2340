package getwreckt.cs2340.rattrack.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import getwreckt.cs2340.rattrack.R;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    public static ArrayList<RatSighting> ratSightings = new ArrayList<>();
    private RatSighting currentSighting;

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
        UserList.addUser(new User("user", "pass"));
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

    /**
     * Current sighting being viewed
     * @return current sighting
     */
    public RatSighting getCurrentSighting() {return this.currentSighting;}

    /**
     * Sets the current sighting to the sighting given
     * @param sighting the sighting to set as the current sighting
     */
    public void setCurrentSighting(RatSighting sighting) {this.currentSighting = sighting;}

    /**
     * Reads a given CSV file and makes a new RatSighting from its values. New RatSighting is
     * put into an ArrayList of all the rat sightings provided in the CSV file.
     * @param is the input stream of the content of the CSV file
     */
    public void readCSVFile(InputStream is) {
        Log.d("Model", "READING CSV FILE");
        Log.println(Log.INFO, "Starting", "READING CSV FILE");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            br.readLine(); //get rid of header line
            while(((line = br.readLine()) != null) && line.length() != 0) {
                String[] sightData = line.split(",");
                //add new Sighting to list of sightings

                ratSightings.add(new RatSighting(sightData[0], sightData[1], sightData[7], sightData[8],
                        sightData[9], sightData[16], sightData[23], sightData[24], sightData[25]));
            }
            br.close();
            Log.d("Model", ratSightings.get(0).getUniqueKey());
            Log.d("Model", ratSightings.get(ratSightings.size() - 1).getUniqueKey());
        } catch (IOException e) {
            Log.e("Model", "error reading csv data");
        }
    }
}
