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
 * Created by Kevin on 11/14/2016.
 */

public class AllClubs extends Activity {
    ListView listView;
    UserInformation this_user = new UserInformation();
    Presenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        presenter = new Presenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_clubs);
        listView = (ListView) findViewById(R.id.list2);

        ArrayList<String> values = presenter.getClubNames();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // ListView Clicked item index
                    int itemPosition = position;
                    // ListView Clicked item value
                    String  itemValue  = (String) listView.getItemAtPosition(position);
                    DisplayClub.setClubName(itemValue);
                    Intent intent = new Intent(AllClubs.this, DisplayClub.class);
                    String jsonResponse = presenter.restGet(new String("getAllClubs"), new String(""));
                    presenter.getDisplayClubInfo(jsonResponse, itemValue);

                    JSONObject object = new JSONObject(jsonResponse);
                    String clubFromServer = object.getString("club");
                    String descriptionFromServer = object.getString("description");
                    String userNameFromServer = object.getString("username");
                    String keywordFromServer = object.getString("keywords");
                    DisplayClub.setClubName(clubFromServer);
                    DisplayClub.setAdministrator(userNameFromServer);
                    DisplayClub.setDescription(descriptionFromServer);
                    DisplayClub.setKeywords(keywordFromServer);

                    Log.d(clubFromServer, clubFromServer);
                    Log.d(descriptionFromServer, descriptionFromServer);
                    Log.d(userNameFromServer, userNameFromServer);
                    Log.d(keywordFromServer, keywordFromServer);
                    Log.d(jsonResponse, jsonResponse);


                    startActivity(intent);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }

            });
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