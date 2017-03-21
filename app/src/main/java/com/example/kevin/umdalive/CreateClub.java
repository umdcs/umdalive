package com.example.kevin.umdalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is the activity that is used for creating a new club.
 *
 * Thankfully this was already commented pretty well so I just added some clarification here and there.
 */

public class CreateClub extends AppCompatActivity {

    private UserInformation  this_user = new UserInformation();
    private String clubName;
    private String userName;
    private String keyWords;
    private String description;
    private String post;

  //moved to view
    //  private Object keywordItem = new Object();          //this item grabs from user

    //constructors
    public CreateClub(String cb, String un, String desc, String p, String key){
        clubName = cb;
        userName = un;
        keyWords = key;
        description = desc;
        post = p;
    }
    public CreateClub(){
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
    //Moved to view
//    public void setClubName(String name){
//        clubName = name;
//    }

    /*
    this resets the keyword to a new word
     para: string of new keyword with '#' in front of it
     */
    //Moved to view
//    public void setKeyWords(String newKeyword){
//        keyWords = newKeyword;
//    }
    /*
    temporary method to have user set their username to place it as the
    admin of the new club they created.
    para: string of username
     */
 //Moved to view
//    //   public void setUserName(String admin){
//        userName = admin;
//    }

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
    //moved to View
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    /*
//    sets the new post for the club
//     */
//    public void setPost(String post) {
//        this.post = post;
//    }
    /*
    onCreate method for CreateClub class
    Contains a spinner that will display the categories for
    the user to choose from to determine what category their
    club fits into.
     */
    //moved to view
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.new_club_activity);
//        Spinner spinner = (Spinner) findViewById(R.id.keywordChooser); // Create an ArrayAdapter using the string array and a default spinner layout
//        //keyword_list is the list of all the club categories. We should probably expand this at some point and add on "other" option.
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//        R.array.keyword_list, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//        //dealing with the selected option of the spinner
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                keywordItem = parent.getItemAtPosition(pos);
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }
    /*
    onClick for make CreateClub button
    current functionality: currently this takes in the user input
    of administrator, description, post, keyword/catagory, and
    sends them to server to be saved.
     */
    //moved to view
//    public void onClickMakeClub(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        //get name of club from edit text
//        EditText newName = (EditText) findViewById(R.id.name_title_enter);
//        setClubName(newName.getText().toString());
//        //get name of admin who created club
//        EditText admin = (EditText) findViewById(R.id.admin_of_club);
//        setUserName(admin.getText().toString());
//        //get description of club
//        EditText desription = (EditText) findViewById(R.id.description_of_club);
//        setDescription(desription.getText().toString());
//        EditText newPost = (EditText) findViewById(R.id.post_of_club);
//        setPost(newPost.getText().toString());
//        setKeyWords((String)keywordItem);
//        startActivity(intent);
//        restPUT(view);
//    }
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
        String jsonString = jsonParam.toString();
        Log.d("DEBUG [PUT]:", jsonString);
        //pass jsonString to restModel
        //new HTTPAsyncTask().execute(this_user.serverAddress + "/newClub", "PUT", jsonString); //Makes sure data is sent to server
    }
