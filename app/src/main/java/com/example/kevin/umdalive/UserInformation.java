package com.example.kevin.umdalive;

/**
 * Created by Luke on 11/8/2016.
 */

public class UserInformation {

    private String local_userName;
    private Club[] local_clubsSubscibed;

    public UserInformation(String temp_local_username, String temp_major, String temp_graduationDate, Club[] temp_clubs_subscribed){
        local_userName = temp_local_username;
        local_clubsSubscibed = temp_clubs_subscribed;
    }

}
