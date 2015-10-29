package com.eba.aislatech;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Chaitanya on 9/20/2015.
 *
 * This Fragment is also easy and sets the webView. It obtains the PDF from online LATECH server.
 * And thanks to Google some easy readability for PDF, this was pretty easy too.
 *
 */
public class FragmentGuidebook extends Fragment {
    public FragmentGuidebook() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_guidebook, container, false);

        WebView mWebView = new WebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + "http://orgs.latech.edu/ais/aspirants/guidebook.pdf");

        return mWebView;
    }

}