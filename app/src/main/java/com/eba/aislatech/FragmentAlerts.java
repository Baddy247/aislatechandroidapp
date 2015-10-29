package com.eba.aislatech;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 9/21/2015.
 *
 * This Fragment is used for Alerts, it gets the Adapter and also get the Query results
 * from Parse and then sets the query results to the Adapter.
 */
public class FragmentAlerts extends Fragment {

    private RecyclerView mRecyclerView;
    ArrayList < ModelAlerts > results = new ArrayList < > ();
    private RecyclerView.Adapter mAdapter = new AdapterAlerts(results);
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    public FragmentAlerts() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_alerts, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.alerts_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ParseQuery < ModelAlerts > query = ParseQuery.getQuery(ModelAlerts.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.whereEqualTo("UniqueID", 1);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback < ModelAlerts > () {

            @Override
            public void done(List < ModelAlerts > tasks, ParseException error) {
                if (tasks != null) {
                    results.clear();
                    results.addAll(tasks);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }
        });


        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }


}