package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.components.XAxis;
import com.google.android.gms.vision.text.Line;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.Model;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.SightingManager;
import getwreckt.cs2340.rattrack.model.User;
import getwreckt.cs2340.rattrack.model.Date;
import getwreckt.cs2340.rattrack.model.SightingManager;

public class GraphActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDataRef;
    private EditText startDate;
    private EditText endDate;
    private Button newGraph;
    private LineChart lineChart;
    private String[] xAxisIntervals = {};
    private String sortBy = "";
    private HashMap<Integer, Integer> dateToCountMap = new HashMap<>();
    List<String> intervalList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mAuth = FirebaseAuth.getInstance();
        mDataRef = FirebaseDatabase.getInstance().getReference();
        lineChart = (LineChart) findViewById(R.id.chart);

        startDate = (EditText) findViewById(R.id.start_date);
        endDate = (EditText) findViewById(R.id.end_date);

        newGraph = (Button) findViewById(R.id.new_graph);

        getGraphReady();

        newGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.viewToGoTo = "Graph";
                Intent toDateRange = new Intent(GraphActivity.this, DateRangeActivity.class);
                startActivity(toDateRange);
            }
        });
    }


    /**
     * Calls DateRangeActivity to set startDate and endDate
     */
    public void getDateRange() {
        Model.viewToGoTo = "Graph";
        Intent toDateRange = new Intent(GraphActivity.this,
                DateRangeActivity.class);
        startActivity(toDateRange);
    }

    public void getGraphReady() {
        mDataRef.child("ratsightings").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                intervalList.clear();

                if (mAuth.getCurrentUser() != null) {
                    SightingManager.ratSightings.clear();

                    Date initDate = SightingManager.startGraphDate;
                    Date lastDate = SightingManager.endGraphDate;

                    int beginYear = initDate.getYear();
                    int beginMonth = initDate.getMonth();
                    int beginDate = initDate.getDate();
                    int beginHour = initDate.getHour();
                    int beginMinute = initDate.getMinute();
                    int beginSecond = initDate.getSecond();

                    int endingYear = lastDate.getYear();
                    int endingMonth = lastDate.getMonth();
                    int endingDate = lastDate.getDate();
                    int endingHour = lastDate.getHour();
                    int endingMinute = lastDate.getMinute();
                    int endingSecond = lastDate.getSecond();

                    int yearPos = 10000;
                    int monPos = 100;

                    int startDate = beginYear * yearPos;
                    startDate += (beginMonth * monPos);
                    startDate += beginDate;
                    int endDate = endingYear * yearPos;
                    endDate += (endingMonth * monPos);
                    endDate += endingDate;
                    int endTime = endingHour * yearPos;
                    endTime += (endingMinute * monPos);
                    endTime += endingSecond;
                    int startTime = beginHour * yearPos;
                    startTime += (beginMinute * monPos);
                    startTime += beginSecond;


                    if (endDate - startDate >= yearPos) {
                        //count back from end year by month
                        sortBy = "months";
                        Log.e("List", "got here 2");

                        int i = beginYear;
                        int counter = 0;
                        for (int j = beginMonth; j < 13; j++) {
                            int dateKey = (j * monPos) + (i * yearPos);
                            dateToCountMap.put(dateKey, 0);
                            intervalList.add("" + counter);
                            if (i == endingYear && j == endingMonth) {
                                j = 12;
                            } else if (j == 12) {
                                i++;
                                j = 0;
                            }
                            counter++;
                        }
                    } else if (endDate - startDate >= monPos) {
                        //count back from end month
                        int endMonth = endingMonth;
                        int startMonth =  beginMonth;
                        if (startMonth - endMonth == 11) {
                            sortBy = "days";
                            int y = beginYear;
                            int m = beginMonth;
                            int counter = 0;
                            for (int j = beginDate; j <= daysForMonAndYear(m, y); j++) {
                                int dateKey = (m * monPos) + (y * yearPos) + j;
                                dateToCountMap.put(dateKey, 0);
                                intervalList.add("" +  counter);
                                if (m == endingMonth && j == endingDate
                                        && y == endingYear) {
                                    j = 32;
                                } else if (j == 31 && m == 12) {
                                    y++;
                                    m = 1;
                                    j = 1;
                                    j--;
                                } else if (j == daysForMonAndYear(m, y)) {
                                    m++;
                                    j = 1;
                                    j--;
                                }
                                counter++;
                            }
                        } else {
                            sortBy = "months";
                            int y = beginYear;
                            int m = beginMonth;
                            int counter = 0;
                            for (int j = beginDate; j <= daysForMonAndYear(m, y); j++) {
                                int dateKey = (m * monPos) + (y * yearPos);
                                dateToCountMap.put(dateKey, 0);
                                intervalList.add("" + counter);
                                if (m == endingMonth && j == endingDate
                                        && y == endingYear) {
                                    j = 32;
                                } else if (j == 31 && m == 12) {
                                    y++;
                                    m = 1;
                                    j = 1;
                                    j--;
                                } else if (j == daysForMonAndYear(m, y)) {
                                    m++;
                                    j = daysForMonAndYear(m, y);
                                    j--;
                                }
                                counter++;
                            }
                        }
                    } else if (endDate - startDate >= 1) {
                        //count back from end day
                        sortBy = "days";
                        int y = beginYear;
                        int m = beginMonth;
                        int counter = 0;
                        for (int j = beginDate; j <= daysForMonAndYear(m, y); j++) {
                            int dateKey = (m * monPos) + (y * yearPos) + j;
                            dateToCountMap.put(dateKey, 0);
                            intervalList.add("" + counter);
                            if (m == endingMonth && j == endingDate
                                    && y == endingYear) {
                                j = 32;
                            } else if (j == 31 && m == 12) {
                                y++;
                                m = 1;
                                j = 1;
                                j--;
                            } else if (j == daysForMonAndYear(m, y)) {
                                m++;
                                j = daysForMonAndYear(m, y);
                                j--;
                            }
                            counter++;
                        }
                    }
                    xAxisIntervals = new String[intervalList.size()];
                    int index = 0;
                    for (String s: intervalList) {
                        xAxisIntervals[index] = s;
                        index++;
                    }
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Log.e("List", "" + SightingManager.ratSightings.size());


                        RatSighting ratSighting = postSnapshot.getValue(RatSighting.class);
                        Date date = ratSighting.getDate();
                        int year = date.getYear();
                        int month = date.getMonth();
                        int day = date.getDate();
                        int dateToInspect = (year * yearPos) + (month * monPos) + (day);

                        int hour = date.getHour();
                        int min = date.getMinute();
                        int sec = date.getSecond();
                        int timeToInspect = (hour * yearPos) + (min * monPos) + (sec);

                        Log.e("List", "" + dateToInspect);

                        if (dateToInspect >= startDate && dateToInspect <= endDate) {
                            Log.e("List", "" + postSnapshot.getValue(RatSighting.class));
                            if (sortBy.equals("years") || sortBy.equals("months")) {
                                int monthKey = (dateToInspect / monPos) * monPos;
                                if (dateToCountMap.get(monthKey) != null) {
                                    int count = dateToCountMap.get(monthKey);
                                    dateToCountMap.put(monthKey, count + 1);
                                }
                            } else if (sortBy.equals("days")) {
                                int dayKey = dateToInspect;
                                if (dateToCountMap.get(dayKey) != null) {
                                    int count = dateToCountMap.get(dayKey);
                                    dateToCountMap.put(dayKey, count + 1);
                                }
                            }
                        }

                    }
                }
                List<Entry> entries = convertDataSetToEntry(dateToCountMap, xAxisIntervals);
                LineDataSet dataset = new LineDataSet(entries, "# of ratsightings by " + sortBy + " since " + SightingManager.startGraphDate.toString());
                LineData data = new LineData(dataset);
                dataset.setColors(ColorTemplate.rgb("7FFF"));
                dataset.setDrawFilled(true);
                lineChart.setData(data);
                lineChart.setVisibleXRangeMinimum(intervalList.size());
                lineChart.fitScreen();
                lineChart.animateY(5000);
//                XAxis xAxis = lineChart.getXAxis();
//                lineChart.getDescription().setText("sightings over time");
//                xAxis.setValueFormatter(new MyAxisValueFormatter(xAxisIntervals));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int daysForMonAndYear(int mon, int year) {
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            return 31;
        } else if (mon == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }

    private List<Entry> convertDataSetToEntry(HashMap<Integer, Integer> data, String[] intervals) {
        Log.e("List", "got here");
        Log.e("List", "" + intervalList.size());

        Set<Integer> keys = data.keySet();
        List<Entry> entries = new ArrayList<>();
        List<Integer> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        int i = 0;
        for (Integer k: keyList) {
            Log.e("List", "got here 2");

            int count = data.get(k);
            String s = intervals[i];
            int interval = Integer.parseInt(s);
            entries.add(new Entry(interval, count));
            i++;
        }

        return entries;
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter {

        private String[] mValues;

        public MyAxisValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            return mValues[(int) value];
        }

        /** this is only needed if numbers are returned, else return 0 */
        public int getDecimalDigits() { return 0; }
    }


}
