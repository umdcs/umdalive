package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Luke on 11/7/2016.
 */

public class LoginActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public void login(View view) {
        EditText emailEntry = (EditText) findViewById(R.id.email_input);
        String emailCheckTemp = emailEntry.getText().toString();
        if(emailCheckTemp.indexOf('@') != -1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid email.", Toast.LENGTH_SHORT).show();
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

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}

