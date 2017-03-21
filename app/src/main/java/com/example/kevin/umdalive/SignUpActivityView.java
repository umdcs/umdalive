package com.example.kevin.umdalive;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivityView extends AppCompatActivity {


    private Presenter presenter;

    private   EditText emailEditText;
    private EditText passwordEditText;
    private EditText userNameEditText;
    private Spinner gradDate;
    private Spinner majorSpinner;



    private String password;
    private String email;
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





//---------------------------------------------------------------------Spinner for the majorSpinner
      majorSpinner =(Spinner) findViewById(R.id.major_input);


        //creating the major adapter for the spinner
        ArrayAdapter<CharSequence> majorAdapter=ArrayAdapter.createFromResource(this, R.array.major_list, android.R.layout.simple_spinner_item);

//turning the major spinner object into a dropdown list
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //applying the Majoradapter to the spinner
        majorSpinner.setAdapter(majorAdapter);


    }

    /**
     * this method retrieves the data from the sign_up_activity layout,
     * when the button is clicked this method sends that data to the presenter
     * all data is converted to strings for the presenter class
     */
    protected void signUp(View view){

        //retrieving emailEditText adress from the EditText object and converting it to a string
        emailEditText =(EditText) findViewById(R.id.new_email_input);
        String emailText= emailEditText.getText().toString();
        //retrieving password and converting it to string
        passwordEditText =(EditText) findViewById(R.id.new_password_input);
        String passwordText= passwordEditText.getText().toString();

        //getting username from the edit text object
        userNameEditText =(EditText) findViewById(R.id.new_name_input);
        String nameTemp= userNameEditText.getText().toString();

/**
 * if the emailadress entered has an '@' symbol
 * then use the setter to set private datafields
 */

        if(emailText.indexOf('@') != -1){
          //setting the graduation date
            setGraduation(gradDate.getSelectedItem().toString());
             setMajorSpinner(majorSpinner.getSelectedItem().toString());
            setEmailEditText(emailText);
            setPasswordEditText(passwordText);
            setUserName(nameTemp);

           System.out.println(getMajorSpinner());
            Log.d(getMajorSpinner(), getMajorSpinner());
            Log.d(getGraduation(),getGraduation());

            presenter.setUserInfo(userName,password, email,major,graduation);


        }

        else Toast.makeText(getApplicationContext(),"Sorry, this is not a valid emailEditText.", Toast.LENGTH_SHORT).show();







    }


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



    //setters and getters

    public void setGraduation(String grad){this.graduation=grad;}
    public void setMajorSpinner(String mess){this.major=mess;}
    public void setEmailEditText(String mess){this.email =mess;}
    public void setPasswordEditText(String mess){this.password=mess;}
    public void setUserName(String mess){this.userName=mess;}


    public String getPasswordEditText(){return password;}
    public String getEmailEditText(){return email;}
    public String getGraduation(){return graduation;}
    public String getMajorSpinner(){return major;}

    public String getUserName(){return userName;}


}