package com.eba.aislatech;

import android.app.Fragment;
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
 * Created by Chaitanya on 9/20/2015.
 *
 * This Fragment is used for Faq, it gets the Adapter and also get the Query results
 * from Parse and then sets the query results to the Adapter.
 */
public class FragmentFaq extends Fragment {

    private RecyclerView mRecyclerView;
    ArrayList<ModelFaq> results = new ArrayList<>();
    private RecyclerView.Adapter mAdapter = new AdapterFaq(results);
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentFaq() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.faq_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ParseQuery<ModelFaq> query = ParseQuery.getQuery(ModelFaq.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<ModelFaq>() {

            @Override
            public void done(List<ModelFaq> tasks, ParseException error) {
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