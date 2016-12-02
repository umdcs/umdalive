package com.example.kevin.umdalive;

import android.content.ClipData;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static UserInformation this_user;
    private static ArrayList<String>  allClubs; //array of strings to represent clubs, will be club objects soon
    EditText posts;



    UserInformation local_user_info;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //obtain the user info from server

        setContentView(R.layout.activity_main);

        getUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        posts = (EditText)findViewById(R.id.mainPosts);
        posts.setMaxLines(20);

    }


    @Override
    public void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.

    }

    @Override
    public void onStop() {
    super.onStop();
    }



    private class HTTPAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection serverConnection = null;
            InputStream is = null;

            Log.d("Debug:", "Attempting to connect to: " + params[0]);

            try {
                URL url = new URL(params[0]);
                serverConnection = (HttpURLConnection) url.openConnection();
                serverConnection.setRequestMethod(params[1]);
                if (params[1].equals("POST") ||
                        params[1].equals("PUT") ||
                        params[1].equals("DELETE")) {
                    Log.d("DEBUG POST/PUT/DELETE:", "In post: params[0]=" + params[0] + ", params[1]=" + params[1] + ", params[2]=" + params[2]);
                    serverConnection.setDoOutput(true);
                    serverConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

                    // params[2] contains the JSON String to send, make sure we send the
                    // content length to be the json string length
                    serverConnection.setRequestProperty("Content-Length", "" +
                            Integer.toString(params[2].toString().getBytes().length));

                    // Send POST data that was provided.
                    DataOutputStream out = new DataOutputStream(serverConnection.getOutputStream());
                    out.writeBytes(params[2].toString());
                    out.flush();
                    out.close();
                }


                int responseCode = serverConnection.getResponseCode();
                Log.d("Debug:", "\nSending " + params[1] + " request to URL : " + params[0]);
                Log.d("Debug: ", "Response Code : " + responseCode);

                is = serverConnection.getInputStream();

                if (params[1] == "GET" || params[1] == "POST" || params[1] == "PUT" || params[1] == "DELETE") {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    try {
                        JSONObject jsonData = new JSONObject(sb.toString());
                        return jsonData.toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                serverConnection.disconnect();
            }

            return "Should not get to this if the data has been sent/received correctly!";
        }

        /**
         * @param result the result from the query
         */
        protected void onPostExecute(String result) {

        updateUser();

        }
    }

    public void displayClubs(String clubNames) {
        Intent intent = new Intent(this, AllClubs.class);

        startActivity(intent);
    }


    public static ArrayList<String> getAllClubs(){
        return allClubs;
    }
    public void getUser(){
        try {
            //gets string from server
            String userData = new HTTPAsyncTask().execute("http://10.0.2.2:5000/userDataGet", "GET").get();
            //string is turned into a jsonobject
            JSONObject user = new JSONObject(userData);
            ArrayList<Club> list = new ArrayList<Club>();
            JSONArray jArray = user.getJSONArray("clubs");
            //creation of an array list from json so user can be created
            if (jArray != null) {
                int len = jArray.length();
                for (int i=0;i<len;i++){

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
        catch (JSONException e1){
            e1.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static UserInformation getUserInformation() {
        return this_user;
    }

    /*
     * function to get user data and update the UI with their info
     */

    public void updateUser() {


        TextView emailView = (TextView) findViewById(R.id.userEmail);
        emailView.setText(this_user.getEmail());

        //gets the users name and sets menu to their name
        TextView userNameView = (TextView) findViewById(R.id.userName);
        userNameView.setText(this_user.getLocalUsername());

    }


    public String getUserName(){return this_user.getLocalUsername();}




    /*
    onclick for refreshing most recent posts
     */
    public void refreshPosts(View view) throws JSONException {
        String mostRecentPosts = null;
        try {
            mostRecentPosts = new HTTPAsyncTask().execute("http://10.0.2.2:5000/mostRecentPosts", "GET").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

    public void onClickNewClub(View view) {
        Intent intent = new Intent(this, Club.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
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
            updateUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allClubs) {
            try {
                String getClubNames;
                //get string of club names from server
                getClubNames = new HTTPAsyncTask().execute("http://10.0.2.2:5000/getAllClubs", "GET").get();

                try {
                    //make json array and extract clubs from it
                    ArrayList<String> list = new ArrayList<String>();
                    JSONObject object = new JSONObject(getClubNames);
                    JSONArray jsonArray = object.getJSONArray("items");

                    String clubName;
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            //gets the club object from the array of clubs
                            JSONObject club = jsonArray.getJSONObject(i);
                            //gets the name of that club and adds to the list of club names to display
                            clubName = club.getString("clubname");
                            list.add(clubName);


                        }
                        //sort club names alphabetically
                        Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
                        allClubs = list;
                        displayClubs(allClubs.toString());

                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.calendar) {

        } else if (id == R.id.tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

