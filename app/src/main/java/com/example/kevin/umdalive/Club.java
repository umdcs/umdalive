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
import com.example.kevin.umdalive.MainActivity;

/**
 * This is the activity that is used for creating a new club.
 *
 * Thankfully this was already commented pretty well so I just added some clarification here and there.
 */

public class Club extends AppCompatActivity {

    private UserInformation  this_user = new UserInformation();
    private String clubName;
    private String userName;
    private String keyWords;
    private String description;
    private String post;

    private Object keywordItem = new Object();          //this item grabs from user

    //constructors
    public Club(String cb, String un, String desc, String p, String key){
        clubName = cb;
        userName = un;
        keyWords = key;
        description = desc;
        post = p;
    }
    public Club(){
        clubName = "default";
        userName = "default-user";
        keyWords = "#default";
        description = "default";
        post = "This club did not get created correctly";
    }
    /*
    This sets the name of the club
    para: string of new club
     */
    public void setClubName(String name){
        clubName = name;
    }

    /*
    this resets the keyword to a new word
     para: string of new keyword with '#' in front of it
     */
    public void setKeyWords(String newKeyword){
        keyWords = newKeyword;
    }
    /*
    temporary method to have user set their username to place it as the
    admin of the new club they created.
    para: string of username
     */
    public void setUserName(String admin){
        userName = admin;
    }

    public String getClubName() {
        return clubName;
    }

    public String getDescription() {
        return description;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public String getPost() {
        return post;
    }

    public String getUserName() {
        return userName;
    }
    /*
    sets description to string entered
    para: new string for description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /*
    sets the new post for the club
     */
    public void setPost(String post) {
        this.post = post;
    }
    /*
    onCreate method for Club class
    Contains a spinner that will display the categories for
    the user to choose from to determine what category their
    club fits into.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_club_activity);
        Spinner spinner = (Spinner) findViewById(R.id.keywordChooser); // Create an ArrayAdapter using the string array and a default spinner layout
        //keyword_list is the list of all the club categories. We should probably expand this at some point and add on "other" option.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.keyword_list, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //dealing with the selected option of the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                keywordItem = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    /*
    onClick for make Club button
    current functionality: currently this takes in the user input
    of administrator, description, post, keyword/catagory, and
    sends them to server to be saved.
     */
    public void onClickMakeClub(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //get name of club from edit text
        EditText newName = (EditText) findViewById(R.id.name_title_enter);
        setClubName(newName.getText().toString());
        //get name of admin who created club
        EditText admin = (EditText) findViewById(R.id.admin_of_club);
        setUserName(admin.getText().toString());
        //get description of club
        EditText desription = (EditText) findViewById(R.id.description_of_club);
        setDescription(desription.getText().toString());
        EditText newPost = (EditText) findViewById(R.id.post_of_club);
        setPost(newPost.getText().toString());
        setKeyWords((String)keywordItem);
        startActivity(intent);
        restPUT(view);
    }
/*
Methods to tell Activity to inherit from parent and follow
protocol of parent
 */
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


    protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    }
/*
This is private class used to send data to server.
 */

//looks like they just copy and pasted this like they did in most other instances in this project...
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
        Toast.makeText(getApplicationContext(),"Data transfer sucessful", Toast.LENGTH_SHORT).show();
    }
}
//dont know why this was created, i dont think its ever used...
    public void restPOST(View view) {
        JSONObject jsonParam = null;
        try {
            //Create JSONObject here
            jsonParam = new JSONObject();
            jsonParam.put("clubname",clubName);
            jsonParam.put("username", userName );
            jsonParam.put("keywords", keyWords);
            jsonParam.put("description", description);
            jsonParam.put("post",post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonParam.toString());
        new HTTPAsyncTask().execute(this_user.serverAddress + "/newClub", "POST", jsonParam.toString());
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
            jsonParam.put("clubname",clubName);
            jsonParam.put("username", userName );
            jsonParam.put("keywords", keyWords);
            jsonParam.put("description", description);
            jsonParam.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG [PUT]:", jsonParam.toString());
        new HTTPAsyncTask().execute(this_user.serverAddress + "/newClub", "PUT", jsonParam.toString()); //Makes sure data is sent to server
    }

}
