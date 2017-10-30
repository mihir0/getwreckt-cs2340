package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import getwreckt.cs2340.rattrack.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.User;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by maya v on 9/21/2017.
 */

public class InAppActivity extends AppCompatActivity {

    private Button logoutButn;
    private TextView text;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataRef;
    private Button startButn;
    private Button makeSightingButn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inapp);
        text = (TextView) findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();

        mDataRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mAuth.getCurrentUser() != null) {
                    Model.setCurrentUser(dataSnapshot.child(mAuth.getCurrentUser().getUid())
                            .getValue(User.class));
                    updateUI(mAuth.getCurrentUser());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        logoutButn = (Button) findViewById(R.id.logout);
        logoutButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        startButn = (Button) findViewById(R.id.btn_start);
        startButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.INFO, "CLICK", "Button is clicked!");
                //InputStream is = getResources().openRawResource(R.raw.rat_sightings);
                Intent toSightingsListView = new Intent(InAppActivity.this,
                        RatSightingListActivity.class);
                startActivity(toSightingsListView);


            }
        });


        makeSightingButn = (Button) findViewById(R.id.make_sighting);
        makeSightingButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.INFO, "CLICK", "Make sighting button clicked");
                Intent toMakeSighting = new Intent(InAppActivity.this,
                        MakeSightingActivity.class);
                startActivity(toMakeSighting);
            }
        });

    }

    private void logout() {
        HashMap<String, Object> mapSignedIn = new HashMap<String, Object>();
        mapSignedIn.put("signedIn", false);
        mDataRef.child("users").child(mAuth.getCurrentUser().getUid()).updateChildren(mapSignedIn);
        mAuth.signOut();
        Model.setCurrentUser(null);
        Intent toWelcomeScreen = new Intent(InAppActivity.this,
                WelcomeActivity.class);
        startActivity(toWelcomeScreen);
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if (Model.getCurrentUser() == null) {
            logout();
        } else if (Model.getCurrentUser().getSignedIn()) {
            //InputStream is = getResources().openRawResource(R.raw.rat_sightings);
            //Model.readCSVFile(is);
            String strLine = "Hello, " + Model.getCurrentUser().getFullName() + ", You are now logged in!";
            text.setText(strLine);
        }
    }
}

