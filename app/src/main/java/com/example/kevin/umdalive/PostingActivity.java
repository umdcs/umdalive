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

    private String postToDisplay;
    private static String clubToPost;
    private EditText newPost;
    //UserInformation this_user = MainActivity.getUserInformation();

    public PostingActivity(){
    }

    /**
     * method takes in a club name interms of a string and sets it to private clubToPost
     * @param club the name of the club that is being psoted
     */
    public static void setClub(String club){
        clubToPost = club;
    }

    /**
     *
     * @return club name that the post is directed too
     */
    public static String getClub(){
        return clubToPost;
    }


    /**
     * takes in the post that the author enters, and sets to the private postToDisplay
     * @param post string containing the mesage to be posted
     */
    private void setPost(String post){
    postToDisplay = post;
    }


    /**
     *
     * @return returns the text that is being posted
     */

    public String getPost(){
        return postToDisplay;
    }

    /**
     * Starts the activity and initializes the layout
     * @param savedInstanceState
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        newPost = (EditText) findViewById(R.id.post_entry_box);

    }

/*
Life cycle methods
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


    /**
     * this method responds to send button in the posting_activity.xml. When clicked it send the user back to the main activity
     * @param view 
     */
    public void sendPost(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //sets the post
        setPost(newPost.getText().toString());

        //logcat messages
        Log.d(postToDisplay, postToDisplay);
        Log.d(postToDisplay, postToDisplay);
        Log.d(postToDisplay, postToDisplay);
        Log.d(postToDisplay, postToDisplay);

        Log.d(clubToPost,clubToPost);
        restPUT(view);
        startActivity(intent);
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
            jsonParam.put("clubToPost", clubToPost);
            jsonParam.put("postToDisplay", postToDisplay);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = jsonParam.toString();
        Log.d("DEBUG [PUT]:", jsonString);
        //new HTTPAsyncTask().execute(this_user.serverAddress + "/newPost", "PUT", jsonString);
    }

}

