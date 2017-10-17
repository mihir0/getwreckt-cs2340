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

import java.util.List;

import getwreckt.cs2340.rattrack.R;
import getwreckt.cs2340.rattrack.model.*;

/**
 * Created by Patel on 10/11/2017.
 */

public class RatSightingListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private FloatingActionButton scrollTopBtn;
    private FloatingActionButton scrollBottomBtn;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sightings_list);
        View recyclerView = findViewById(R.id.sightings_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);


//        if (findViewById(R.id.dataitem_detail_container) != null) {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-w900dp).
//            // If this view is present, then the
//            // activity should be in two-pane mode.
//            mTwoPane = true;
//        }
        //Grab dialog widgets

        scrollTopBtn = (FloatingActionButton) findViewById(R.id.scroll_to_top);
        scrollBottomBtn = (FloatingActionButton) findViewById(R.id.scroll_to_bottom);

        scrollTopBtn.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               layoutManager.scrollToPosition(0);
           }
        });

        scrollBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.scrollToPosition(Model.ratSightings.size() - 1);
            }
        });

    }


    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(Model.ratSightings));

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<RatSighting> mValues;

        public SimpleItemRecyclerViewAdapter(List<RatSighting> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sightings_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getUniqueKey());
            holder.mContentView.setText(mValues.get(position).getCity());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent toSightingDetail = new Intent(context, SightingDetailActivity.class);

                    toSightingDetail.putExtra("SIGHTING", holder.mItem);

                    context.startActivity(toSightingDetail);
                }
            });

            /** set up when we optimize for tablets
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
//                        Bundle arguments = new Bundle();
//                        arguments.putInt(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
//                        DataItemDetailFragment fragment = new DataItemDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.dataitem_detail_container, fragment)
//                                .commit();
                    } else {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, DataItemDetailActivity.class);
//                        Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getId());
//                        intent.putExtra(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
//
//                        context.startActivity(intent);
                    }
                }
            });
             **/
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public RatSighting mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
