package com.example.kevin.umdalive;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class PostingActivityView extends AppCompatActivity {


    Presenter presenter;
    private String postToDisplay;
    private static String clubToPost;
    private EditText newPost;

    protected void onCreate(Bundle savedInstanceState) {
        presenter= new Presenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        newPost = (EditText) findViewById(R.id.post_entry_box);

    }

    public void sendPost(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        //sets the post
        setPost(newPost.getText().toString());

        //logcat messages
        Log.d(postToDisplay, postToDisplay);
        Log.d(clubToPost, clubToPost);
        //sending the post and the club to post too, to the presenter
        presenter.restPost(clubToPost,postToDisplay);
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
