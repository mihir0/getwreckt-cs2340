package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        final HashMap<String, String> month = new HashMap<>();
        month.put("Select", "0");
        month.put("January", "01");
        month.put("February", "02");
        month.put("March", "03");
        month.put("April", "04");
        month.put("May", "05");
        month.put("June", "06");
        month.put("July", "07");
        month.put("August", "08");
        month.put("September", "09");
        month.put("October", "10");
        month.put("November", "11");
        month.put("December", "12");

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

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, month.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(adapter);

        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDateString = month.get(startMonth.getSelectedItem().toString())
                        + "/" + startDay.toString()
                        + "/" + startYear.getText().toString() + " "
                        + startHour.toString() + ":"
                        + startMin.toString() + ":"
                        + (startisPM.isChecked() ? "PM" : "AM");

                String endDateString = month.get(endMonth.getSelectedItem().toString())
                        + "/" + endDay.toString()
                        + "/" + endYear.getText().toString() + " "
                        + endHour.toString() + ":"
                        + endMin.toString() + ":"
                        + (endisPM.isChecked() ? "PM" : "AM");

                if (Model.viewToGoTo.equals("Graph")) {
                    Model.startGraphDate = new Date(startDateString);
                    Model.endGraphDate = new Date(endDateString);
                    Intent toGraph = new Intent(DateRangeActivity.this, GraphActivity.class);
                    startActivity(toGraph);
                } else if (Model.viewToGoTo.equals("Map")) {
                    Model.startMapDate = new Date(startDateString);
                    Model.endMapDate = new Date(endDateString);
                    Intent toMap = new Intent(DateRangeActivity.this, RatSightingMapActivity.class);
                    startActivity(toMap);
                }
            }
        });

    }
}
