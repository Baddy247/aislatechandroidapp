package com.eba.aislatech;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chaitanya on 9/20/2015.
 *
 * This Fragment is easiest and sets Static data, this might have to be changed at a later stage.
 *
 */
public class FragmentContact extends Fragment {
    public CardView phoneCard1;
    public CardView phoneCard2;
    public CardView phoneCard3;
    public CardView phoneCard4;
    public CardView phoneCard5;
    public CardView email;

    public FragmentContact() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        phoneCard1 = (CardView) rootView.findViewById(R.id.contact_phone1);

        phoneCard1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:(818)731-1520"));
            startActivity(intent);
        }
        });

        phoneCard2 = (CardView) rootView.findViewById(R.id.contact_phone2);

        phoneCard2.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:(617)416-3033"));
            startActivity(intent);
        }
        });

        phoneCard3 = (CardView) rootView.findViewById(R.id.contact_phone3);

        phoneCard3.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:(318)497-0865"));
            startActivity(intent);
        }
        });

        phoneCard4 = (CardView) rootView.findViewById(R.id.contact_phone4);

        phoneCard4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:(510)468-7548"));
            startActivity(intent);
        }
        });

        phoneCard5 = (CardView) rootView.findViewById(R.id.contact_phone5);

        phoneCard5.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:(435)647-6100"));
            startActivity(intent);
        }
        });

        email = (CardView) rootView.findViewById(R.id.contact_email_cardview);

        email.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {
                    "ais@latech.edu"
            });
            startActivity(intent);
        }
        });


        return rootView;
    }

}