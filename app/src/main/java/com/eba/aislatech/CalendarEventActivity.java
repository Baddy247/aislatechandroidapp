package com.eba.aislatech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mithun on 10/17/2015.
 * This is to create a custom calendar view for Events. Uncomment the bottom code for more customization.
 */
public class CalendarEventActivity extends AppCompatActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    ArrayList dates = new ArrayList();
    ArrayList titles = new ArrayList();
    ArrayList locations = new ArrayList();
    ArrayList desc = new ArrayList();
    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");




    private void setCustomResourceForDates() {
        final ParseQuery< ModelEvents > query = ParseQuery.getQuery(ModelEvents.class);
        List<ModelEvents> activityList;
        try {
            activityList = query.find();
            for (    ModelEvents task : activityList){
                dates.add(formatter.format(task.getTimestamp()));
                desc.add(task.getDescription());
                titles.add(task.getTitle());
                locations.add(task.getLocation());
                caldroidFragment.setBackgroundResourceForDate(R.color.blue, task.getTimestamp());
                caldroidFragment.setTextColorForDate(R.color.white, task.getTimestamp());
            }
        }catch (ParseException e){
                e.printStackTrace();
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event);
        final TextView text1 = (TextView)findViewById(R.id.tab3);
        final TextView text2 = (TextView)findViewById(R.id.tab4);
//        text2.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryDark));

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_events);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        mRecyclerView.setLayoutManager(mLayoutManager);
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChangeMonth(int month, int year) {
//                String text = "month: " + month + " year: " + year;
//                Toast.makeText(getApplicationContext(), text,
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {

                int k = dates.indexOf(formatter.format(date));
                if(k>-1) {
                    Intent intent = new Intent(CalendarEventActivity.this, SupportEventActivity.class);
                    intent.putExtra("rvEventTitle", titles.get(k).toString());
                    intent.putExtra("rvEventDescription", desc.get(k).toString());
                    intent.putExtra("rvEventLocation", locations.get(k).toString());
                    intent.putExtra("rvEventTimestamp", date);

                    startActivity(intent);
                }else{

                }
//                caldroidFragment.refreshView();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

//        final TextView textView = (TextView) findViewById(R.id.textview);

//        final Button customizeButton = (Button) findViewById(R.id.customize_button);

        // Customize the calendar
//        customizeButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (undo) {
//                    customizeButton.setText(getString(R.string.customize));
//                    textView.setText("");
//
//                    // Reset calendar
//                    caldroidFragment.clearDisableDates();
//                    caldroidFragment.clearSelectedDates();
//                    caldroidFragment.setMinDate(null);
//                    caldroidFragment.setMaxDate(null);
//                    caldroidFragment.setShowNavigationArrows(true);
//                    caldroidFragment.setEnableSwipe(true);
//                    caldroidFragment.refreshView();
//                    undo = false;
//                    return;
//                }
//
//                // Else
//                undo = true;
//                customizeButton.setText(getString(R.string.undo));
//                Calendar cal = Calendar.getInstance();
//
//                // Min date is last 7 days
//                cal.add(Calendar.DATE, -7);
//                Date minDate = cal.getTime();
//
//                // Max date is next 7 days
//                cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 14);
//                Date maxDate = cal.getTime();
//
//                // Set selected dates
//                // From Date
//                cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 2);
//                Date fromDate = cal.getTime();
//
//                // To Date
//                cal = Calendar.getInstance();
//                cal.add(Calendar.DATE, 3);
//                Date toDate = cal.getTime();
//
//                // Set disabled dates
//                ArrayList<Date> disabledDates = new ArrayList<Date>();
//                for (int i = 5; i < 8; i++) {
//                    cal = Calendar.getInstance();
//                    cal.add(Calendar.DATE, i);
//                    disabledDates.add(cal.getTime());
//                }
//
//                // Customize
//                caldroidFragment.setMinDate(minDate);
//                caldroidFragment.setMaxDate(maxDate);
//                caldroidFragment.setDisableDates(disabledDates);
//                caldroidFragment.setSelectedDates(fromDate, toDate);
//                caldroidFragment.setShowNavigationArrows(false);
//                caldroidFragment.setEnableSwipe(false);
//
//                caldroidFragment.refreshView();
//
//                // Move to date
//                // cal = Calendar.getInstance();
//                // cal.add(Calendar.MONTH, 12);
//                // caldroidFragment.moveToDate(cal.getTime());
//
//                String text = "Today: " + formatter.format(new Date()) + "\n";
//                text += "Min Date: " + formatter.format(minDate) + "\n";
//                text += "Max Date: " + formatter.format(maxDate) + "\n";
//                text += "Select From Date: " + formatter.format(fromDate)
//                        + "\n";
//                text += "Select To Date: " + formatter.format(toDate) + "\n";
//                for (Date date : disabledDates) {
//                    text += "Disabled Date: " + formatter.format(date) + "\n";
//                }
//
//                textView.setText(text);
//            }
//        });
//
//        Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);
//
//        final Bundle state = savedInstanceState;
//        showDialogButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // Setup caldroid to use as dialog
//                CaldroidFragment dialogCaldroidFragment = new CaldroidFragment();
//                dialogCaldroidFragment.setCaldroidListener(listener);
//
//                // If activity is recovered from rotation
//                final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
//                if (state != null) {
//                    dialogCaldroidFragment.restoreDialogStatesFromKey(
//                            getSupportFragmentManager(), state,
//                            "DIALOG_CALDROID_SAVED_STATE", dialogTag);
//                    Bundle args = dialogCaldroidFragment.getArguments();
//                    if (args == null) {
//                        args = new Bundle();
//                        dialogCaldroidFragment.setArguments(args);
//                    }
//                } else {
//                    // Setup arguments
//                    Bundle bundle = new Bundle();
//                    // Setup dialogTitle
//                    dialogCaldroidFragment.setArguments(bundle);
//                }
//
//                dialogCaldroidFragment.show(getSupportFragmentManager(),
//                        dialogTag);
//            }
//        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // text1.setTextColor(getResources().getColor(R.color.ColorPrimaryDark));
                text1.setBackgroundColor(getResources().getColor(R.color.ColorPrimaryDark));
                text2.setBackgroundColor(getResources().getColor(R.color.background_material_light));
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
        if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
