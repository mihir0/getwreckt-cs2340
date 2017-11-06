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
    private final HashMap<String, String> months = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        months.put("Select", "0");
        months.put("January", "01");
        months.put("February", "02");
        months.put("March", "03");
        months.put("April", "04");
        months.put("May", "05");
        months.put("June", "06");
        months.put("July", "07");
        months.put("August", "08");
        months.put("September", "09");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December", "12");

        ArrayList<String> days = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            days.add(String.format("%02d", i));
        }

        ArrayList<String> hours = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            hours.add(String.format("%02d", i));
        }

        ArrayList<String> minutes = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            minutes.add(String.format("%02d", i));
        }

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

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDay.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, hours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startHour.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, minutes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMin.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, months.keySet().toArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endMonth.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endDay.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, hours);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endHour.setAdapter(adapter);

        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, minutes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endMin.setAdapter(adapter);

        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDateString = months.get(startMonth.getSelectedItem().toString())
                        + "/" + startDay.toString()
                        + "/" + startYear.getText().toString() + " "
                        + startHour.toString() + ":"
                        + startMin.toString() + ":"
                        + (startisPM.isChecked() ? "PM" : "AM");

                String endDateString = months.get(endMonth.getSelectedItem().toString())
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
