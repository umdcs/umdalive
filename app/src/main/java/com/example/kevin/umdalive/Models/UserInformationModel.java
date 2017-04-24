package com.example.kevin.umdalive.Models;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kevin.umdalive.Models.ClubInformationModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Questioning this files usage
 *
 * Created by Ryan on 3/17/2017.
 *
 * Class to define UserInformation on sign up
 */
@SuppressWarnings("unused")
public class UserInformationModel extends AppCompatActivity {

    private String name;
    private String password;
    private String email;
    private String major;
    private String gradDate;

    private ArrayList<String> localClubNames;

    private ArrayList<ClubInformationModel> localClubsSubscribed;
    private ArrayList<String> localPostsSubscribed;

    public UserInformationModel(){
        name = "";
        localClubsSubscribed = new ArrayList<ClubInformationModel>();
        localClubNames = new ArrayList<String>();
        localPostsSubscribed = new ArrayList<String>();
    }

    public UserInformationModel(String name, String major, String email, String gradDate, ArrayList<ClubInformationModel> tempClubsSubscribed){
        this.name = name;
        this.email = email;
        this.major = major;
        this.gradDate = gradDate;
        localPostsSubscribed = new ArrayList<String>();
        localClubsSubscribed = tempClubsSubscribed;
    }


    /*
       Takes in a new arraylist of most recent posts then
       Removes all current posts and then replaces objects in arraylist temp
       and addes them to local array list
   */
    public void setLocalPosts(ArrayList<String> tempPostsSubscribed)
    {
        while(localPostsSubscribed.size() !=0){
            localPostsSubscribed.remove(0);
        }
        for(String x : tempPostsSubscribed)
        {
            localPostsSubscribed.add(x);
        }
    }

    public void setLocalClubNames(ArrayList<String> names)
    {
        localClubNames = names;
    }

    public ArrayList<String> getLocalClubNames() {
        return localClubNames;
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
