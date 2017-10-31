package getwreckt.cs2340.rattrack.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.User;

import android.support.v4.app.FragmentActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.

        setContentView(R.layout.activity_rat_sighting_map);
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

        mDataRef.child("ratsightings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser() != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        RatSighting ratSighting = ds.getValue(RatSighting.class);
                        LatLng latLng = new LatLng(Double.parseDouble(ratSighting.getLocation().getLatitude()),
                                Double.parseDouble(ratSighting.getLocation().getLongitude()));
                        map.addMarker(new MarkerOptions().position(latLng).title(ratSighting.toString()));
                    }
                    mDataRef.child("ratsightings").keepSynced(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
