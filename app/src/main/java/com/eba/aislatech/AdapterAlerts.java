package com.eba.aislatech;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chaitanya on 9/21/2015.
 *
 * This RecyclerView Adapter is used for Alerts, it displays the alerts in cardview which is 
 * inflated from support_alerts_row.xml.
 *
 */

public class AdapterAlerts extends RecyclerView.Adapter < AdapterAlerts.DataObjectHolder > {

    private ArrayList < ModelAlerts > mDataset;

    public AdapterAlerts(ArrayList < ModelAlerts > myDataset) {
        mDataset = myDataset;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {

    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.support_alerts_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    //This method will get the data from the Model and set it in the textviews.
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.alertsTitle.setText(mDataset.get(position).getTitle());
        holder.alertsDescription.setText(mDataset.get(position).getDescription());

    }

    public void addItem(ModelAlerts dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
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

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        //Get Alerts Title and Description from the view
        TextView alertsTitle;
        TextView alertsDescription;

        public DataObjectHolder(View itemView) {
            super(itemView);
            alertsTitle = (TextView) itemView.findViewById(R.id.alerts_title);
            alertsDescription = (TextView) itemView.findViewById(R.id.alerts_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Any on click code goes well.

        }
    }


}