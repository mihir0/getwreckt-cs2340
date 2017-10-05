package getwreckt.cs2340.rattrack.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import getwreckt.cs2340.rattrack.R;

/**
 * Created by maya v on 9/21/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() {return _instance;}
    private Model() {
        loadDummyData();
    }
    private User currentUser;

    private void loadDummyData() {
        UserList.addUser(new User("user", "password"));
    }

    public User getCurrentUser() {return this.currentUser;}

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

            }
            br.close();

        } catch (IOException e) {
            Log.e("Model", "error reading csv data");
        }
    }

}
