package com.eba.aislatech;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 9/20/2015.
 *
 * This Fragment is used for Gallery, it gets the Adapter and also get the Query results
 * from Parse and then sets the query results to the Adapter. Also this Fragment since it is
 * handled by the GridView, this fragment holds the onClickListner.
 */
public class FragmentGallery extends Fragment {

    private AdapterGallery mAdapter;
    private GridView mGridView;
    public FragmentGallery() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        mAdapter = new AdapterGallery(this.getActivity(), new ArrayList<ModelGallery>());

        final GridView mListView = (GridView) rootView.findViewById(R.id.gridview_gallery);
        ParseQuery<ModelGallery> query = ParseQuery.getQuery(ModelGallery.class);
        query.findInBackground(new FindCallback<ModelGallery>() {

            @Override
            public void done(List<ModelGallery> tasks, ParseException error) {
                if (tasks != null) {
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });

        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                ModelGallery model = (ModelGallery) mListView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), SupportGalleryActivity.class);
                intent.putExtra("galleryItemTitle", model.getTitle());
                intent.putExtra("galleryItemDescription", model.getDescription());
                intent.putExtra("galleryItemPhotoURL", model.getPhotoFile().getUrl());
                startActivity(intent);

            }
        });

        return rootView;
    }


}