package com.eba.aislatech;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.parse.*;


import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 10/02/2015.
 *
 * This Array Adapter is used for Gallery, it displays the gallery thumbnails in a gridview. * 
 *
 */

public class AdapterGallery extends ArrayAdapter < ModelGallery > {
    private Context mContext;
    private List < ModelGallery > mTasks;

    public AdapterGallery(Context context, List < ModelGallery > objects) {
        super(context, R.layout.support_gallery_row, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.support_gallery_row, null);
        }
        ModelGallery task = mTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.gallery_title);
        descriptionView.setText(task.getTitle());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item);
        Ion.with(imageView)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .load(task.getPhotoThumbnail().getUrl());


        return convertView;
    }


}