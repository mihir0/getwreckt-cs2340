package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.SightingManager;

/**
 * Created by maya v on 10/16/2017.
 */

public class MakeSightingActivity extends AppCompatActivity {
    private EditText dateField;
    private EditText timeField;
    private EditText addrField;
    private EditText cityField;
    private EditText zipField;
    private Spinner boroughSpinner;
    private Spinner typeLocationSpinner;
    private TextView coordinates;
    private EditText latitudeField;
    private EditText longtudeField;
    private Button makeBtn;
    private RatSighting _sighting;
    private DatabaseReference mDataRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sighting);

        dateField = (EditText) findViewById(R.id.date_input);
        timeField = (EditText) findViewById(R.id.time_input);
        addrField = (EditText) findViewById(R.id.address_input);
        cityField = (EditText) findViewById(R.id.city_input);
        zipField = (EditText) findViewById(R.id.zip_input);

        boroughSpinner = (Spinner) findViewById(R.id.borough_input);
        typeLocationSpinner = (Spinner) findViewById(R.id.typeLocation_input);

        coordinates = (TextView) findViewById(R.id.coordinates_title);// just the "Coordinates" before tha lat/long input
        latitudeField = (EditText) findViewById(R.id.latitude_input);
        longtudeField = (EditText) findViewById(R.id.longitude_input);

        makeBtn = (Button) findViewById(R.id.make_button);



        ArrayList<String> boroughs = new ArrayList<>();
        boroughs.add("Unknown");
        boroughs.add("Bronx");
        boroughs.add("Brooklyn");
        boroughs.add("Manhattan");
        boroughs.add("Queens");
        boroughs.add("Staten Island");

        ArrayList<String> typeLocations = new ArrayList<>();
        typeLocations.add("Unknown");
        typeLocations.add("1-2 Family Dwelling");
        typeLocations.add("1-2 Family Mixed Use Building");
        typeLocations.add("3+ Family Apt. Building");
        typeLocations.add("3+ Family Mixed Use Building");
        typeLocations.add("Commercial Building");
        typeLocations.add("Construction Site");
        typeLocations.add("Day Care/Nursery");
        typeLocations.add("Government Building");
        typeLocations.add("Hospital");
        typeLocations.add("Office Building");
        typeLocations.add("Other (Explain Below)");
        typeLocations.add("Parking Lot/Garage");
        typeLocations.add("Public Garden");
        typeLocations.add("Public Stairs");
        typeLocations.add("School/Pre-School");
        typeLocations.add("Single Room Occupancy (SRO)");
        typeLocations.add("Summer Camp");
        typeLocations.add("Vacant Building");
        typeLocations.add("Vacant Lot");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,
                boroughs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, typeLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeLocationSpinner.setAdapter(adapter);

        onMakeButtonPressed();

    }

    /**
     * Makes a new sighting
     */
    public void onMakeButtonPressed() {
        Log.d("Register", "Register new user");

        makeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInAppScreen = new Intent(MakeSightingActivity.this,
                        InAppActivity.class);

                String date = dateField.getText().toString();
                String time = timeField.getText().toString();
                String address = addrField.getText().toString();
                String city = cityField.getText().toString();
                String zip = zipField.getText().toString();
                String latitude = latitudeField.getText().toString();
                String longitude = longtudeField.getText().toString();

                String borough = boroughSpinner.getSelectedItem().toString();
                String typeLocation = typeLocationSpinner.getSelectedItem().toString();

                if (!isValidSighting(date, typeLocation, zip, address, city, borough, latitude,
                        longitude)) {
                    dateField.setError("Must fill all fields with valid sighting details.");
                } else {
                    try {
                        _sighting = new RatSighting(date + " " + time, typeLocation, zip, address, city, borough,
                                latitude, longitude);

                        SightingManager.addSighting(_sighting);

                        Log.d("UserNull MakeSighting", Model.getCurrentUser().getUserName());

                        mDataRef.child("ratsightings").child(_sighting.getUniqueKey()).setValue(_sighting);

                        startActivity(toInAppScreen);

                    } catch (IllegalArgumentException iae) {
                        dateField.setError(iae.getMessage());
                    }
                }
            }
        });
    }

    /**
     * Checks weather the sighting is valid or not
     * @param date date of the sighting
     * @param address address of the sighting
     * @param city city of the sighting
     * @param zip zip code of the sighting
     * @param borough borough of the sighting
     * @param typeLocation type location of the sighting
     * @param latitude latitude of the sighting
     * @param longitude longitude of the sighting
     * @return boolena result of the sighting if it is valid or not
     */
    public boolean isValidSighting(String date, String address, String city, String zip,
                                   String borough, String typeLocation, String latitude,
                                   String longitude) {
        return !date.equals("") && !address.equals("")
                && !address.equals("") && !borough.equals("") && !typeLocation.equals("")
                && !latitude.equals("") && !longitude.equals("");
    }
}
