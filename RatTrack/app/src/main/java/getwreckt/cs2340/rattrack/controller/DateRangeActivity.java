package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.SightingManager;

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
    private TextView errorMsg;
    private final HashMap<String, String> months = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);

        final HashMap<String, String> month = new HashMap<>();
        month.put("Month", "0");
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
        errorMsg = (TextView) findViewById(R.id.error_msg);

        //day spinner array
        ArrayList<String> days = new ArrayList<>();
        days.add("Day");
        for (int i = 1; i <= 31; i++) {
            days.add(String.format("%02d", i));
        }

        //hour spinner array
        ArrayList<String> hours = new ArrayList<>();
        hours.add("Hour");
        for (int i = 1; i <= 12; i++) {
            hours.add(String.format("%02d", i));
        }

        //min spinner array
        ArrayList<String> mins = new ArrayList<>();
        mins.add("Minute");
        for (int i = 0; i <= 59; i++) {
            mins.add(String.format("%02d", i));
        }

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, (String[]) month.keySet().toArray());
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(monthAdapter);

        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, days);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startDay.setAdapter(dayAdapter);
        endDay.setAdapter(dayAdapter);

        ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, hours);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startHour.setAdapter(hourAdapter);
        endHour.setAdapter(hourAdapter);

        ArrayAdapter<String> minAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mins);
        minAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        startMin.setAdapter(minAdapter);
        endHour.setAdapter(minAdapter);
        
        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidDate(startMonth.getSelectedItem().toString(),
                        startDay.getSelectedItem().toString(),
                        startYear.getText().toString(),
                        startHour.getSelectedItem().toString(),
                        startMin.getSelectedItem().toString()) &&
                        isValidDate(endMonth.getSelectedItem().toString(),
                                endDay.getSelectedItem().toString(),
                                endYear.getText().toString(),
                                endHour.getSelectedItem().toString(),
                                endMin.getSelectedItem().toString())) {

                    Date start = new Date(Integer.parseInt(startMonth.getSelectedItem().toString()),
                            Integer.parseInt(startDay.getSelectedItem().toString()),
                            Integer.parseInt(startYear.getText().toString()),
                            Integer.parseInt(startHour.getSelectedItem().toString()),
                            Integer.parseInt(startMin.getSelectedItem().toString()),
                            startisPM.isChecked());

                    Date end = new Date(Integer.parseInt(endMonth.getSelectedItem().toString()),
                            Integer.parseInt(endDay.getSelectedItem().toString()),
                            Integer.parseInt(endYear.getText().toString()),
                            Integer.parseInt(endHour.getSelectedItem().toString()),
                            Integer.parseInt(endMin.getSelectedItem().toString()),
                            endisPM.isChecked());

                    if (Model.viewToGoTo.equals("Graph")) {
                        SightingManager.startGraphDate = start;
                        SightingManager.endGraphDate = end;
                        Intent toGraph = new Intent(DateRangeActivity.this,
                                GraphActivity.class);
                        startActivity(toGraph);
                    } else if (Model.viewToGoTo.equals("Map")) {
                        SightingManager.startMapDate = start;
                        SightingManager.endMapDate = end;
                        Intent toMap = new Intent(DateRangeActivity.this,
                                RatSightingMapActivity.class);
                        startActivity(toMap);
                    }
                }
            }
        });

    }

    private boolean isValidDate(String selMonth, String selDay,
                                       String selYear, String selHour,
                                       String selMin) {
        if (selMonth.equals("Month")) {
            errorMsg.setText("Select a month");
            return false;
        }

        if (selDay.equals("Day")) {
            errorMsg.setText("Select a day");
            return false;
        }

        if (selHour.equals("Hour")) {
            errorMsg.setText("Select an hour");
            return false;
        }

        if (selMin.equals("Minute")) {
            errorMsg.setText("Select a minute");
            return false;
        }

        try {
            int year = Integer.parseInt(selYear);
            if (!((year  >= 1900) && (year <= Calendar.YEAR))) {
                errorMsg.setText("Enter a realistic year");
                return false;
            }
        } catch (NumberFormatException nfe) {
            errorMsg.setText("Invalid year entry");
            return false;
        }

        int day = Integer.parseInt(selDay);
        int year = Integer.parseInt(selYear);

        HashMap<String, Integer> monthDays = new HashMap<>();
        monthDays.put("January", 31);
        monthDays.put("March", 31);
        monthDays.put("April", 30);
        monthDays.put("May", 31);
        monthDays.put("June", 30);
        monthDays.put("July", 31);
        monthDays.put("August", 31);
        monthDays.put("September", 30);
        monthDays.put("October", 31);
        monthDays.put("November", 30);
        monthDays.put("December", 31);

        if (selMonth.equals("February")) {
            if (day > 28) {
                if (!((day == 29) && (year % 4 == 0))) {
                    errorMsg.setText("Invalid day for selected month");
                    return false;
                }
            }
        }

        if (Integer.parseInt(selDay) > monthDays.get(selMonth)) {
            errorMsg.setText("Invalid day for selected month");
            return false;
        }

        return true;
    }

    private boolean isValidRange(Date start, Date end) {
        //end > start
        Date.DateChainedComparator comp = new Date.DateChainedComparator();
        if (comp.compare(start, end) > 1) {
            errorMsg.setText("End date must come after start date");
            return false;
        }
        return true;
    }
}
