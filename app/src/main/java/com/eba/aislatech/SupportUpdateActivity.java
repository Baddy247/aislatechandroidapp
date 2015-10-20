package com.eba.aislatech;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya on 09/1/2015
 *
 *
 * This Activity handles the Update activity, when clicked on the listview in the Fragment Update.
 * Also I tried hard to set the animation but since Google sucks when compared to Apple. The animation
 * transiton which can be done in newer versions of Android but it cannot be done for older versions.
 * I set partial aniamtion, it works but it is a bit weird.
 *
 */
public class SupportUpdateActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_updates);

        TextView description = (TextView) findViewById(R.id.detail_updates_description);
        TextView title = (TextView) findViewById(R.id.detail_updates_title);

        String listItemTitle = getIntent().getStringExtra("listItemTitle");
        String listItemDescription = getIntent().getStringExtra("listItemDescription");

        title.setText(listItemTitle);
        description.setText(listItemDescription);

        ViewCompat.setTransitionName(title, listItemTitle);


        //SETTING TOOLBAR
        toolbar = (Toolbar) findViewById(R.id.tool_bar_updates);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AIS - Latech");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
