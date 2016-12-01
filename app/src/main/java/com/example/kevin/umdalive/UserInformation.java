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

    private String local_userName;

    private ArrayList<Club> local_clubs_Subscibed;
    private ArrayList<String> local_posts_Subscibed;
    public final String serverAddress = "https://lempo.d.umn.edu:5001";
    private ArrayList<String> local_club_Names;


    /*
    Sets the local data that the app will run off of, will use this method when user signs in
     */
    public UserInformation(String temp_local_username, String temp_major, String temp_graduationDate, Club[] temp_clubs_subscribed){
        local_userName = temp_local_username;
        local_clubs_Subscibed = new ArrayList<Club>();
        local_posts_Subscibed = new ArrayList<String>();
        for(Club x : temp_clubs_subscribed)
        {
            local_clubs_Subscibed.add(x);
        }
    }

    public UserInformation(){
        local_userName = "";
        local_clubs_Subscibed = new ArrayList<Club>();
        local_club_Names = new ArrayList<String>();
        local_posts_Subscibed = new ArrayList<String>();
    }
    /*
    get method for username of user.
     */
    public String getLocalUsername()
    {
        return local_userName;
    }
    /*
    get for ArrayList of clubs
    */
    public ArrayList<Club> getLocalClubs()
    {
        return local_clubs_Subscibed;

    }
    /*
    Edits username
     */
    public void setLocal_userName(String name)
    {
    local_userName = name;
    }
    /*
        Takes in a new arraylist of most recent posts then
        Removes all current posts and then replaces objects in arraylist temp
        and addes them to local array list
         */
    public void setLocalPosts(ArrayList<String> temp_posts_subscribed)
    {
        while(local_posts_Subscibed.size() !=0){
            local_posts_Subscibed.remove(0);
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
     */
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


    private class HTTPAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection serverConnection = null;
            InputStream is = null;

            Log.d("Debug:", "Attempting to connect to: " + params[0]);

            try {
                URL url = new URL( params[0] );
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
         *
         * @param result the result from the query
         */
        protected void onPostExecute(String result) {

        }
    }
}
