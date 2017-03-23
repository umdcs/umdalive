package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.util.Collections;

public class MainView  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

Presenter presenter;

    private static UserInformationModel thisUser = new UserInformationModel();

    EditText posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this);
        getUser(); // calls server
        setContentView(R.layout.activity_main);
        viewSetup();
    }

    // starts display CreateClub Activity View
    public void displayClubs(String clubNamess) {
        Intent intent = new Intent(this, AllClubsView.class);
        startActivity(intent);
    }

    // starts display Clubs for post Activity View
    public void displayClubsForPost(String clubNames)
    {
        Intent intent = new Intent(this, PostForClubActivityView.class);
        startActivity(intent);
    }

    public void updateUser() {
        TextView emailView = (TextView) findViewById(R.id.userEmail);
        emailView.setText(thisUser.getEmail());

        //gets the users name and sets menu to their name
        TextView userNameView = (TextView) findViewById(R.id.userName);
        userNameView.setText(thisUser.getName());
    }

    /**
     * Refreshes the posts.
     * @param view the button handles this
     */
    public void refreshPosts(View view){
        //make mostRecentPosts equal the results from mostRecentPostsGET() in RestModel
        String mostRecentPosts = presenter.restGet("getRecentPosts","");
        ArrayList<String> recentPosts = presenter.refreshPosts(mostRecentPosts);
        thisUser.setLocalPosts(recentPosts);
        posts.setText(presenter.displayPosts(recentPosts));
    }

    /**
     * Starts CreateClubView
     * @param view button handles this
     */
    public void onClickNewClub(View view) {
        Intent intent = new Intent(this, CreateClubView.class);
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
            getUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Will stay here in the model
    public void getUser() {
            //will probably use the second parameter in the future for specific users..
            String userData = presenter.restGet("getUserData", "");
            thisUser = presenter.getMainUser(userData);
    }

    //will stay in model
    public static UserInformationModel getUserInformation() {
    return thisUser;
    }

    /**
     * this method responds to what the user selects on the menu
     * @param item the item the user selected
     * @return true or false if the item was succesfully received
     */

    //MVP
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allClubs) {
            //getting Json string of club names from server
            String getClubNames= presenter.restGet("getClub","");
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
                    thisUser.setLocalClubNames(list);


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
                    thisUser.setLocalClubNames(list);
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

    /**
     * This was all in onCreate and it was really cluttered so I just moved it to a seperate function.
     */
    private void viewSetup(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Understand this fab better
        //I believe it says "Replace with your own action" because the last group copy/pasted this from the internet
        //lololololololololololololllolololol
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle); //deprecated thing
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        posts = (EditText) findViewById(R.id.mainPosts);
        posts.setMaxLines(20);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
