package com.example.kevin.umdalive;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Luke on 12/1/2016.
 */

public class PostForClubActivity extends Activity {

    ListView listView;

    /**
     * method initializes this view.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_for_club_select);
        listView = (ListView) findViewById(R.id.list);

        //Calls the getLocal_club_Names from the main activity and assigns it into an arry
        //This doesnt exist anymore, will need to get the clubs on its own like AllClubs does

        //ArrayList<String> values = MainActivity.getUserInformation().getLocal_club_Names();
        //change null to equal the actual values.
        ArrayList<String> values = null;

        try {

            //Assigns the ArrayList Values to List view using the adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String  itemValue  = (String) listView.getItemAtPosition(position);
                    PostingActivity.setClub(itemValue);
                    Intent intent = new Intent(PostForClubActivity.this, PostingActivity.class);
                    startActivity(intent);

                    // Show Alert


                }

            });

        }catch (Exception e)
        {
        }}
//life cycle methods

    protected void onPause() {
        super.onPause();
    }
    protected void onResume() { //brings activity back to main screen.
        super.onResume();
    }
    protected void onStop() {
        super.onStop();
    }
    protected void onDestroy() {
        super.onDestroy();
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}