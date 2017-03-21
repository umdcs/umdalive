package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Luke on 11/7/2016.
 */

public class SignUpActivity extends AppCompatActivity {
    /*
    Instance variables to save the input from user
    before they are sent to server.
     */
    private String emailAddress = "";               //holds email of user
    private String user_password = "";              //holds password of user
    private String graduation_date = "";            //holds graduation date as string
    private String major = "";                      //holds major of user as string
    private Object graduationItem = new Object();   //Object used to get item from the spinner containing graduation
                                                    // date. (will later be converted to string and stored in graduation_date
    private Object majorItem = new Object();        //Object used to get item from the spinner containing major
                                                    //(will later be converted to string and stored in graduation_date



    /*
    onCreate method for signUpActivity class
    */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        /*
        Spinner for Graduation Year
         */
        Spinner spinner_class = (Spinner) findViewById(R.id.graduationYear_input); // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.graduation_date, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
        spinner_class.setAdapter(adapter1);
        spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                graduationItem = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        /*
        Spinner for Major
         */
        Spinner spinner_major = (Spinner) findViewById(R.id.major_input); // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.major_list, android.R.layout.simple_spinner_item); // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
        spinner_major.setAdapter(adapter2);
        spinner_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                majorItem = parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    /*
    onClick for Send button to make a new user
    currecntly checks email for '@' symbol
     */
    public void signUp(View view) {
        EditText emailEntry = (EditText) findViewById(R.id.new_email_input);
        EditText passwordEntry = (EditText) findViewById(R.id.new_password_input);
        String emailCheckTemp = emailEntry.getText().toString();
        String password = passwordEntry.toString();
        if(emailCheckTemp.indexOf('@') != -1) {
            setEmailAddress(emailCheckTemp);
            setGraduationDate((String)graduationItem);
            setMajor(majorItem.toString());
            setPassword(password);
            System.out.println(major);
            Log.d(major,major);
            Log.d(graduation_date,graduation_date);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            restPUT(view);
        }
        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid email.", Toast.LENGTH_SHORT).show();
    }


    /*
    get and set methods for instance variables
    */
    public void setGraduationDate(String graduationDate) {this.graduation_date = graduationDate; }
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
    public void setPassword(String password) {this.user_password = password;}
    public void setMajor(String major) {this.major = major;}
    public String getEmailAddress() { return emailAddress; }
    public String getGraduationDate() { return graduation_date;}
    public String getMajor() { return major;}

    /*
    Methods to inherit from parent class
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

    /**
     * Acts as the onClick callback for the REST PUT Button. The code will generate a REST PUT
     * action to the REST Server.
     *
     * @param view
     */
    public void restPUT(View view) {

        JSONObject jsonParam = null;
        try {
            //Create JSONObject here
            jsonParam = new JSONObject();
            jsonParam.put("email",emailAddress);;
            jsonParam.put("password",user_password);
            jsonParam.put("graduation_date",graduation_date);
            jsonParam.put("major", major);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = jsonParam.toString();
        Log.d("DEBUG [PUT]:", jsonString);
        //new HTTPAsyncTask().execute("http://10.0.2.2:5000/userInformation", "PUT", jsonString); //Makes sure data is sent to server
    }


}

