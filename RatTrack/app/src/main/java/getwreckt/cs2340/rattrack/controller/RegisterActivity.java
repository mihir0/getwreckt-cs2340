package getwreckt.cs2340.rattrack.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import getwreckt.cs2340.rattrack.R;

/**
 * Created by maya v on 9/21/2017.
 */

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView userField;
    private EditText passField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
