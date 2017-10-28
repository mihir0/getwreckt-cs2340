package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.RatSighting;

public class SightingDetailActivity extends AppCompatActivity {
    //widgets for binding
    private TextView uniqueKeyField;
    private TextView dateField;
    private TextView addrField;
    private TextView cityField;
    private TextView zipField;
    private TextView boroughField;
    private TextView coordinateField;
    private Button doneBtn;

    private RatSighting sighting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_detail);

        //grab dialog widgets
        uniqueKeyField = (TextView) findViewById(R.id.unique_key);
        dateField = (TextView) findViewById(R.id.date);
        addrField = (TextView) findViewById(R.id.address);
        cityField = (TextView) findViewById(R.id.city);
        zipField = (TextView) findViewById(R.id.zip);
        boroughField = (TextView) findViewById(R.id.borough);
        coordinateField = (TextView) findViewById(R.id.coordinates);

        doneBtn = (Button) findViewById(R.id.done_button);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSightingList = new Intent(SightingDetailActivity.this,
                        RatSightingListActivity.class);
                startActivity(toSightingList);
            }
        });

        sighting = (RatSighting) getIntent().getParcelableExtra("SIGHTING");

        //set details
        uniqueKeyField.setText(sighting.getUniqueKey());
        dateField.setText("Date: " + sighting.getDate());
        if (sighting.getLocation().getAddress().equals("")) {
            addrField.setText("N/A");
        } else {
            addrField.setText(sighting.getLocation().getAddress());
        }
        cityField.setText(sighting.getLocation().getCity());
        zipField.setText(sighting.getLocation().getZip());
        boroughField.setText(sighting.getLocation().getBorough().getName());
        coordinateField.setText("Coordinates: (" + sighting.getLocation().getLatitude() + ", "
                + sighting.getLocation().getLongitude() + ")");

        setTitle("Sighting Details");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, RatSightingListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
