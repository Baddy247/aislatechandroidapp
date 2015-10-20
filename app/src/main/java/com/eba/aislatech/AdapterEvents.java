package com.eba.aislatech;

/**
 * Created by Chaitanya on 9/17/2015.
 *
 * This RecyclerView Adapter is used for Events, it displays the alerts in cardview which is 
 * inflated from support_events_row.xml. This adapter class also holds the onClick event for the 
 * Events.
 *
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterEvents extends RecyclerView.Adapter < AdapterEvents.DataObjectHolder > {

    private ArrayList < ModelEvents > mDataset;

    public AdapterEvents(ArrayList < ModelEvents > myDataset) {

        mDataset = myDataset;


    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        //this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.support_events_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.eventsTitle.setText(mDataset.get(position).getTitle());
        holder.eventsLocation.setText(mDataset.get(position).getLocation());
        holder.eventsTimestam.setText(mDataset.get(position).getTimestamp().toString());
        holder.eventsDescription.setText(mDataset.get(position).getDescription());

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(mDataset.get(position).getTimestamp());
        Calendar cal2 = Calendar.getInstance();
        cal2.getTime();

        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
            holder.eventsTitle.setBackgroundColor(Color.GREEN);
        }

        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR)) {
            holder.eventsCardview.setVisibility(View.GONE);
        }
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) {
            holder.eventsCardview.setVisibility(View.GONE);
        }

    }

    public void addItem(ModelEvents dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        //  notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        if (mDataset == null) {
            return 0;
        }
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView eventsTitle;
        TextView eventsLocation;
        TextView eventsTimestam;
        TextView eventsDescription;
        CardView eventsCardview;

        //Gets all the views from the support row of events.

        public DataObjectHolder(View itemView) {
            super(itemView);
            eventsTitle = (TextView) itemView.findViewById(R.id.events_title);
            eventsLocation = (TextView) itemView.findViewById(R.id.events_location);
            eventsTimestam = (TextView) itemView.findViewById(R.id.events_datetime);
            eventsDescription = (TextView) itemView.findViewById(R.id.events_description);
            eventsCardview = (CardView) itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(this);
        }

        //This click event starts a new Support event activity and supplies the extra data from Parse.
        @Override
        public void onClick(View v) {
            Context context;
            context = v.getContext();

            Intent intent = new Intent(context, SupportEventActivity.class);
            intent.putExtra("rvEventTitle", mDataset.get(getAdapterPosition()).getTitle());
            intent.putExtra("rvEventDescription", mDataset.get(getAdapterPosition()).getDescription());
            intent.putExtra("rvEventLocation", mDataset.get(getAdapterPosition()).getLocation());
            intent.putExtra("rvEventTimestamp", mDataset.get(getAdapterPosition()).getTimestamp());

            context.startActivity(intent);
        }
    }

}