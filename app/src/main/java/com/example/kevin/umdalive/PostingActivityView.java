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
    private EditText title;
    private EditText location;
    private EditText time;
    private EditText date;

    protected void onCreate(Bundle savedInstanceState) {
        presenter = new Presenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        title = (EditText) findViewById(R.id.event_title);
        time = (EditText) findViewById(R.id.event_time);
        date = (EditText) findViewById(R.id.event_date);
        location = (EditText) findViewById(R.id.event_location);
    }

    public void sendPost(View view) {
        String curClub = presenter.restGet("getClub", "");
        String tempTitle = title.getText().toString();
        String tempTime = time.getText().toString();
        String tempDate = date.getText().toString();
        String tempLocation = location.getText().toString();

        try {
            JSONObject club = new JSONObject(curClub);
            String clubName = club.get("clubname").toString();
            presenter.putPost(clubName, tempTitle, tempTime, tempDate, tempLocation);
            Log.d("Club posting: " + clubName, "New post: " + title.getText().toString());
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
