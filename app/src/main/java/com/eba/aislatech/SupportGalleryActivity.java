package com.eba.aislatech;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Chaitanya on 10/3/2015.*
 *
 *
 * This Activity handles the Gallery Event, when clicked on the GridView in the Fragment Gallery.
 * Also this Activity handles the FAB click listener and downloads the pic.
 *
 */
public class SupportGalleryActivity extends AppCompatActivity  {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_gallery);

        TextView description = (TextView) findViewById(R.id.detail_gallery_description);
        TextView title = (TextView) findViewById(R.id.detail_gallery_title);
        final ImageView imageView = (ImageView) findViewById(R.id.gallery_image);

        final String galleryItemTitle = getIntent().getStringExtra("galleryItemTitle");
        String galleryItemDescription = getIntent().getStringExtra("galleryItemDescription");
        final String galleryItemUrl = getIntent().getStringExtra("galleryItemPhotoURL");

        title.setText(galleryItemTitle);
        description.setText(galleryItemDescription);

        Ion.with(imageView)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .load(galleryItemUrl);


        //SETTING TOOLBAR
        toolbar = (Toolbar) findViewById(R.id.tool_bar_gallery);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");

        File sdCard = Environment.getExternalStorageDirectory();
        final File dir = new File (sdCard.getAbsolutePath() + "/aislatech");
        dir.mkdirs();

        final ProgressBar progressBar = new ProgressBar(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_download);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ion.with(getApplicationContext())
                        .load(galleryItemUrl)
                        .progressBar(progressBar)
                        .write(new File(dir, galleryItemTitle + ".jpg"))
                        .setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File file) {
                                Toast.makeText(getApplicationContext(), "Downloaded Image",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                Toast.makeText(getApplicationContext(), "Downloading Image..",
                        Toast.LENGTH_SHORT).show();

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
