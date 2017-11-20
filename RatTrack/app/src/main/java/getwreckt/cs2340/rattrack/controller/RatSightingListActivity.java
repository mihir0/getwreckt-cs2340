package getwreckt.cs2340.rattrack.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.RatSighting;
import getwreckt.cs2340.rattrack.model.SightingManager;

/**
 * Created by Patel on 10/11/2017.
 */

public class RatSightingListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private FloatingActionButton scrollTopBtn;
    private FloatingActionButton scrollBottomBtn;
    private FloatingActionButton scrollUpBtn;
    private FloatingActionButton scrollDownBtn;
    private LinearLayoutManager layoutManager;
    private static int position = 0;
    private List<RatSighting> valuesToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valuesToShow = new ArrayList<>();
        setContentView(R.layout.activity_sightings_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sightings_list);

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(FirebaseDatabase.getInstance().getReference()));

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

    }

    private int endIndex(int position) {
        return ((position + 10) > SightingManager.ratSightings.size()) ?
                SightingManager.ratSightings.size() :
                (position + 10);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        /**
         * Parameterized Constructor
         * @param ref reference for database
         */
        public SimpleItemRecyclerViewAdapter(final DatabaseReference ref) {
            ref.child("ratsightings").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    SightingManager.ratSightings.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Log.e("List", "" + postSnapshot.getValue(RatSighting.class));
                        RatSighting ratSighting = postSnapshot.getValue(RatSighting.class);
                        SightingManager.ratSightings.add(ratSighting);
                    }
                    ref.child("ratsightings").keepSynced(true);
                    valuesToShow = SightingManager.ratSightings.subList(position, endIndex(position));
                    notifyDataSetChanged();


                    scrollTopBtn = (FloatingActionButton) findViewById(R.id.scroll_to_top);
                    scrollBottomBtn = (FloatingActionButton) findViewById(R.id.scroll_to_bottom);

                    scrollTopBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = 0;
                            valuesToShow = SightingManager.ratSightings.subList(position, endIndex(position));
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(position);
                        }
                    });
                    
                    scrollBottomBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = (SightingManager.ratSightings.size() / 10) * 10;
                            valuesToShow = SightingManager.ratSightings.subList(position, endIndex(position));
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(position);
                        }
                    });

                    scrollUpBtn = (FloatingActionButton) findViewById(R.id.scroll_up);
                    scrollDownBtn = (FloatingActionButton) findViewById(R.id.scroll_down);

                    scrollUpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = ((position - 10) < 0) ?
                                    0 :
                                    (position - 10);
                            valuesToShow = SightingManager.ratSightings.subList(position, endIndex(position));
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(position);
                        }
                    });

                    scrollDownBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = ((position + 10) > SightingManager.ratSightings.size()) ?
                                    position :
                                    (position + 10);
                            valuesToShow = SightingManager.ratSightings.subList(position, endIndex(position));
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(position);
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sightings_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = valuesToShow.get(position);
            holder.mIdView.setText(valuesToShow.get(position).getUniqueKey());
            holder.mContentView.setText(valuesToShow.get(position).getLocation().getCity());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent toSightingDetail = new Intent(context, SightingDetailActivity.class);

                    toSightingDetail.putExtra("SIGHTING", holder.mItem);
                    context.startActivity(toSightingDetail);
                }
            });

        }

        @Override
        public int getItemCount() {
            return valuesToShow.size();
        }

        /**
         * Inner class to support view holder
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public RatSighting mItem;

            /**
             * Parametrized Constructor
             * @param view view to display
             */
            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.textViewContent);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}

