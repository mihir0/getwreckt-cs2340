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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.DateChainedComparator;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.SightingManager;

public class DateRangeActivity extends AppCompatActivity {

    static DateRangeActivity activity;
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
    private final Map<String, String> months = new HashMap<>();


    private static class MonthComparator implements Comparator<String> {
        private Map<String, String> map;

        public MonthComparator(HashMap<String, String> map) {
            this.map = map;
        }
        @Override
        public int compare(String o1, String o2) {
            Integer i = Integer.parseInt(map.get(o1));
            Integer j = Integer.parseInt(map.get(o2));
            return i.compareTo(j);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_range);
        activity = this;

        final Map<String, String> month = new HashMap<>();
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


        //TODO change order for month adapter
        final ArrayList<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

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

        int maxDaysForMonth = 31;

        //day spinner array
        ArrayList<String> days = new ArrayList<>();
        days.add("Day");
        for (int i = 1; i <= maxDaysForMonth; i++) {
            days.add(String.format("%02d", i));
        }

        int maxHours = 12;

        //hour spinner array
        ArrayList<String> hours = new ArrayList<>();
        hours.add("Hour");
        for (int i = 1; i <= maxHours; i++) {
            hours.add(String.format("%02d", i));
        }

        int maxMin = 59;

        //min spinner array
        ArrayList<String> mins = new ArrayList<>();
        mins.add("Minute");
        for (int i = 0; i <= maxMin; i++) {
            mins.add(String.format("%02d", i));
        }

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, months);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startMonth.setAdapter(monthAdapter);
        endMonth.setAdapter(monthAdapter);

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
        endMin.setAdapter(minAdapter);
        
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


                    Date start = new Date(Integer.parseInt(month.get(startMonth.getSelectedItem().toString())),
                            Integer.parseInt(startDay.getSelectedItem().toString()),
                            Integer.parseInt(startYear.getText().toString()),
                            Integer.parseInt(startHour.getSelectedItem().toString()),
                            Integer.parseInt(startMin.getSelectedItem().toString()),
                            startisPM.isChecked());

                    Date end = new Date(Integer.parseInt(month.get(endMonth.getSelectedItem().toString())),
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

    public boolean isValidDate(String selMonth, String selDay,
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

        int minYear = 1900;

        try {
            int year = Integer.parseInt(selYear);
            if (!((year  >= minYear) && (year <= Calendar.getInstance().get(Calendar.YEAR)))) {
                errorMsg.setText("Enter a realistic year. You entered: " + year + ".");
                return false;
            }
        } catch (NumberFormatException nfe) {
            errorMsg.setText("Invalid year entry");
            return false;
        }

        int day = Integer.parseInt(selDay);
        int year = Integer.parseInt(selYear);

        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        HashMap<String, Integer> monthDays = new HashMap<>();
        monthDays.put("January", daysPerMonth[0]);
        monthDays.put("February", daysPerMonth[1]);
        monthDays.put("March", daysPerMonth[2]);
        monthDays.put("April", daysPerMonth[3]);
        monthDays.put("May", daysPerMonth[4]);
        monthDays.put("June", daysPerMonth[5]);
        monthDays.put("July", daysPerMonth[6]);
        monthDays.put("August", daysPerMonth[7]);
        monthDays.put("September", daysPerMonth[8]);
        monthDays.put("October", daysPerMonth[9]);
        monthDays.put("November", daysPerMonth[10]);
        monthDays.put("December", daysPerMonth[11]);

        if (selMonth.equals("February")) {
            if (day > daysPerMonth[1]) {
                if ((day == daysPerMonth[1] + 1) && (year % 4 == 0)) {
                    return true;
                } else {
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
        DateChainedComparator comp = new DateChainedComparator();
        if (comp.compare(start, end) > 1) {
            errorMsg.setText("End date must come after start date");
            return false;
        }
        return true;
    }

    public static DateRangeActivity getInstance(){
        return activity;
    }
}
