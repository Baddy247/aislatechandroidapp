package com.eba.aislatech;

/**
 * Created by c-mchennur on 10/28/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DFragment extends DialogFragment  {
    ListView mylist;
    ArrayList dateitems ;
    ArrayList descitems ;
    ArrayList locationitems ;
    ArrayList listitems ;



    public void setList(ArrayList list){
        listitems = list;
    }

    public void setDateList(ArrayList list){
        dateitems = list;
    }
    public void setLocList(ArrayList list){
        locationitems = list;
    }
    public void setDescList(ArrayList list){
        descitems = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popup_content, container,
                false);
       mylist = (ListView)  rootView.findViewById(R.id.poplist);
        TextView title = (TextView) rootView.findViewById(R.id.dialog_title);
        title.setText("Today's Events");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), SupportEventActivity.class);
                intent.putExtra("rvEventTitle", listitems.get(position).toString());
                intent.putExtra("rvEventDescription", descitems.get(position).toString());
                intent.putExtra("rvEventLocation", locationitems.get(position).toString());
                intent.putExtra("rvEventTimestamp", dateitems.get(position).toString());

                startActivity(intent);
            }
        });

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.popup_content_row, R.id.poprow_text, listitems);

        mylist.setAdapter(adapter);


    }

}
