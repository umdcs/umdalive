package com.example.kevin.umdalive.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevin.umdalive.Models.LoginActivity;
import com.example.kevin.umdalive.Models.PostInformationModel;
import com.example.kevin.umdalive.Models.UserInformationModel;
import com.example.kevin.umdalive.Presenters.Presenter;
import com.example.kevin.umdalive.R;

import java.util.ArrayList;

public class MainView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Presenter presenter;
    private static UserInformationModel thisUser;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<PostInformationModel> posts;
    private SwipeRefreshLayout swipeRefreshLayout;

    LoginActivity log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this);
        thisUser = new UserInformationModel();
        layoutManager = new LinearLayoutManager(this);
        setContentView(R.layout.activity_main);
        Log.d("DEBUG: ", "Setting up view");
        viewSetup();




        String value = null;
        String name=null;
        Uri pic;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("Email");
            name=extras.getString("Name");
          
            //The key argument here must match that used in the other activity
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView Email = (TextView)hView.findViewById(R.id.studentEmail);
        TextView Name=(TextView) hView.findViewById(R.id.userName);

        Name.setText(name);
        Email.setText(value);
        ImageView img=(ImageView) hView.findViewById(R.id.imageView);

        img.setImageURI();





    }

    /**
     * starts display CreateClub Activity View //I think this should say display AllClubsView
     */
    public void displayAllClubs() {
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
     * starts DisplayClubView
     */
    public void displayClub() {
        Intent intent = new Intent(this, DisplayClubView.class);
        startActivity(intent);
    }

    /**
     * starts SearchClubsView
     */
    public void searchClub() {
        Intent intent = new Intent(this, SearchClubsView.class);
        startActivity(intent);
    }

    /**
     * starts CreateClubView
     */
    public void makeNewClub() {
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
            displayAllClubs();
        }
        else if(id == R.id.user_profile) {
Intent intent= new Intent(this, userdata.class);
            startActivity(intent);


        }



        else if (id == R.id.Post) {
            displayClubsForPost();
        } else if (id == R.id.calendar) {

        } else if (id == R.id.search) {
            searchClub();
        } else if (id == R.id.new_club) {
            makeNewClub();
        } else if (id == R.id.nav_club1) {

        } else if (id == R.id.nav_club2) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * gets recent posts and displays
     */
    private void displayPosts(){
        posts = presenter.refreshPosts(presenter.restGet("getRecentPosts", ""));
        RecyclerView.Adapter adapter = presenter.getPostAdapter(posts, recyclerView);
        recyclerView.swapAdapter(adapter, true);
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * sets posts
     */
    private void setPosts(){
        posts = presenter.refreshPosts(presenter.restGet("getRecentPosts", ""));
        //thisUser.setLocalPosts(posts);
        //commented out because not needed at this point.
    }

    /**
     * This was all in onCreate and it was really cluttered so I just moved it to a separate function.
     */
    private void viewSetup() {
        setPosts();
        setUser();
        recyclerView = (RecyclerView) findViewById(R.id.mainPosts);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = presenter.getPostAdapter(posts, recyclerView);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                displayPosts();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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