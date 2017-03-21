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
    UserInformation this_user = MainActivity.getUserInformation();


    /**
     * Starts the activity and initializes the layout
     *
     * @param savedInstanceState
     */

    protected void onCreate(Bundle savedInstanceState) {
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
        Log.d(postToDisplay, postToDisplay);
        Log.d(postToDisplay, postToDisplay);
        Log.d(postToDisplay, postToDisplay);

        Log.d(clubToPost, clubToPost);
        //sending the post and the club to post too, to the presenter
        presenter.restGet(getClub(), getPost());
//starts new activity
        startActivity(intent);
    }


    /**
     * method takes in a club name interms of a string and sets it to private clubToPost
     *
     * @param club the name of the club that is being psoted
     */
    public static void setClub(String club) {
        clubToPost = club;
    }

    /**
     * @return club name that the post is directed too
     */
    public static String getClub() {
        return clubToPost;
    }


    /**
     * takes in the post that the author enters, and sets to the private postToDisplay
     *
     * @param post string containing the mesage to be posted
     */
    private void setPost(String post) {
        postToDisplay = post;
    }


    /**
     * @return returns the text that is being posted
     */

    public String getPost() {
        return postToDisplay;
    }

}