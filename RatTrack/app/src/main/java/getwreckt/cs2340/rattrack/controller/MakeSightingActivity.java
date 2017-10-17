package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import getwreckt.cs2340.rattrack.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import getwreckt.cs2340.rattrack.model.*;


import getwreckt.cs2340.rattrack.model.Model;

/**
 * Created by maya v on 10/16/2017.
 */

public class MakeSightingActivity extends AppCompatActivity {
    private EditText dateField;
    private EditText timeField;
    private EditText addrField;
    private Spinner boroughSpinner;
    private Spinner typeLocationSpinner;
    private TextView coordinates;
    private EditText latitudeField;
    private EditText longtudeField;
    private Button makeBtn;
    private RatSighting _sighting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sighting);

        dateField = (EditText) findViewById(R.id.date_input);
        timeField = (EditText) findViewById(R.id.time_input);
        addrField = (EditText) findViewById(R.id.address_input);

        //change to text input if it's easier
        boroughSpinner = (Spinner) findViewById(R.id.borough_input);
        typeLocationSpinner = (Spinner) findViewById(R.id.typeLocation_input);

        coordinates = (TextView) findViewById(R.id.coordinates_title);// just the "Coordinates" before tha lat/long input
        latitudeField = (EditText) findViewById(R.id.latitude_input);
        longtudeField = (EditText) findViewById(R.id.longitude_input);

        makeBtn = (Button) findViewById(R.id.make_button);

        ArrayList<RatSighting> sightings = Model.ratSightings;
        ArrayList<String> boroughs = new ArrayList<>();
        ArrayList<String> typeLocations = new ArrayList<>();

        for (RatSighting r: sightings) {
            if (!boroughs.contains(r.getBorough())) {
                boroughs.add(r.getBorough());
            }
            if (!typeLocations.contains(r.getTypeLocation())) {
                typeLocations.add(r.getTypeLocation());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, boroughs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, typeLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter);

        onMakeButtonPressed();

    }

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
                String latitude = latitudeField.getText().toString();
                String longitude = longtudeField.getText().toString();

                String borough = boroughSpinner.getSelectedItem().toString();
                String typeLocation = typeLocationSpinner.getSelectedItem().toString();
                String uniqueKey = Model.ratSightings.size() + "";
                if (!isValidSighting(date, time, address, borough, typeLocation,
                        latitude, longitude, uniqueKey)) {
                    dateField.setError("A valid username and password are required");
                } else {

                    try {
                        _sighting = new RatSighting(date, time, address,
                                borough, typeLocation,
                                latitude, longitude, uniqueKey);

                        startActivity(toInAppScreen);

                    } catch (IllegalArgumentException iae) {
                        dateField.setError(iae.getMessage());
                    }
                }

            }
        });

    }

    /**
     * Checks whether {@code user} and {@code pass} are nonempty strings
     * @param address the username to check
     * @param time the password to check
     * @return whether {@code user} and {@code pass} are not empty strings
     */
    public boolean isValidSighting(String date, String time, String address,
                                   String borough, String typeLocation,
                                   String latitude, String longitude,
                                   String uniqueKey) {
        return !date.equals("") && !time.equals("") && !address.equals("")
                && !address.equals("") && !borough.equals("") && !typeLocation.equals("")
                && !latitude.equals("") && !longitude.equals("") && !uniqueKey.equals("");
    }
}
