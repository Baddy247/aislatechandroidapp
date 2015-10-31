package com.eba.aislatech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

/**
 * Created by Chaitanya on 9/14/2015.
 *
 * This Array Adapter is used for Updates, it displays the Updates title in a listview. It inflates
 * support_updates_row.xml and also sets the random colorsDrawable icon which is imported from the
 * 3rd party library.
 *
 */

public class AdapterUpdates extends ArrayAdapter < ModelUpdates > {
    private Context mContext;
    private List < ModelUpdates > mTasks;

    public AdapterUpdates(Context context, List < ModelUpdates > objects) {
        super(context, R.layout.support_updates_row, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    //test
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.support_updates_row, null);
        }
        ModelUpdates task = mTasks.get(position);

        //test2
        //Set image for list item
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getRandomColor();

        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .rect();
        TextDrawable drawable = builder.build(task.getNotificationLevel(), color1);

        ImageView image = (ImageView) convertView.findViewById(R.id.updates_image);
        image.setImageDrawable(drawable);


        TextView descriptionView = (TextView) convertView.findViewById(R.id.updates_title);

        descriptionView.setText(task.getName());


        return convertView;
    }


}