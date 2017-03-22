package com.example.kevin.umdalive;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Questioning this files usage
 *
 * Created by rgebh_000 on 3/17/2017.
 *
 * Class to define UserInformation on sign up
 */
public class UserInformationModel extends AppCompatActivity {

    private String name;
    private String password;
    private String email;
    private String major;
    private String gradDate;


    private ArrayList<String> localClubNames;
    private ArrayList<String> localClubsSubscribed;
    private ArrayList<String> localPostsSubscribed;


    public UserInformationModel(){
        name = "";
        localClubsSubscribed = new ArrayList<String>();
        localClubNames = new ArrayList<String>();
        localPostsSubscribed = new ArrayList<String>();
    }


    /**
     * constructor
     *
     * not currently using post and clubs subscribed for simplicity
     * @param name of user
     * @param password of user
     * @param major of user
     * @param email of user
     * @param gradDate of user
     *
     */
    public UserInformationModel(String name, String password, String major, String email, String gradDate, ArrayList<String> temp_clubs_subscribed){
        this.name = name;
        this.password = password;
        this.email = email;
        this.major = major;
        this.gradDate = gradDate;
        localPostsSubscribed = new ArrayList<String>();
        localClubsSubscribed = temp_clubs_subscribed;
    }

    public UserInformationModel(String name, String major, String email, String gradDate, ArrayList<String> temp_clubs_subscribed){
        this.name = name;

        this.email = email;
        this.major = major;
        this.gradDate = gradDate;
        localPostsSubscribed = new ArrayList<String>();
        localClubsSubscribed = temp_clubs_subscribed;
    }



    /*
       Takes in a new arraylist of most recent posts then
       Removes all current posts and then replaces objects in arraylist temp
       and addes them to local array list
   */
    public void setLocalPosts(ArrayList<String> temp_posts_subscribed)
    {
        while(localPostsSubscribed.size() !=0){
            localPostsSubscribed.remove(0);
        }
        for(String x : temp_posts_subscribed)
        {
            localPostsSubscribed.add(x);
        }
    }





    /**
     * getter for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name
     *
     * @param name name be be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for password
     *
     * @param password to be set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getter for email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email
     *
     * @param email to be set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for Major
     *
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * setter for major
     *
     * @param major to be set
     */
    public void setMajor(String major) {
        this.major = major;

    }

    /**
     * getter for gradDate
     *
     * @return gradDate
     */
    public String getGradDate() {
        return gradDate;
    }

    /**
     * setter for gradDate
     *
     * @param gradDate to be set
     */
    public void setGradDate(String gradDate) {
        this.gradDate = gradDate;
    }

    /**
     * Function to create a JSON object of a UserInformation
     * JSON object is then made into a string and returned
     *
     * @return jsonString string form of JSON object UserInformation
     */
    public String jsonStringify() {
        JSONObject jsonString = null;
        try {
            //Create JSONObject here
            jsonString = new JSONObject();
            jsonString.put("name", name);
            jsonString.put("password", password);
            jsonString.put("email", email);
            jsonString.put("major", major);
            jsonString.put("gradDate", gradDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonString.toString());

        return jsonString.toString();
    }
}
