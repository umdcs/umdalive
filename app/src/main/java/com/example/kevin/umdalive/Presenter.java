package com.example.kevin.umdalive;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by rgebh_000 on 3/15/2017.
 *
 * Presenter class to communicate between views and models
 */

public class Presenter {
    private LoginActivity log;
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
        return restModel.restPost(task, data);
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param data data to be handle
     * @return Currently returns a string to represent what could be returned
     */
    public String restPut(String task, String data) {
        return restModel.restPut(task, data);
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param toDelete what will be deleted
     * @return Currently returns a string to represent what could be returned
     */
    public String restDelete(String task, String toDelete) {
        return restModel.restDelete(task, toDelete);
    }

    /**
     * Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param toGet data to get
     * @return Currently returns a string to represent what could be returned
     */
    public  String restGet(String task, String toGet) {
        return restModel.restGet(task, toGet);
    }

    /**
     * Used to create a new club object to be sent to the server.
     * @param clubName name of club
     * @param userName name of admin
     * @param keyWords tags
     * @param description description of club
     * @param initialPost first post
     * @return string version of the JSON package.
     */
    public String makeClub(String clubName, String userName, String keyWords, String description, String initialPost){
        return CreateClub.makeClub(clubName, userName, keyWords, description, initialPost);
    }

    /**
     * For MainActivity
     * @param userData
     * @return
     */
    public UserInformationModel getMainUser(String userData){
        return MainActivity.getUser(userData);
    }

    /**
     * For MainActivity
     * @param jsonString
     * @return
     */
    public ArrayList<String> refreshPosts(String jsonString) {
        return MainActivity.refreshPosts(jsonString);
    }

    /**
     * For MainActivity
     * @param posts
     * @return
     */
    public String displayPosts(ArrayList<String> posts){
        return MainActivity.displayPosts(posts);
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
     * @param jsonString string o json data
     */
    public void setDisplayClubInfo(String jsonString){
        try {
            JSONObject object = new JSONObject(jsonString);
            String clubFromServer = object.getString("club");
            String descriptionFromServer = object.getString("description");
            String userNameFromServer = object.getString("username");
            String keywordFromServer = object.getString("keywords");

//            DisplayClub.setClubName(clubFromServer);
//            DisplayClub.setAdministrator(userNameFromServer);
//            DisplayClub.setDescription(descriptionFromServer);
//            DisplayClub.setKeywords(keywordFromServer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public void googleSignIn(){
log.onCreate();

    }

    }




