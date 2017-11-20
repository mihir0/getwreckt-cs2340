package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.RatSighting;

/**
 * Detail Sighting class
 */
public class SightingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_detail);

        //grab dialog widgets
        TextView uniqueKeyField = (TextView) findViewById(R.id.unique_key);
        TextView dateField = (TextView) findViewById(R.id.date);
        TextView addrField = (TextView) findViewById(R.id.address);
        TextView cityField = (TextView) findViewById(R.id.city);
        TextView zipField = (TextView) findViewById(R.id.zip);
        TextView boroughField = (TextView) findViewById(R.id.borough);
        TextView coordinateField = (TextView) findViewById(R.id.coordinates);

        Button doneBtn = (Button) findViewById(R.id.done_button);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSightingList = new Intent(SightingDetailActivity.this,
                        RatSightingListActivity.class);
                startActivity(toSightingList);
            }
        });

        RatSighting sighting = getIntent().getParcelableExtra("SIGHTING");

        //set details
        uniqueKeyField.setText(sighting.getUniqueKey());
        dateField.setText("Date: " + sighting.getDate());
        if ("".equals(sighting.getLocation().getAddress())) {
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
