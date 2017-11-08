package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.SightingManager;
import getwreckt.cs2340.rattrack.model.User;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by Mihir Parshionikar on 10/24/2017.
 */
public class RatSightingMapActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap map;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataRef;
    private Button updateMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.

        setContentView(R.layout.activity_rat_sighting_map);

        updateMap = (Button) findViewById(R.id.update_map);
        updateMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.viewToGoTo = "Map";
                Intent toDateRange = new Intent(RatSightingMapActivity.this, DateRangeActivity.class);
                startActivity(toDateRange);
            }
        });

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we add markers.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;


        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();

        LatLng ll;

        mDataRef.child("ratsightings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser() != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        RatSighting ratSighting = ds.getValue(RatSighting.class);
                        if (isValidSighting(ratSighting)
                                && ratSighting.getDate().compareTo(SightingManager.startMapDate) >= 0
                                && ratSighting.getDate().compareTo(SightingManager.endMapDate) <= 0) {
                            try {
                                LatLng latLng = new LatLng(Double.parseDouble(ratSighting.getLocation().getLatitude()),
                                        Double.parseDouble(ratSighting.getLocation().getLongitude()));
                                String snippet = ratSighting.toString();
                                map.addMarker(new MarkerOptions().position(latLng).title(ratSighting.toString()).snippet(snippet));
                                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            } catch (Exception e) {

                            }
                        }
                    }
                    mDataRef.child("ratsightings").keepSynced(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean isValidSighting(RatSighting ratSighting) {
        return !(ratSighting.getLocation().getLatitude().equals("")
                || ratSighting.getLocation().getLongitude().equals(""));
    }
}
