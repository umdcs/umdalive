package com.example.kevin.umdalive;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity {

    //Presenter presenter; //there shouldn't be an error here after merging with the Presenter branch to gain the presenter class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

       // presenter = new Presenter(this);
    }
    /*
    This is the onClick for the Login button
    current functionality: currently all this does is check for "@" symbol and go to next page.
    The next page is the Main Activity.

    Replace with OAuth
     */

    public void login(View view) {
        EditText emailEntry = (EditText) findViewById(R.id.email_input);
        String emailCheckTemp = emailEntry.getText().toString();
        if(emailCheckTemp.indexOf('@') != -1) {
            Intent intent = new Intent(this, MainView.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid email.", Toast.LENGTH_SHORT).show();
    }
}
