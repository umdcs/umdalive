package com.example.kevin.umdalive;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * This is the activity that displays the list of all clubs.
 */

public class AllClubs extends Activity {
    ListView listView;
    UserInformation this_user = new UserInformation();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_clubs);
        //this is the ListView for the all clubs activity listing all of the clubs
        listView = (ListView) findViewById(R.id.list2);
        //gets the list of clubs
        ArrayList<String> values = MainActivity.getUserInformation().getLocal_club_Names();


        try {
            /* Im not entirely sure but I think the way that this works is it uses a default Android list layout(simple_list_item_1)
             * to fill in the space of the listView with the contents of values.
             */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            //applies the adapter we just created to the ListView
            listView.setAdapter(adapter);
            /*
             * The following is what happens when a user selects something the ListView
             */


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    //PASS listView, parent, view, position, id INTO getClub FUNCTION IN MODEL USING THE PRESENTER

                    //creates an intent that will become the view of the selected club
                    Intent intent = new Intent(AllClubs.this, DisplayClub.class);
                    startActivity(intent);

                }

            });

        }catch (Exception e){}
        }

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


