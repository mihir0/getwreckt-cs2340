package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;



/**
 * Created by maya v on 9/21/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText passField;
    private AutoCompleteTextView userField;
    private Button loginButn;
    private Button cancelButn;
    private TextInputLayout passTil;
    private User _user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        Model model = Model.getInstance();
        Log.d("Login", "Login returning user");

        loginButn = (Button) findViewById(R.id.login_button);
        loginButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInAppScreen = new Intent(LoginActivity.this,
                        InAppActivity.class);
                String username = userField.getText().toString();
                String password = passField.getText().toString();
                if (UserList.userPassMatch(username, password)){
                    Model.getInstance().setCurrentUser(UserList.getUser(username));

                    startActivity(toInAppScreen);
                } else {
                    passTil.setError("Invalid username or password. Try Again.");
                }
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
