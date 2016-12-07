package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

/**
 * Created by Luke on 12/6/2016.
 */

public class PostingActivity extends AppCompatActivity {

    private String post;
    private static String clubToPost;
    UserInformation this_user = MainActivity.getUserInformation();

    public PostingActivity(){
    }

    public static void setClub(String club){
        clubToPost = club;
    }

    public static String getClub(){
        return clubToPost;
    }

    private void setPost(String post){
    this.post = post;
    }

    public String getPost(){
        return this.post;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        EditText newPost = (EditText) findViewById(R.id.post_entry_box);
        setPost(newPost.getText().toString());
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

    public void sendPost(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        restPUT(view);
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
            Toast.makeText(getApplicationContext(),"Data attempting update", Toast.LENGTH_LONG).show();
        }
    }
    public void restPOST(View view) {
        JSONObject jsonParam = null;
        try {
            //Create JSONObject here
            jsonParam = new JSONObject();
            jsonParam.put("clubToPost:", clubToPost);
            jsonParam.put("post",post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonParam.toString());
        new HTTPAsyncTask().execute(this_user.serverAddress + "/newPost", "POST", jsonParam.toString());
    }

    /**
     * Acts as the onClick callback for the REST PUT Button. The code will generate a REST PUT
     * action to the REST Server.
     *
     * @param view
     */
    public void restPUT(View view) {

        JSONObject jsonParam = null;
        try {
            //Create JSONObject here
            jsonParam = new JSONObject();
            jsonParam.put("club:", clubToPost);
            jsonParam.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG [PUT]:", jsonParam.toString());
        new HTTPAsyncTask().execute(this_user.serverAddress + "/newPost", "PUT", jsonParam.toString()); //Makes sure data is sent to server
    }

}

