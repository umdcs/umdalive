package com.example.kevin.umdalive;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivityView extends AppCompatActivity {


    Presenter presenter;
    private String postToDisplay;
    private static String clubToPost;
    private EditText newPost;

    protected void onCreate(Bundle savedInstanceState) {
        presenter = new Presenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        newPost = (EditText) findViewById(R.id.post_entry_box);
    }

    public void sendPost(View view) {
        String curClub = presenter.restGet("getClub", "");
        try {
            JSONObject club = new JSONObject(curClub);
            String clubName = club.get("clubname").toString();
            presenter.putPost(clubName, newPost.getText().toString());
            Log.d("Club posting: " + clubName, "New post: " + newPost.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
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
}
