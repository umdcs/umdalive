package com.example.kevin.umdalive;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;

public class SignUpActivityView extends AppCompatActivity {


    private Presenter presenter;

    private   EditText email;
    private EditText Password;
    private EditText userNamee;
    private Spinner gradDate;
    private Spinner Major;



    private String password;
    private String Email;
    private String userName;

    private String graduation;
    private  String major;



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





//---------------------------------------------------------------------Spinner for the Major
      Major=(Spinner) findViewById(R.id.major_input);


        //creating the major adapter for the spinner
        ArrayAdapter<CharSequence> majorAdapter=ArrayAdapter.createFromResource(this, R.array.major_list, android.R.layout.simple_spinner_item);

//turning the major spinner object into a dropdown list
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //applying the Majoradapter to the spinner
        Major.setAdapter(majorAdapter);


    }

    /**
     * this method retrieves the data from the sign_up_activity layout,
     * when the button is clicked this method sends that data to the presenter
     * all data is converted to strings for the presenter class
     */
    protected void signUp(View view){

        //retrieving email adress from the EditText object and converting it to a string
        email=(EditText) findViewById(R.id.new_email_input);
        String emailText= email.getText().toString();
        //retrieving password and converting it to string
        Password=(EditText) findViewById(R.id.new_password_input);
        String passwordText=Password.getText().toString();

        //getting username from the edit text object
        userNamee=(EditText) findViewById(R.id.new_name_input);
        String nameTemp=userNamee.getText().toString();

/**
 * if the emailadress entered has an '@' symbol
 * then use the setter to set private datafields
 */

        if(emailText.indexOf('@') != -1){
          //setting the graduation date
            setGraduation(gradDate.getSelectedItem().toString());
             setMajor(Major.getSelectedItem().toString());
            setEmail(emailText);
            setPassword(passwordText);
            setUserName(nameTemp);

           System.out.println(getMajor());
            Log.d(getMajor(),getMajor());
            Log.d(getGraduation(),getGraduation());

            presenter.setUserInfo(this.getUserName(),this.getPassword(),this.getEmail(),this.getMajor(),this.getGraduation());


        }

        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid email.", Toast.LENGTH_SHORT).show();







    }


    //setters and getters

    public void setGraduation(String grad){this.graduation=grad;}
    public void setMajor(String mess){this.major=mess;}
    public void setEmail(String mess){this.Email=mess;}
    public void setPassword(String mess){this.password=mess;}
    public void setUserName(String mess){this.userName=mess;}


    public String getPassword(){return password;}
    public String getEmail(){return Email;}
    public String getGraduation(){return graduation;}
    public String getMajor(){return major;}

    public String getUserName(){return userName;}


}