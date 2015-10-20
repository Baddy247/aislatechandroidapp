package com.eba.aislatech;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Chaitanya on 4/2/2015.
 *
 * This is easy, as it only sets the static data. And WebView just makes it easier to read than
 * just plain textView
 *
 */
public class FragmentHome extends Fragment {
    public FragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        String htmlText = " %s ";
        String myData = getResources().getString(R.string.home_description);

        WebView webView = (WebView) rootView.findViewById(R.id.homeText);
        webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");

        return rootView;
    }

}
