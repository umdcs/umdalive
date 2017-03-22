package com.example.kevin.umdalive;

import android.app.Activity;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by rgebh_000 on 3/15/2017.
 *
 * Presenter class to communicate between views and models
 */

public class Presenter {
    private Activity activity;
    private RestModel restModel;
    private AllClubs allClubs;

    /**
     * Constructor for presenter
     * @param incomingActivity view that created presenter.
     *
     * creates a RestModel for node communication
     */
    public Presenter (Activity incomingActivity) {
        restModel = new RestModel();
        allClubs = new AllClubs();
        activity = incomingActivity;
    }

    /**
     * Constructor for testing RestModel that requires no view
     */
    public Presenter() {
        allClubs = new AllClubs();
        restModel = new RestModel();
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param data data to be handle
     * @return Currently returns a string to represent what could be returned
     */
    public String restPost(String task, String data) {
        restModel.restPost(task, data);
        return "restPost()String";
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param data data to be handle
     * @return Currently returns a string to represent what could be returned
     */
    public String restPut(String task, String data) {
        restModel.restPut(task, data);
        return "restPut()String";
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param toDelete what will be deleted
     * @return Currently returns a string to represent what could be returned
     */
    public String restDelete(String task, String toDelete) {
        restModel.restDelete(task, toDelete);
        return "restDelete()String";
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param toGet data to get
     * @return Currently returns a string to represent what could be returned
     */
    public  String restGet(String task, String toGet) {
        restModel.restGet(task, toGet);
        return "restGet()String";
    }








    /**
     * Sets user info called from signUpActivityView
     *
     * Sets by using constructor from UserInformationModel
     *
     * not sure if this is the appropriate way to do all this.
     *
     * @param name of new user
     * @param password of new user
     * @param email of new user
     * @param major of new user
     * @param gradDate of new user
     */
    public void setUserInfo(String name, String password, String email, String major, String gradDate) {
        //geting loocal post subscribed

        ArrayList<String> localPostSubscribed= this.getClubNames();


        UserInformationModel newUser = new UserInformationModel(name, password, email, major, gradDate,localPostSubscribed);
        //send to rest function and then to server to store data
        //confirmation to user?
    }

    /**
     * Sets the infomation from the user selected club(in AllClubsView) that is to be displayed in DisplayClubView
     * !!!!This was just copy/pasted from AllClubsView, will need to be changed at some point!!!! -Andy
     * @param jsonString
     */
    public void setDisplayClubInfo(String jsonString){
        try {
            JSONObject object = new JSONObject(jsonString);
            String clubFromServer = object.getString("club");
            String descriptionFromServer = object.getString("description");
            String userNameFromServer = object.getString("username");
            String keywordFromServer = object.getString("keywords");

            DisplayClub.setClubName(clubFromServer);
            DisplayClub.setAdministrator(userNameFromServer);
            DisplayClub.setDescription(descriptionFromServer);
            DisplayClub.setKeywords(keywordFromServer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets club info called from createClubView
     *
     * @param clubName name of club
     * @param userName name of user creating club
     * @param keyWords keywords to be used in tag (#rocketry or #MPIRG)
     * @param description brief description of the club
     */
    public void setClubInfo(String clubName, String userName, String keyWords, String description) {
       ClubInformationModel  newClub = new ClubInformationModel(clubName, userName, keyWords, description);
        //send to rest function and then to server to store data
        //confirmation to user?
    }

    /**
     *gets all the club names
     */
    public ArrayList<String> getClubNames(){
        return allClubs.getClubNames(restGet(new String("getAllClubs"), new String("")));
    }

    /**
     * Stubbed function to communicate with server and retrieve one club by name
     *
     * @param clubName to retrieve info of
     */
    public void getClub(String clubName){
        //get info from server of club
        //send to view all data made by club
    }

    /**
     * Stubbed function to communicate with server and retrieve all posts from one club by name
     *
     * @param clubName to retrieve posts of
     */
    public void getClubsPosts(String clubName) {
        //get info from server of all posts made by one club
        //send to view a list of all posts.
    }


    }




}
