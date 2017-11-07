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
    private Button updateGraph;
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
        Date fromDate = SightingManager.startGraphDate;
        Date toDate = SightingManager.endGraphDate;
        startDate = (EditText) findViewById(R.id.start_date);
        endDate = (EditText) findViewById(R.id.end_date);
        updateGraph = (Button) findViewById(R.id.update_graph);
        newGraph = (Button) findViewById(R.id.new_graph);
        updateGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    SightingManager.startGraphDate = new Date(startDate.getText().toString());
                    SightingManager.endGraphDate = new Date(endDate.getText().toString());
                    Log.e("List", "got here");
                    getGraphReady();
                } catch (Exception e) {
                    endDate.setError("please enter valid dates");
                }
            }
        });

        newGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(GraphActivity.this, GraphActivity.class);
                startActivity(refresh);
            }
        });

    }

    public void getGraphReady() {
        mDataRef.child("ratsightings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (mAuth.getCurrentUser() != null) {
                    SightingManager.ratSightings.clear();

                    int startDate = SightingManager.startGraphDate.getYear() * 10000;
                    startDate += (SightingManager.startGraphDate.getMonth() * 100);
                    startDate += SightingManager.startGraphDate.getDate();
                    int endDate = SightingManager.endGraphDate.getYear() * 10000;
                    endDate += (SightingManager.endGraphDate.getMonth() * 100);
                    endDate += SightingManager.endGraphDate.getDate();
                    int endTime = SightingManager.endGraphDate.getHour() * 10000;
                    endTime += (SightingManager.endGraphDate.getMinute() * 100);
                    endTime += SightingManager.endGraphDate.getSecond();
                    int startTime = SightingManager.startGraphDate.getHour() * 10000;
                    startTime += (SightingManager.startGraphDate.getMinute() * 100);
                    startTime += SightingManager.startGraphDate.getSecond();


                    if (endDate - startDate >= 10000) {
                        //count back from end year by month
                        sortBy = "years";
                        int i = SightingManager.endGraphDate.getYear();
                        for (int j = SightingManager.endGraphDate.getMonth(); j >= 1; j--) {
                            int dateKey = (j * 100) + (i * 10000);
                            dateToCountMap.put(dateKey, 0);
                            intervalList.add(j + "");
                            if (i == SightingManager.startGraphDate.getYear() && j == SightingManager.startGraphDate.getMonth()) {
                                j = -1;
                            } else if (j == 1) {
                                i--;
                                j = 12;
                            }
                        }
                    } else if (endDate - startDate >= 100) {
                        //count back from end month
                        sortBy = "months";
                        int y = SightingManager.endGraphDate.getYear();
                        int m = SightingManager.endGraphDate.getMonth();
                        for (int j = SightingManager.endGraphDate.getDate(); j >= 1; j--) {
                            int dateKey = (m * 100) + (y * 10000) + j;
                            dateToCountMap.put(dateKey, 0);
                            intervalList.add(j + "");
                            if (m == SightingManager.startGraphDate.getMonth() && j == SightingManager.startGraphDate.getDate()
                                    && y == SightingManager.startGraphDate.getYear()) {
                                j = -1;
                            } else if (j == 1 && m == 1) {
                                y--;
                                m = 12;
                                j = 31;
                            } else if (m == 3 && j == 1 && (y%4 == 0)) {
                                j = 29;
                                m--;
                            } else if(m == 3 && j == 1) {
                                j = 28;
                                m--;
                            } else if (j == 1) {
                                m--;
                                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8
                                        || m == 10) {
                                    j = 31;
                                } else {
                                    j = 30;
                                }
                            }
                        }
                    } else if (endDate - startDate >= 1) {
                        //count back from end day
                        sortBy = "days";
                        int y = SightingManager.endGraphDate.getYear();
                        int m = SightingManager.endGraphDate.getMonth();
                        for (int j = SightingManager.endGraphDate.getDate(); j >= 1; j--) {
                            int dateKey = (m * 100) + (y * 10000) + j;
                            dateToCountMap.put(dateKey, 0);
                            intervalList.add(j + "");
                            if (m == SightingManager.startGraphDate.getMonth() && j == SightingManager.startGraphDate.getDate()
                                    && y == SightingManager.startGraphDate.getYear()) {
                                j = -1;
                            } else if (j == 1 && m == 1) {
                                y--;
                                m = 12;
                                j = 31;
                            } else if (m == 3 && j == 1 && (y%4 == 0)) {
                                j = 29;
                                m--;
                            } else if(m == 3 && j == 1) {
                                j = 28;
                                m--;
                            } else if (j == 1) {
                                m--;
                                if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8
                                        || m == 10) {
                                    j = 31;
                                } else {
                                    j = 30;
                                }
                            }
                        }
                    }
                    xAxisIntervals = new String[intervalList.size()];
                    int index = intervalList.size() - 1;
                    for (String s: intervalList) {
                        xAxisIntervals[index] = s;
                        index--;
                    }
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Log.e("List", "" + SightingManager.ratSightings.size());


                        RatSighting ratSighting = postSnapshot.getValue(RatSighting.class);
                        Date date = ratSighting.getDate();
                        int year = date.getYear();
                        int month = date.getMonth();
                        int day = date.getDate();
                        int dateToInspect = (year * 10000) + (month * 100) + (day);

                        int hour = date.getHour();
                        int min = date.getMinute();
                        int sec = date.getSecond();
                        int timeToInspect = (hour * 10000) + (min * 100) + (sec);

                        Log.e("List", "" + dateToInspect);

                        if (startDate == endDate) {
                            if (dateToInspect == startDate) {
                                if (timeToInspect >= startTime && timeToInspect <= endTime){
                                    SightingManager.ratSightings.add(ratSighting);
                                }
                            }
                        } else if (dateToInspect >= startDate && dateToInspect <= endDate) {
                            Log.e("List", "" + postSnapshot.getValue(RatSighting.class));
                            SightingManager.ratSightings.add(ratSighting);
                            if (sortBy.equals("years") || sortBy.equals("months")) {
                                int monthKey = (dateToInspect / 100) * 100;
                                int count = dateToCountMap.get(monthKey);
                                dateToCountMap.put(monthKey, count + 1);
                            } else if (sortBy.equals("days")) {
                                int dayKey = dateToInspect;
                                int count = dateToCountMap.get(dayKey);
                                dateToCountMap.put(dayKey, count + 1);
                            }

                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        List<Entry> entries = convertDataSetToEntry(dateToCountMap, xAxisIntervals);
        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        dataset.setDrawFilled(true);
        lineChart.setData(data);
        lineChart.animateY(5000);
        XAxis xAxis = lineChart.getXAxis();
        lineChart.getDescription().setText("Average Calls per Month");
        xAxis.setValueFormatter(new MyAxisValueFormatter(xAxisIntervals));

    }

    private List<Entry> convertDataSetToEntry(HashMap<Integer, Integer> data, String[] intervals) {
        Set<Integer> keys = data.keySet();
        List<Entry> entries = new ArrayList<>();
        List<Integer> keyList = new ArrayList<>(keys);
        Collections.sort(keyList);
        int i = 0;
        for (Integer k: keyList) {
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
