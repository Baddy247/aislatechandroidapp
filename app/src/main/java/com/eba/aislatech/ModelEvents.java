package com.eba.aislatech;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by Chaitanya on 9/17/2015.
 *
 * This is the encapsulation class for Alerts and thanks to Parse, this is used to connect to
 * Parse Class 'Alerts'
 *
 */
@ParseClassName("Events")
public class ModelEvents extends ParseObject {
    private String eventsTitle;
    private String eventsLocation;
    private String eventsTimestamp;

    public ModelEvents() {
    }

    ModelEvents(String text1, String text2, String text3) {
        eventsTitle = text1;
        eventsLocation = text2;
        eventsTimestamp = text3;
    }

    public String getTitle() {
        return getString("Name");
    }

    public void setTitle(String mText1) {
        this.eventsTitle = mText1;
    }

    public String getLocation() {
        return getString("Location");
    }

    public String getDescription() {
        return getString("Description");
    }

    public void setLocation(String mText2) {
        this.eventsLocation = mText2;
    }

    public Date getTimestamp() {
        return getDate("Timestamp");
    }

    public void setTimestamp(String mText2) {
        this.eventsTimestamp = mText2;
    }
}
