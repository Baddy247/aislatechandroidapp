package com.eba.aislatech;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Chaitanya on 9/20/2015.
 *
 * This is the encapsulation class for Alerts and thanks to Parse, this is used to connect to
 * Parse Class 'Alerts'
 *
 */
@ParseClassName("Faq")
public class ModelFaq extends ParseObject {

    public ModelFaq() {
    }

    public String getFaqQuestion() {
        return getString("Question");
    }


    public String getFaqAnswer() {
        return getString("Answer");
    }


}
