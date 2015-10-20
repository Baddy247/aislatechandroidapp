package com.eba.aislatech;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Chaitanya on 9/22/2015.
 *
 * This Activity handles the Support Event, when clicked on the CardView in the Fragment Events.
 * Also this Activity handles the FAB click listener and opens the calendar.
 *
 */
public class SupportEventActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_events);

        final String rvEventTitle = getIntent().getStringExtra("rvEventTitle");
        TextView eventTitle=(TextView)findViewById(R.id.events_details_title);
        eventTitle.setText(rvEventTitle);

        final String rvEventDesc = getIntent().getStringExtra("rvEventDescription");
        TextView eventDesc=(TextView)findViewById(R.id.events_details_description);
        eventDesc.setText(rvEventDesc);

        final String rvEventLocation = getIntent().getStringExtra("rvEventLocation");
        TextView eventLocation=(TextView)findViewById(R.id.detail_event_location);
        eventLocation.setText(rvEventLocation);

        final Date rvEventTimestamp = (Date) getIntent().getSerializableExtra("rvEventTimestamp");
        TextView eventTimestamp=(TextView)findViewById(R.id.detail_event_timestamp);
        eventTimestamp.setText(rvEventTimestamp.toString());

        Button buttonClose=(Button) findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, rvEventTitle);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, rvEventLocation);
                intent.putExtra(CalendarContract.Events.DESCRIPTION, rvEventDesc);
               // intent.putExtra(CalendarContract.Events.DTSTART, rvEventTimestamp);
                Calendar cal = Calendar.getInstance();
                cal.setTime(rvEventTimestamp);
                GregorianCalendar calDate = new GregorianCalendar(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE));
                //calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());


                startActivity(intent);

            }
        });


    }




}
