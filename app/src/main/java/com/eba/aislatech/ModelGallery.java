package com.eba.aislatech;

import android.graphics.Bitmap;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Chaitanya on 9/20/2015.
 *
 * This is the encapsulation class for Alerts and thanks to Parse, this is used to connect to
 * Parse Class 'Alerts'
 *
 */
@ParseClassName("Gallery")
public class ModelGallery extends ParseObject {


    public ModelGallery() {
        // A default constructor is required.
    }

    public String getTitle() {
        return getString("Title");
    }


    public String getDescription() {
        return getString("Description");
    }


    public ParseFile getPhotoFile() {
        return getParseFile("Photo");
    }

    public ParseFile getPhotoThumbnail() {
        return getParseFile("PhotoThumbnail");
    }

}
