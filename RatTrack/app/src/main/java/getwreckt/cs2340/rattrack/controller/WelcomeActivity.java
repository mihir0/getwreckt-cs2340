package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import getwreckt.cs2340.rattrack.R;

/**
 * Created by maya v on 9/21/2017.
 */

public class WelcomeActivity extends AppCompatActivity {

    private Button loginBut;
    private Button registerBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

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
