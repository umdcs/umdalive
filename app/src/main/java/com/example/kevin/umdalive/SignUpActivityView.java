package com.example.kevin.umdalive;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class SignUpActivityView extends AppCompatActivity {

    private   EditText email;
    private EditText Password;
    private Spinner graduation;

    private Spinner gradDate;
    private  Spinner major;

    /**
     * This starts this activity
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);



        gradDate= (Spinner) findViewById(R.id.graduationYear_input);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> gradDateAdapter = ArrayAdapter.createFromResource(this,
                R.array.graduation_date, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        gradDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
       gradDate.setAdapter(gradDateAdapter);


        major=(Spinner) findViewById(R.id.major_input);


        //creating the major adapter for the spinner
        ArrayAdapter<CharSequence> majorAdapter=ArrayAdapter.createFromResource(this, R.array.major_list, android.R.layout.simple_spinner_item);

//turning the major spinner object into a dropdown list
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


    }

    /**
     * this method retrieves the data from the sign_up_activity layout,
     * when the button is clicked this method sends that data to the presenter
     * all data is converted to strings for the presenter class
     */
    protected void signUp(ModelViewPresenterComponents.View view){
        /**
         * getting the selcted item from the spinner and converting it to a string.
         */
        String gradDateText= gradDate.getSelectedItem().toString();

        /**
         * retrieving the major selected by user into a string for the presenter
         */

        String majorText= major.getSelectedItem().toString();

        //retrieving email adress from the EditText object and converting it to a string
        email=(EditText) findViewById(R.id.new_email_input);
        String emailText= email.getText().toString();

        //retrieving password and converting it to string
        Password=(EditText) findViewById(R.id.new_password_input);
        String passwordText=Password.getText().toString();










    }
}