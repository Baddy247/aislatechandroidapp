package com.eba.aislatech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Chaitanya on 10/1/2015.
 *
 * This is deprecated, since I removed the settings activity entirely. It is too much of a headache.
 */
public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //SETTING TOOLBAR
        toolbar = (Toolbar) findViewById(R.id.tool_bar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
