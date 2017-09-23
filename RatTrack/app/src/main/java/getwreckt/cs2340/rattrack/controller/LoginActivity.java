package getwreckt.cs2340.rattrack.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;



/**
 * Created by maya v on 9/21/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText passField;
    private AutoCompleteTextView userField;
    private Button loginButn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


}
