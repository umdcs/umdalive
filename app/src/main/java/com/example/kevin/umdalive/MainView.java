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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Presenter presenter;
    private static UserInformationModel thisUser;
    EditText posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this);
        thisUser = new UserInformationModel();
        setUser(); // calls server
        setContentView(R.layout.activity_main);
        viewSetup();
    }

    /**
     * starts display CreateClub Activity View
     */
    public void displayClubs() {
        Intent intent = new Intent(this, AllClubsView.class);
        startActivity(intent);
    }

    /**
     * starts display Clubs for post Activity View
     */
    public void displayClubsForPost() {
        Intent intent = new Intent(this, PostForClubActivityView.class);
        startActivity(intent);
    }

    /**
     * Refreshes the posts.
     *
     * @param view the button handles this
     */
    public void refreshPosts(View view) {
        //make mostRecentPosts equal the results from mostRecentPostsGET() in RestModel
        String mostRecentPosts = presenter.restGet("getRecentPosts", "");
        ArrayList<String> recentPosts = presenter.refreshPosts(mostRecentPosts);
        thisUser.setLocalPosts(recentPosts);
        posts.setText(presenter.displayPosts(recentPosts));
    }

    /**
     * Starts CreateClubView
     *
     * @param view button handles this
     */
    public void onClickNewClub(View view) {
        Intent intent = new Intent(this, CreateClubView.class);
        startActivity(intent);
    }

    /**
     * Makes it so pressing back when drawer is open will take the user back to the main screen
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu menu being used
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here.
     *
     * @param item item selected
     * @return true if success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.refresh_user_data) {
            setUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Sets up the users profile info
     */
    public void setUser() {
        //will probably use the second parameter in the future for specific users..
        String userData = presenter.restGet("getUserData", "");
        thisUser = presenter.getMainUser(userData);
    }

    /**
     * This keeps track of whats happens when things are selected on the side bar.
     * The previous group had it so the MainView would fetch and setup all the activities here but it makes
     * more sense to do it in the respective activities(see AllClubsView for example).
     *
     * @param item the menu item selected
     * @return true if success
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.allClubs) {
            displayClubs();
        } else if (id == R.id.Post) {
            displayClubsForPost();
        } else if (id == R.id.calendar) {

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
    private void viewSetup() {
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
        //commented out because it's deprecated. If it turns out we need it we'll figure it out later.
        //drawer.setDrawerListener(toggle);
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
