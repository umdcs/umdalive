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

    private Object keywordItem = new Object();          //this item grabs from user
    Presenter presenter;
    /*
   onCreate method for CreateClub class
   Contains a spinner that will display the categories for
   the user to choose from to determine what category their
   club fits into.
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new Presenter(this);
        setContentView(R.layout.new_club_activity);
        Spinner spinner = (Spinner) findViewById(R.id.keywordChooser); // Create an ArrayAdapter using the string array and a default spinner layout
        //keyword_list is the list of all the club categories. We should probably expand this at some point and add on "other" option.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.keyword_list, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
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
        onClick for make CreateClub button
        current functionality: currently this takes in the user input
        of administrator, description, post, keyword/catagory, and
        sends them to server to be saved.
         */
    public void onClickMakeClub(View view) {
        Intent intent = new Intent(this, MainView.class);

        EditText newName = (EditText) findViewById(R.id.name_title_enter);
        EditText admin = (EditText) findViewById(R.id.admin_of_club);
        EditText description = (EditText) findViewById(R.id.description_of_club);
        EditText newPost = (EditText) findViewById(R.id.post_of_club);

        String jsonString = presenter.makeClub(newName.getText().toString(), admin.getText().toString(),
                (String)keywordItem, description.getText().toString(), newPost.getText().toString());

        startActivity(intent);
        presenter.restPut("putNewClub", jsonString);
    }
}
