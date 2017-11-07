package getwreckt.cs2340.rattrack.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import java.util.List;

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
    private String xAxisInterval = "";
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
                    getGraphReady(lineChart);
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

    public void getGraphReady(LineChart lineChart) {
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

                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
