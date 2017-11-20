package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import getwreckt.cs2340.rattrack.R;

/**
 * First activity user sees. Directs to welcome or registration
 * Author: Maya Viust
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initLogin();
        initRegister();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDataRef = FirebaseDatabase.getInstance().getReference();
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
    private void initLogin() {
        Button loginBut = (Button) findViewById(R.id.login);
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
    private void initRegister() {
        Button registerBut = (Button) findViewById(R.id.register);
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
