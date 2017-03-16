package com.example.kevin.umdalive;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Andrew on 3/15/2017.
 */

public class RestModel {
    private UserInformation  this_user = new UserInformation();
    private Context context;

    /**
     * The context might be used later debugging with toast messages. Right now it is not needed though.
     * @param context
     */
    public RestModel(Context context){
        this.context = context;
    }

    public RestModel(){}

    public void setContext(Context context){
        this.context = context;
    }

    /**
     * Taken from AllClubs.java
     * I dont think they ever made it so this is actually displayed when a club is clicked so work on that...
     * Some of this stuff should be done in the AllClubs model class, make sure we fix that at some point.
     *
     * @return String of JSON response
     */
    public String getAllClubs(JSONObject jsonParam){
        try{
            return new HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "GET", jsonParam.toString()).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * From MainActivity refreshPosts function.
     *
     * @return
     */
    public String getRecentPosts(){
        String mostRecentPosts = null;
        try {
            mostRecentPosts = new HTTPAsyncTask().execute(this_user.serverAddress + "/mostRecentPosts", "GET").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mostRecentPosts;
    }

    /**
     * From MainActivity
     * @return
     */
    public String getUserData(){
        String userData;
        try {
            userData = new HTTPAsyncTask().execute(this_user.serverAddress + "/userDataGet", "GET").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            userData = null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            userData = null;
        }
        return userData;
    }

    /**
     * Taken from Club.java
     *
     * @param jsonParam
     */
    public void putNewClub(JSONObject jsonParam) {
        new HTTPAsyncTask().execute(this_user.serverAddress + "/newClub", "PUT", jsonParam.toString()); //Makes sure data is sent to server
    }

    /**
     * The previous group just copy and pasted all this. At some point we make it better.
     */
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
            Toast.makeText(context, "Data transfer sucessful", Toast.LENGTH_SHORT).show();
        }
    }
}