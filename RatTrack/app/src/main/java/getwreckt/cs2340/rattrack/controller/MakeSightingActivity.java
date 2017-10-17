package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import getwreckt.cs2340.rattrack.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import getwreckt.cs2340.rattrack.model.Model;

/**
 * Created by maya v on 10/16/2017.
 */

public class MakeSightingActivity extends AppCompatActivity {
    private EditText dateField;
    private EditText timeField;
    private EditText addrField;
    private Spinner boroughField;
    private Spinner typeLocationField;
    private TextView coordinates;
    private EditText latitudeField;
    private EditText longtudeField;
    private Button makeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_sighting);

        dateField = (EditText) findViewById(R.id.date_input);
        timeField = (EditText) findViewById(R.id.time_input);
        addrField = (EditText) findViewById(R.id.address_input);

        //change to text input if it's easier
        boroughField = (Spinner) findViewById(R.id.borough_input);
        typeLocationField = (Spinner) findViewById(R.id.typeLocation_input);

        coordinates = (TextView) findViewById(R.id.coordinates_title);// just the "Coordinates" before tha lat/long input
        latitudeField = (EditText) findViewById(R.id.latitude_input);
        longtudeField = (EditText) findViewById(R.id.longitude_input);

        makeBtn = (Button) findViewById(R.id.make_button);
    }

}
