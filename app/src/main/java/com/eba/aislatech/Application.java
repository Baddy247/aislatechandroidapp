package com.eba.aislatech;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Chaitanya on 9/17/2015.
 * This application class is app wide class and this is used to set the Parse credentials.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Parse setup

        ParseObject.registerSubclass(ModelUpdates.class);
        ParseObject.registerSubclass(ModelEvents.class);
        ParseObject.registerSubclass(ModelGallery.class);
        ParseObject.registerSubclass(ModelFaq.class);
        ParseObject.registerSubclass(ModelAlerts.class);
        // Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(this, "c2RNEBMH7cEcXB26Ql5uC5kVgMmqzfCRksEOLU4y", "2JrOTu2D7VeTZ2EyyMIBF7EOKyNlFqwHh1qtA3XI");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);


    }
}