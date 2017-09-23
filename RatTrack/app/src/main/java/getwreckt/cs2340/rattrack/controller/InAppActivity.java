package getwreckt.cs2340.rattrack.controller;

import android.support.v7.app.AppCompatActivity;
import getwreckt.cs2340.rattrack.R;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by maya v on 9/21/2017.
 */

public class InAppActivity extends AppCompatActivity {

    private Button logoutButn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inapp);
    }
}

