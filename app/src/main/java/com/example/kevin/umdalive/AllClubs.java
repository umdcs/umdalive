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

public class AllClubs{
    ListView listView;
    UserInformation thisUser = new UserInformation();

    public void getDisplayClubInfo(String jsonResponse, String itemValue){
        JSONObject jsonParam = null;
        //Create JSONObject here
        jsonParam = new JSONObject();
        jsonParam.put("club", itemValue);
    }





        @Override
        public void onItemClick (AdapterView < ? > parent, View view,
        int position, long id){

        // ListView Clicked item index
        int itemPosition = position;

        // ListView Clicked item value
        String itemValue = (String) listView.getItemAtPosition(position);
        DisplayClub.setClubName(itemValue);
        //creates an intent that will become the view of the selected club
        //Gets the info of the club to display on the DisplayClub activity
        JSONObject jsonParam = null;
        //Create JSONObject
        jsonParam = new JSONObject();
        //adds the name of the club into the JSON object
        jsonParam.put("club", itemValue);
        //not sure why they used PUT here... shouldn't it be GET? Nothing is being added to the club/list of clubs at this point.
        //String jsonResponse = new HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "PUT", jsonParam.toString()).get();
        JSONObject object = new JSONObject(jsonResponse);
        //gets off the club info from the jsonResponse
        String clubFromServer = object.getString("club");
        String descriptionFromServer = object.getString("description");
        String userNameFromServer = object.getString("username");
        String keywordFromServer = object.getString("keywords");
        //sets the club parameters on the DisplayClub view
        DisplayClub.setClubName(clubFromServer);
        DisplayClub.setAdministrator(userNameFromServer);
        DisplayClub.setDescription(descriptionFromServer);
        DisplayClub.setKeywords(keywordFromServer);

        Log.d(clubFromServer, clubFromServer);
        Log.d(descriptionFromServer, descriptionFromServer);
        Log.d(userNameFromServer, userNameFromServer);
        Log.d(keywordFromServer, keywordFromServer);
        Log.d(jsonResponse, jsonResponse);

        // Show Alert (I think this is for debugging, idk why the user would need to see this)
        Toast.makeText(getApplicationContext(),
                "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                .show();
    }
    });
}
    }


