package com.eba.aislatech;

import android.app.ActivityOptions;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Fade;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Chaitanya on 9/14/2015.
 *
 * This Fragment is used for Updates, it gets the Adapter and also get the Query results
 * from Parse and then sets the query results to the Adapter. Also this Fragment since it is
 * handled by the ListView, this fragment holds the onClickListner.
 */
public class FragmentUpdates extends Fragment {

    AdapterUpdates mAdapter;

    public FragmentUpdates() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_updates, container, false);

        mAdapter = new AdapterUpdates(this.getActivity(), new ArrayList < ModelUpdates > ());

        final ListView mListView = (ListView) rootView.findViewById(R.id.updates_list);
        ParseQuery < ModelUpdates > query = ParseQuery.getQuery(ModelUpdates.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.whereEqualTo("UniqueID", 1);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback < ModelUpdates > () {

            @Override
            public void done(List < ModelUpdates > tasks, ParseException error) {
                if (tasks != null) {
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView <? > arg0, View arg1,
                                    int position, long arg3) {


                ModelUpdates model = (ModelUpdates) mListView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), SupportUpdateActivity.class);
                intent.putExtra("listItemTitle", model.getName());
                intent.putExtra("listItemDescription", model.getDescription());
                intent.putExtra("view", "R.layout.detail_updates");

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(), new Pair < View, String > (rootView.findViewById(R.id.updates_title), model.getName())


                );

                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());


            }
        });

        return rootView;
    }


}