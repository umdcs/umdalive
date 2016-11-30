package com.example.kevin.umdalive;


import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by Luke on 11/8/2016.
 */

public class UserInformation {

    private String name;
    private String email;
    private String major;
    private String gradDate;


    private ArrayList<String> local_clubs_Subscibed;
    private ArrayList<String> local_posts_Subscibed;


    private ArrayList<String> local_club_Names;


    /*
    Sets the local data that the app will run off of, will use this method when user signs in
     */

    public UserInformation(String temp_local_username, String temp_major, String emai, String temp_graduationDate, ArrayList<String> temp_clubs_subscribed){
        name = temp_local_username;
        email = emai;
        major = temp_major;
        gradDate = temp_graduationDate;
        local_clubs_Subscibed = temp_clubs_subscribed;


    }


    /*
     *get methods for user information
     */
    public String getLocalUsername()
    {
        return name;
    }
    public String getEmail(){return email;}
    public String getMajor(){return major;}
    public String getGradDate(){return gradDate;}
    public ArrayList<String> getLocalClubs(){return local_clubs_Subscibed;}

    /*
        Takes in a new arraylist of most recent posts then
        Removes all current posts and then replaces objects in arraylist temp
        and addes them to local array list
         */
    public void setLocalPosts(ArrayList<String> temp_posts_subscribed)
    {
        for(String x : local_posts_Subscibed){
            local_posts_Subscibed.remove(x);
        }
        for(String x : temp_posts_subscribed)
        {
            local_posts_Subscibed.add(x);
        }
    }
    /*
    Takes in a new arraylist of clubs then
    Removes all current clubs and then replaces objects in arraylist temp
    and addes them to local array list

    public void setLocalClubs(ArrayList<Club> temp_clubs_subscribed)
    {

        for(Club x : local_clubs_Subscibed){
            local_clubs_Subscibed.remove(x);
        }
        for(Club x : temp_clubs_subscribed)
        {
            local_clubs_Subscibed.add(x);
        }
    }
    public void setLocal_club_Names(ArrayList<String> names)
    {
        for(String i : names){
            local_club_Names.remove(i);
        }
        for(String x : names){
            local_club_Names.add(x);
        }
    }
    public ArrayList<String> getLocal_club_Names()
    {
        return local_club_Names;
    }
    */



}
