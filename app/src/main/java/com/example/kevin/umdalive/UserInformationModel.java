package com.example.kevin.umdalive;


import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class UserInformationModel extends AppCompatActivity {

    private String name;
    private String email;
    private String major;
    private String gradDate;


    /**
     * not using these three fields yet to keep things simple
     */

    //private ArrayList<String> localClubNames;
    //private ArrayList<Club> localClubsSubscribed;
    //private ArrayList<String> localPostsSubscribed;


    /**
     * Copied from old code
     * not sure if needed
     */
    public UserInformationModel(){
        name = "";
        //localClubsSubscribed = new ArrayList<Club>();
        //localClubNames = new ArrayList<String>();
        //localPostsSubscribed = new ArrayList<String>();
    }


    /**
     * constructor for user info
     *
     * not currently using post and clubs subscribed for simplicity
     * @param name
     * @param major
     * @param email
     * @param gradDate
     *
     */
    public UserInformationModel(String name, String major, String email, String gradDate) {//, ArrayList<Club> temp_clubs_subscribed){
        this.name = name;
        this.email = email;
        this.major = major;
        this.gradDate = gradDate;
        //localPostsSubscribed = new ArrayList<String>();
        //localClubsSubscribed = temp_clubs_subscribed;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public String getGradDate() {
        return gradDate;
    }



}
