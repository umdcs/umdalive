package com.example.kevin.umdalive.Views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.kevin.umdalive.Models.PostForClubActivity;
import com.example.kevin.umdalive.Presenters.Presenter;
import com.example.kevin.umdalive.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivityView extends AppCompatActivity {


    private Presenter presenter;
    private static String clubToPost;
    private EditText title;
    private EditText location;
    private EditText time;
    private EditText date;
    private EditText addInfo;
    private String clubName;

    protected void onCreate(Bundle savedInstanceState) {
        clubName = getIntent().getStringExtra(PostForClubActivityView.CLUB_NAME);
        presenter = new Presenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        title = (EditText) findViewById(R.id.event_title);
        time = (EditText) findViewById(R.id.event_time);
        date = (EditText) findViewById(R.id.event_date);
        location = (EditText) findViewById(R.id.event_location);
        addInfo = (EditText) findViewById(R.id.additional_info);

    }

    public void sendPost(View view) {
        Log.d("Club posting: " + clubName, "New post: " + title.getText().toString());
        presenter.putPost(clubName, title.getText().toString(), time.getText().toString(), date.getText().toString()
                , location.getText().toString(), addInfo.getText().toString());
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
