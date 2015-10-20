package com.eba.aislatech;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Chaitanya on 9/14/2015.
 *
 * This is the encapsulation class for Alerts and thanks to Parse, this is used to connect to
 * Parse Class 'Alerts'
 *
 */
@ParseClassName("Updates")
public class ModelUpdates extends ParseObject {

    public String getName() {
        return getString("Name");
    }

    public void setName(String title) {
        put("Name", title);
    }

    public String getDescription() {
        return getString("Description");
    }

    public String getNotificationLevel() {
        return getString("Notificationlvl");
    }

    public void setDescription(String title) {
        put("Description", title);
    }
}
