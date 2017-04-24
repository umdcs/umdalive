package com.example.kevin.umdalive.Presenters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.example.kevin.umdalive.Models.AllClubs;
import com.example.kevin.umdalive.Models.ClubInformationModel;
import com.example.kevin.umdalive.Models.CreateClub;
import com.example.kevin.umdalive.Models.LoginActivity;
import com.example.kevin.umdalive.Models.MainActivity;
import com.example.kevin.umdalive.Models.PostAdapter;
import com.example.kevin.umdalive.Models.PostInformationModel;
import com.example.kevin.umdalive.Models.RestModel;
import com.example.kevin.umdalive.Models.UserInformationModel;

import java.util.ArrayList;

/**
 * Created by ryan_000 on 3/15/2017.
 *
 * Presenter class to communicate between views and models
 */

public class Presenter {
    private Activity activity;
    private RestModel restModel;



    /**
     * Constructor for presenter
     * @param incomingActivity view that created presenter.
     *
     * creates a RestModel for node communication
     */
    public Presenter(Activity incomingActivity) {
        restModel = new RestModel();
        activity = incomingActivity;
    }

    /**
     * Constructor for testing RestModel that requires no view
     */
    public Presenter() {
        activity = null;
        restModel = new RestModel();
    }

    private Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private RestModel getRestModel() {
        return restModel;
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
     * @return string version of the JSON package.
     */
    public String makeClub(String clubName, String userName, String keyWords, String description){
        return CreateClub.makeClub(clubName, userName, keyWords, description);//, initialPost);
    }

    /**
     * For MainActivity
     * @param userData to get
     * @return user
     */
    public UserInformationModel getMainUser(String userData){
        return MainActivity.getUser(userData);
    }

    /**
     * For MainActivity
     * @param jsonString restGet results for most recent posts
     * @return list of post objects
     */
    public ArrayList<PostInformationModel> refreshPosts(String jsonString) {
        return MainActivity.refreshPosts(jsonString);
    }

    public void putPost(String club, String title, String time, String date, String location, String addInfo, String image){
        restPut("putNewPost", PostInformationModel.jsonStringify(club, title, time, date, location, addInfo, image));
    }

    /**
     * gets all the club names
     * @return list of all clubs
     */
    public ArrayList<String> getClubNames(){
        return AllClubs.getClubNames(restGet("getAllClubs", ""));
    }

    /**
     * used to get all clubs with a keyword
     * @param keyword to search
     * @return clubs containing keywords
     */
    public ArrayList<String> getSearchClubNames(String keyword){
        return AllClubs.getClubNames(restGet("getSearchAllClubs", keyword));
    }

    public PostAdapter getPostAdapter(ArrayList<PostInformationModel> posts, RecyclerView rView){
        return new PostAdapter(posts, rView);
    }

    /**
     * testing
     * @param presenter to compare
     * @return if presenter is equal
     */
    public boolean equals(Presenter presenter){
        boolean isEqual = true;
        if(!activity.equals(presenter.getActivity())) isEqual = false;
        if(!restModel.equals(presenter.getRestModel())) isEqual = false;
        return isEqual;
    }

    /**
     * Checks if a string is a valid form of input
     * @param str input
     * @return false if invalid
     */
    public boolean isClubInfoValid(String str){
        return ClubInformationModel.checkAscii(str);
    }

    /**
     * Checks if a string is a valid form of input
     * @param str input
     * @return false if invalid
     */
    public boolean isClubNameValid(String str){
        return ClubInformationModel.checkClubNameAscii(str);
    }

    /**
     * Checks if a string is a valid form of input
     * @param str input
     * @return false if invalid
     */
    public boolean isPostInfoValid(String str){
        return PostInformationModel.checkAscii(str);
    }

public void userData(String major, String grad){
    LoginActivity log=new LoginActivity();

String email=log.getmEmail();

    String name=log.getmFullName();

    UserInformationModel User=new UserInformationModel(name,major,email,grad);


}




}