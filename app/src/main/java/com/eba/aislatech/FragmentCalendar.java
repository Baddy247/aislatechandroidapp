package com.eba.aislatech;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by c-mchennur on 10/27/2015.
 */
public class FragmentCalendar extends Fragment {

    private FragmentActivity myContext;
    private CaldroidFragment caldroidFragment;
    ArrayList<Date> dates = new ArrayList<Date>();
    ArrayList titles = new ArrayList();
    ArrayList locations = new ArrayList();
    ArrayList desc = new ArrayList();
    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH");
    final SimpleDateFormat formatter1 = new SimpleDateFormat("dd");
    public static final String ARG_PAGE = "ARG_PAGE";
//    ArrayList < ModelEvents > results = new ArrayList < > ();

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public static FragmentCalendar newInstance(int page) {
        FragmentCalendar fragmentFirst = new FragmentCalendar();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.calendar_event, container, false);
        int page = getArguments().getInt("ARG_PAGE", 0);
        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
        caldroidFragment.setArguments(args);

        setCustomResourceForDates();

        FragmentManager fragManager = myContext.getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction t = fragManager.beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {


            @Override
            public void onSelectDate(Date date, View view) {
               ArrayList<Date> indexList = new ArrayList<Date>();
                ArrayList<String> eventList = new ArrayList<>();
                ArrayList<String> descList = new ArrayList<>();
                ArrayList<String> locationList = new ArrayList<>();
                    for (int i = 0; i < dates.size(); i++) {
                        if (formatter.format(dates.get(i)).contains(formatter1.format(date))) {
                            indexList.add(dates.get(i));
                            eventList.add(titles.get(i).toString());
                            descList.add(desc.get(i).toString());
                            locationList.add(locations.get(i).toString());
                        }
                    }

                Log.i("Dates Array: ", dates.toString());
                if(indexList.size()==1) {
//                    for(int k = 0; k< indexList.size(); k++){
                    int k = dates.indexOf(indexList.get(0));
                    Intent intent = new Intent(getActivity(), SupportEventActivity.class);
                    intent.putExtra("rvEventTitle", titles.get(k).toString());
                    intent.putExtra("rvEventDescription", desc.get(k).toString());
                    intent.putExtra("rvEventLocation", locations.get(k).toString());
                    intent.putExtra("rvEventTimestamp", dates.get(k).toString());

                    startActivity(intent);
                }else if(indexList.size()>1){

                    displayPopupWindow(view, indexList, eventList, descList, locationList);

                }

            }

            @Override
            public void onChangeMonth(int month, int year) {
//                String text = "month: " + month + " year: " + year;
//                Toast.makeText(getApplicationContext(), text,
//                        Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        return rootView;
    }

    private void displayPopupWindow(View v, ArrayList<Date> dateList ,ArrayList<String> list, ArrayList<String> desclist, ArrayList<String> loclist) {

       FragmentManager fm = getFragmentManager();
        DFragment dFragment = new DFragment();
        dFragment.setList(list);
        dFragment.setDateList(dateList);
        dFragment.setDescList(desclist);
        dFragment.setLocList(loclist);
        dFragment.show(fm,"Dialog");
    }

    private void setCustomResourceForDates() {
        final ParseQuery< ModelEvents > query = ParseQuery.getQuery(ModelEvents.class);
        query.whereEqualTo("UniqueID", 1);
        query.orderByAscending("Timestamp");
        List<ModelEvents> activityList;
        try {
            activityList = query.find();
            for (    ModelEvents task : activityList){
                dates.add(task.getTimestamp());
                desc.add(task.getDescription());
                titles.add(task.getTitle());
                locations.add(task.getLocation());
                caldroidFragment.setBackgroundResourceForDate(R.color.blue, task.getTimestamp());
                caldroidFragment.setTextColorForDate(R.color.white, task.getTimestamp());
//                results.add(task);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }

    }


}
