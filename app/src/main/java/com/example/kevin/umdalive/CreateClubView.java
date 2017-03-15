package com.example.kevin.umdalive;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateClubView extends AppCompatActivity {

    private String clubName;
    private String userName;
    private String keyWords;
    private String description;
    private String post;

    private Object keywordItem = new Object();          //this item grabs from user


    /*
   onCreate method for CreateClub class
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
                keywordItem = parent.getItemAtPosition(pos); //this shouldn't need to be in the view, but I don't currently know what to do with it
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
        onClick for make CreateClub button
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
        //restPUT(view); //will be used when the Async class is created
    }
}
