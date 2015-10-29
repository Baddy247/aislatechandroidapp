package com.eba.aislatech;

<<<<<<< HEAD
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
=======

import android.os.Bundle;
import android.support.v4.app.Fragment;
>>>>>>> refs/remotes/ebahub/master
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 9/17/2015.
 *
 * This Fragment is used for Events, it gets the Adapter and also get the Query results
 * from Parse and then sets the query results to the Adapter.
 */
public class FragmentEvents extends Fragment {

    private RecyclerView mRecyclerView;
    ArrayList < ModelEvents > results = new ArrayList < > ();
    private RecyclerView.Adapter mAdapter = new AdapterEvents(results);
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";


    public FragmentEvents() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        final TextView text2 = (TextView)rootView.findViewById(R.id.tab2);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ParseQuery < ModelEvents > query = ParseQuery.getQuery(ModelEvents.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.whereEqualTo("UniqueID", 1);
        query.orderByAscending("Timestamp");
        query.findInBackground(new FindCallback < ModelEvents > () {

            @Override
            public void done(List < ModelEvents > tasks, ParseException error) {
                if (tasks != null) {
                    // query.clearCachedResult();
                    results.clear();
                    results.addAll(tasks);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        });


        mRecyclerView.setAdapter(mAdapter);



        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryDark));
                //text2.setTextColor(getResources().getColor(R.color.ColorPrimaryDark));
                Intent intent = new Intent(getActivity(), CalendarEventActivity.class);
                startActivity(intent);
                text2.setBackgroundColor(getResources().getColor(R.color.background_material_light));
            }
        });

        return rootView;
    }


    }
