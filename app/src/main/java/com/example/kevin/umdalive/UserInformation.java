package com.example.kevin.umdalive;


import java.util.ArrayList;

/**
 * Created by Luke on 11/8/2016.
 */

public class UserInformation {

    private String name;
    private String email;
    private String major;
    private String gradDate;


    private ArrayList<CreateClub> local_clubs_subscribed;
    private ArrayList<String> local_posts_subscribed;

    //public final String serverAddress = "https://lempo.d.umn.edu:5001";
    public final String serverAddress = "http://10.0.2.2:5000";
    private ArrayList<String> local_club_Names;

    public String getServerAddress(){
        return serverAddress;
    }

    /*
    Sets the local data that the app will run off of, will use this method when user signs in
     */
    public UserInformation(String temp_local_username, String temp_major, String emai, String temp_graduationDate, ArrayList<CreateClub> temp_clubs_subscribed){
        name = temp_local_username;
        email = emai;
        major = temp_major;
        gradDate = temp_graduationDate;
        local_posts_subscribed = new ArrayList<String>();
        local_clubs_subscribed = temp_clubs_subscribed;

    }

    public UserInformation(){
        name = "";
        local_clubs_subscribed = new ArrayList<CreateClub>();
        local_club_Names = new ArrayList<String>();
        local_posts_subscribed = new ArrayList<String>();
    }
    /*
        get methods for user information
    */
    public String getLocalUsername() {return name;}
    public String getEmail(){return email;}
    public String getMajor(){return major;}
    public String getGradDate(){return gradDate;}
    public ArrayList<CreateClub> getLocalClubs(){return local_clubs_subscribed;}

    /*
        Takes in a new arraylist of most recent posts then
        Removes all current posts and then replaces objects in arraylist temp
        and addes them to local array list
    */
    public void setLocalPosts(ArrayList<String> temp_posts_subscribed)
    {
        while(local_posts_subscribed.size() !=0){
            local_posts_subscribed.remove(0);
        }
        for(String x : temp_posts_subscribed)
        {
            local_posts_subscribed.add(x);
        }
    }

    public void setLocal_club_Names(ArrayList<String> names)
    {
        local_club_Names = names;
    }
    public ArrayList<String> getLocal_club_Names()
    {
        return local_club_Names;
    }



}
