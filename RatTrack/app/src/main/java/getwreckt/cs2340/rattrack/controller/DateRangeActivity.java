package getwreckt.cs2340.rattrack.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.Model;

public class DateRangeActivity extends AppCompatActivity {

    private Spinner startMonth;
    private Spinner startDay;
    private EditText startYear;
    private Spinner startHour;
    private Spinner startMin;
    private CheckBox startisPM;
    private Spinner endMonth;
    private Spinner endDay;
    private EditText endYear;
    private Spinner endHour;
    private Spinner endMin;
    private CheckBox endisPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        startMonth = (Spinner) findViewById(R.id.start_month);
        startDay = (Spinner) findViewById(R.id.start_day);
        startYear = (EditText) findViewById(R.id.start_year);
        startHour = (Spinner) findViewById(R.id.start_hour);
        startMin = (Spinner) findViewById(R.id.start_min);
        startisPM = (CheckBox) findViewById(R.id.start_is_pm);
        endMonth = (Spinner) findViewById(R.id.end_month);
        endDay = (Spinner) findViewById(R.id.end_day);
        endYear = (EditText) findViewById(R.id.end_year);
        endHour = (Spinner) findViewById(R.id.end_hour);
        endMin = (Spinner) findViewById(R.id.end_min);
        endisPM = (CheckBox) findViewById(R.id.end_is_pm);

        Model.startGraphDate = new Date();
        Model.endGraphDate = new Date();
    }
}
