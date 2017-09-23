package getwreckt.cs2340.rattrack.controller;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.content.Intent;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;

/**
 * Created by maya v on 9/21/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView userField;
    private TextInputEditText passField;
    private Button registerButn;
    private TextInputLayout til;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /**
         * Grab dialog widgets
         */

        userField = (AutoCompleteTextView) findViewById(R.id.username);
        passField = (TextInputEditText) findViewById(R.id.password);
        til = (TextInputLayout) findViewById(R.id.text_input_layout);

        onRegPressed();
    }

    public void onRegPressed() {
        Log.d("Register", "Register new user");

        registerButn = (Button) findViewById(R.id.register);
        registerButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInAppScreen = new Intent(RegisterActivity.this,
                        InAppActivity.class);
                String username = userField.getText().toString();
                String password = passField.getText().toString();
                if (!isValidUserPass(username, password)) {
                    til.setError("A valid username and password are required");
                } else {

                    try {
                        UserList.addUser(username, password);
                        startActivity(toInAppScreen);

                    } catch (IllegalArgumentException iae) {
                        til.setError(iae.getMessage());
                    }
                }

            }
        });

    }

    public boolean isValidUserPass(String user, String pass) {
        return !pass.equals("") && !pass.equals("");
    }
}
