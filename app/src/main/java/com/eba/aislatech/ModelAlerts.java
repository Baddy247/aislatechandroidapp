package com.eba.aislatech;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Chaitanya on 9/21/2015.
 *
 * This is the encapsulation class for Alerts and thanks to Parse, this is used to connect to
 * Parse Class 'Alerts'
 *
 */
@ParseClassName("Alerts")
public class ModelAlerts extends ParseObject {
    private String eventsTitle;
    private String eventsLocation;
    private String eventsTimestamp;

    public ModelAlerts() {
    }

	 public String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
        String ts = df.format(getCreatedAt());
        return ts;
    }
    public String getTitle() {
        return getString("Name");
    }

    public String getDescription() {
        return getString("Description");
    }


}

