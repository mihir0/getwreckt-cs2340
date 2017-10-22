package getwreckt.cs2340.rattrack.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;

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
    private int position;
    private List<RatSighting> valuesToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = 0;
        valuesToShow = new ArrayList<>();
        setContentView(R.layout.activity_sightings_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sightings_list);

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(FirebaseDatabase.getInstance().getReference()));

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        public SimpleItemRecyclerViewAdapter(final DatabaseReference ref) {
            ref.child("ratsightings").addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot snapshot) {
                    Model.ratSightings.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        RatSighting ratSighting = postSnapshot.getValue(RatSighting.class);
                        Model.ratSightings.add(ratSighting);
                    }
                    ref.child("ratsightings").keepSynced(true);
                    valuesToShow = Model.ratSightings.subList((position / 10) * 10, (position / 10) * 10 + 10);
                    notifyDataSetChanged();


                    scrollTopBtn = (FloatingActionButton) findViewById(R.id.scroll_to_top);
                    scrollBottomBtn = (FloatingActionButton) findViewById(R.id.scroll_to_bottom);

                    scrollTopBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = 0;
                            valuesToShow = Model.ratSightings.subList((position / 10) * 10, (position / 10) * 10 + 10);
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(0);
                        }
                    });

                    scrollBottomBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = Model.ratSightings.size() - 1;
                            valuesToShow = Model.ratSightings.subList((position / 10) * 10, (position / 10) * 10 + 10);
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(Model.ratSightings.size() - 1);
                        }
                    });

                    scrollUpBtn = (FloatingActionButton) findViewById(R.id.scroll_up);
                    scrollDownBtn = (FloatingActionButton) findViewById(R.id.scroll_down);

                    scrollUpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = position - 10 < 0 ?
                                    0 :
                                    position - 10;
                            valuesToShow = Model.ratSightings.subList((position / 10) * 10, (position / 10) * 10 + 10);
                            notifyDataSetChanged();
                            layoutManager.scrollToPosition(position);
                        }
                    });

                    scrollDownBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            position = position + 10 > Model.ratSightings.size() - 1 ?
                                    Model.ratSightings.size() - 1 :
                                    position + 10;
                            valuesToShow = Model.ratSightings.subList((position / 10) * 10, (position / 10) * 10 + 10);
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
            holder.mContentView.setText(valuesToShow.get(position).getCity());

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

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public RatSighting mItem;

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

