package getwreckt.cs2340.rattrack.controller;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.StringBuilderPrinter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.ArrayList;


import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;

/**
 * Created by maya v on 9/21/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView userField;
    private EditText fullNameField;
    private TextInputEditText passField;
    private Button registerButn;
    private Button cancelButn;
    private Spinner userTypeSpinner;

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

        userTypeSpinner = (Spinner) findViewById(R.id.user_type_spinner);

        /*
          Set up the adapter to display the user types in the spinner
         */

        ArrayList<String> userTypes = new ArrayList<String>();
        userTypes.add("User");
        userTypes.add("Admin");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        onRegPressed();
        onCancelPressed();
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
                String userType = userTypeSpinner.getSelectedItem().toString();

                if (!isValidUserPass(username, password)) {
                    til.setError("A valid username and password are required");
                } else {

                    try {
                        User user;
                        if (userType.equals("Admin")) {
                            user = new Admin(username, password);
                        } else {
                            user = new User(username, password);
                        }
                        UserList.addUser(user);
                        Model.getInstance().setCurrentUser(user);
                        startActivity(toInAppScreen);

                    } catch (IllegalArgumentException iae) {
                        til.setError(iae.getMessage());
                    }
                }

            }
        });

    }

    public void onCancelPressed() {
        cancelButn = (Button) findViewById(R.id.cancel);
        cancelButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.super.finish();
            }

        });
    }

    public boolean isValidUserPass(String user, String pass) {
        return !user.equals("") && !pass.equals("");
    }
}
