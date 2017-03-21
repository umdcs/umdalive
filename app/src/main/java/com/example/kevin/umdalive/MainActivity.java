package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.AsyncTask;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;

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
import java.util.Collections;
import java.util.concurrent.ExecutionException;

/*
Lots of server connections
fix that
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variable name consistency
    private static UserInformation this_user = new UserInformation();


    UserInformation local_user_info;
    EditText posts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtain the user info from server
        getUser(); // calls server
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Understand this fab better
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //this is a sidebar thing check layout for better idea
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle); //deprecated thing
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        posts = (EditText)findViewById(R.id.mainPosts);
        posts.setMaxLines(20);
    }

/*
 * consistancy with curly braces and one line functions
 */


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    // starts display CreateClub Activity
    public void displayClubs(String clubNames) {
        Intent intent = new Intent(this, AllClubs.class);
        startActivity(intent);
    }

    // starts display Cluba for post Activity
    public void displayClubsForPost(String clubNames)
    {
        Intent intent = new Intent(this, PostForClubActivity.class);
        startActivity(intent);
    }
    //Will stay here in the model
    public void getUser() {
        try {

            //make userData equal getUserData from RestModel
            String userData = null;
            //string is turned into a jsonobject
            JSONObject user = new JSONObject(userData);
            ArrayList<ClubInformation> list = new ArrayList<ClubInformation>();
            JSONArray jArray = user.getJSONArray("clubs");

            if (jArray != null) {
                int len = jArray.length();
                for (int i=0;i<len;i++){
                    JSONObject clubObject = jArray.getJSONObject(i);
                    //create new club object from server data
                    ClubInformation tempClub = new ClubInformation(clubObject.get("clubname").toString(),
                            clubObject.get("username").toString(),
                            clubObject.get("keywords").toString(),
                            clubObject.get("description").toString(),
                            clubObject.get("post").toString());
                    Log.d("club name: ", clubObject.get("clubname").toString());
                    //add new club object to array
                    list.add(tempClub);
                }


            }
            this_user = new UserInformation(user.getString("name"),
                    user.getString("major"),
                    user.getString("email"),
                    user.getString("graduationDate"),
                    list);

            Log.d("userData", userData);
            //will obtain json string from textview and take value out from string

        }
        catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    //will stay in model
    public static UserInformation getUserInformation() {
        return this_user;
    }

    /*
     * function to get user data and update the UI with their info
     */
    //will move to view
    public void updateUser(String str1) {
        TextView emailView = (TextView) findViewById(R.id.userEmail);
        emailView.setText(this_user.getEmail());

        //gets the users name and sets menu to their name
        TextView userNameView = (TextView) findViewById(R.id.userName);
        userNameView.setText(this_user.getLocalUsername());
    }


    /*
    onclick for refreshing most recent posts

    MVP
     */
    public void refreshPosts(View view){
        //make mostRecentPosts equal the results from mostRecentPostsGET() in RestModel
        String mostRecentPosts = null;

        ArrayList<String> list = new ArrayList<String>();
        JSONObject object = null;
        try {
            object = new JSONObject(mostRecentPosts);
            JSONArray jsonArray = object.getJSONArray("items");
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    list.add(jsonArray.get(i).toString());
                    Log.d(jsonArray.get(i).toString(), jsonArray.get(i).toString());
                }
                Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
                this_user.setLocalPosts(list);
                String displayPosts = "";
                for (int i = 0; i < len; i++) {
                    displayPosts += " \n" + list.get(i);
                }
                posts.setText(displayPosts);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    onclick for making a new club
     */
    //move to view
    public void onClickNewClub(View view) {
        Intent intent = new Intent(this, CreateClubView.class);
        startActivity(intent);
    }

    @Override
    //move to view
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    //stay here in model
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    //stay in model
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.refresh_user_data) {
            getUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //MVP
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allClubs) {
            String getClubNames;
            //get string of club names from server
            getClubNames = null;
            //getClubNames = new HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "GET").get();

            try {
                // JSONObject club_names = new JSONObject(jsonString);
                ArrayList<String> list = new ArrayList<String>();
                JSONObject object = new JSONObject(getClubNames);
                JSONArray jsonArray = object.getJSONArray("items");
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        list.add(jsonArray.get(i).toString());
                        Log.d(jsonArray.get(i).toString(), jsonArray.get(i).toString());
                    }
                    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
                    this_user.setLocal_club_Names(list);
                    displayClubs(getClubNames);
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        else if (id == R.id.Post) {
            String getClubNames;
            //get string of club names from server
            //getClubNames = new HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "GET").get();
            getClubNames = null;

            try {
                // JSONObject club_names = new JSONObject(jsonString);
                ArrayList<String> list = new ArrayList<String>();
                JSONObject object = new JSONObject(getClubNames);
                JSONArray jsonArray = object.getJSONArray("items");
                if (jsonArray != null) {
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        list.add(jsonArray.get(i).toString());
                        Log.d(jsonArray.get(i).toString(), jsonArray.get(i).toString());
                    }
                    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
                    this_user.setLocal_club_Names(list);
                    displayClubsForPost(getClubNames);
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        else if (id == R.id.calendar) {

        } else if (id == R.id.tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


