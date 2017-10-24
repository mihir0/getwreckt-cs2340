package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;



/**
 * Created by maya v on 9/21/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText passField;

    public AutoCompleteTextView userField;

    //private AutoCompleteTextView userField;
    private Button loginButn;
    private Button cancelButn;
    private TextInputLayout passTil;
    private User _user;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();

        //Grab dialog widgets

        userField = (AutoCompleteTextView) findViewById(R.id.username);
        passField = (TextInputEditText) findViewById(R.id.password);
        passTil = (TextInputLayout) findViewById(R.id.pass_text_input_layout);
        onLoginPressed();
        onCancelPressed();
    }


    /**
     * Logs into the system and changes to the inapp view.
     */
    public void onLoginPressed() {

        Log.d("Login", "Login returning user");

        loginButn = (Button) findViewById(R.id.login_button);
        loginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent toInAppScreen = new Intent(LoginActivity.this,
                        InAppActivity.class);
                String username = userField.getText().toString();
                String password = passField.getText().toString();
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        HashMap<String, Object> mapSignedIn = new HashMap<String, Object>();
                                        mapSignedIn.put("signedIn", true);
                                        mDataRef.child("users").child(mAuth.getCurrentUser().getUid()).updateChildren(mapSignedIn);
                                        startActivity(toInAppScreen);
                                    } else {
                                        passTil.setError("Invalid username or password. Try Again.");
                                    }
                                }
                            });
            }
        });
    }

    /**
     * Cancels the login attempt and goes back to the welcome view
     */
    public void onCancelPressed() {
        cancelButn = (Button) findViewById(R.id.cancel_button);
        cancelButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }
}
