package com.eba.aislatech;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by Chaitanya long time back in a galaxy far far away.
 *
 * The main Activity, since everything else is fragment except for a few supporting activities.
 * This activity sets the Navigation Drawer and also handles the code for the Fragments.
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String navDrawer_Titles[];
    View navDrawer_header;
    ListView navDrawerList;
    DrawerLayout navDrawerLayout;
    ActionBarDrawerToggle navDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SETTING TOOLBAR
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        //SETTING NAVIGATION DRAWER
        navDrawer_header = View.inflate(this, R.layout.support_navdrawer_header, null);
        navDrawer_Titles = getResources().getStringArray(R.array.Titles);
        navDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navDrawerList = (ListView) findViewById(R.id.navDrawerList);
        navDrawerList.addHeaderView(navDrawer_header);
        navDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.support_navdrawer_itemrow, navDrawer_Titles));
        navDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        };
        navDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        navDrawerLayout.setDrawerListener(navDrawerToggle);
        navDrawerToggle.syncState();
        if (savedInstanceState == null) {
            displayView(0);
        }

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

//        if (id == R.id.action_settings) {
//            Intent i=new Intent(this, SettingsActivity.class);
//            startActivity(i);
//
//            return true;
//        }
        if (id == R.id.action_home) {
            displayView(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentHome();
                getSupportActionBar().setTitle("AIS - Latech");
                break;
            case 1:
                fragment = new FragmentHome();
                getSupportActionBar().setTitle("AIS - Latech");
                break;
            case 2:
                fragment = new FragmentUpdates();
                getSupportActionBar().setTitle(navDrawer_Titles[1]);
                break;
            case 3:
                fragment = new FragmentEvents();
                getSupportActionBar().setTitle(navDrawer_Titles[2]);
                break;
            case 4:
                fragment = new FragmentGallery();
                getSupportActionBar().setTitle(navDrawer_Titles[3]);
                break;
            case 5:
                fragment = new FragmentFaq();
                getSupportActionBar().setTitle(navDrawer_Titles[4]);
                break;
            case 6:
                fragment = new FragmentContact();
                getSupportActionBar().setTitle(navDrawer_Titles[5]);
                break;
            case 7:
                fragment = new FragmentAlerts();
                getSupportActionBar().setTitle(navDrawer_Titles[6]);
                break;
            case 8:
                fragment = new FragmentGuidebook();
                getSupportActionBar().setTitle(navDrawer_Titles[7]);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            navDrawerList.setItemChecked(position, true);
            navDrawerList.setSelection(position);
            navDrawerLayout.closeDrawer(navDrawerList);

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
            navDrawerLayout.closeDrawer(navDrawerList);
        }
    }

}
