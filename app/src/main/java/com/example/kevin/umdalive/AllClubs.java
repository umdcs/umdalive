package com.example.kevin.umdalive;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kevin on 11/14/2016.
 */

public class AllClubs extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.all_clubs);
        ArrayList<String> values = MainActivity.getUserInformation().getLocal_club_Names();
        //String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
         //       "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
           //     "Linux", "OS/2" };

        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            setListAdapter(adapter);
        }catch (Exception e)
        {

        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
    }


