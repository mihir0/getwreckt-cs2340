package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import getwreckt.cs2340.rattrack.R;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

import getwreckt.cs2340.rattrack.model.Model;

/**
 * Created by maya v on 9/21/2017.
 */

public class InAppActivity extends AppCompatActivity {

    private Button logoutButn;
    private Button startButn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inapp);
        text = (TextView) findViewById(R.id.textView);
        String strLine = "Hello, " + Model.getInstance().getCurrentUser().getFullName()
                + ", You are now logged in!";
        text.setText(strLine);

        logoutButn = (Button) findViewById(R.id.logout);
        logoutButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toWelcomeScreen = new Intent(InAppActivity.this,
                        WelcomeActivity.class);
                startActivity(toWelcomeScreen);
            }
        });

        startButn = (Button) findViewById(R.id.btn_start);
        startButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.println(Log.INFO, "CLICK", "Button is clicked!");
                InputStream is = getResources().openRawResource(R.raw.rat_sightings);
                Model.getInstance().readCSVFile(is);
                Intent toSightingsListView = new Intent(InAppActivity.this,
                        RatSightingListActivity.class);
                startActivity(toSightingsListView);


            }
        });


    }

}

