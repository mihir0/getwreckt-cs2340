package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import java.util.HashMap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.User;
import getwreckt.cs2340.rattrack.model.Model;

/**
 * Created by maya v on 9/21/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Button loginBut;
    private Button registerBut;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataRef;
    private boolean persistenceEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initLogin();
        initRegister();
        mAuth = FirebaseAuth.getInstance();
        if (!persistenceEnabled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            persistenceEnabled = false;
        }
        mDataRef = FirebaseDatabase.getInstance().getReference();
        if (mAuth.getCurrentUser() != null) {
            Intent toInAppScreen = new Intent(WelcomeActivity.this,
                    InAppActivity.class);
            startActivity(toInAppScreen);
        }

        //deletes ratsightings?
        //mDataRef.child("ratsightings").setValue(null);

    }

    /**
     * Creates the login button.
     */
    public void initLogin() {
        loginBut = (Button) findViewById(R.id.login);
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLoginScreen = new Intent(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(toLoginScreen);
            }
        });
    }

    /**
     * Creates the register button.
     */
    public void initRegister() {
        registerBut = (Button) findViewById(R.id.register);
        registerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegScreen = new Intent(WelcomeActivity.this,
                        RegisterActivity.class);
                startActivity(toRegScreen);
            }
        });
    }

}
