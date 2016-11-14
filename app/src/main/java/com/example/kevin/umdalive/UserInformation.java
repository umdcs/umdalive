package com.example.kevin.umdalive;

import java.util.ArrayList;

/**
 * Created by Luke on 11/8/2016.
 */

public class UserInformation {

    private String local_userName;
    private ArrayList<Club> local_clubsSubscibed;

    /*
    Sets the local data that the app will run off of, will use this method when user signs in
     */
    public UserInformation(String temp_local_username, String temp_major, String temp_graduationDate, Club[] temp_clubs_subscribed){
        local_userName = temp_local_username;
        local_clubsSubscibed = new ArrayList<Club>();
        for(Club x : temp_clubs_subscribed)
        {
            local_clubsSubscibed.add(x);
        }
    }
    /*
    get method for username of user.
     */
    public String getLocalUsername()
    {
        return local_userName;
    }
    /*
    get for ArrayList of clubs
    */
    public ArrayList<Club> getLocalClubs()
    {
        return local_clubsSubscibed;
    }
    /*
    Edits username
     */
    public void setLocal_userName(String name)
    {
    local_userName = name;
    }
    /*
    Takes in a new arraylist of clubs then
    Removes all current clubs and then replaces objects in arraylist temp
    and addes them to local array list
     */
    public void setLocalClubs(ArrayList<Club> temp_clubs_subscribed)
    {
        for(Club x : local_clubsSubscibed){
            local_clubsSubscibed.remove(x);
        }
        for(Club x : temp_clubs_subscribed)
        {
            local_clubsSubscibed.add(x);
        }
    }
}
