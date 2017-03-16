package com.example.kevin.umdalive;

import android.view.View;

/**
 * Created by rgebh_000 on 3/15/2017.
 * Presenter class to communicate between views and models
 */

public class Presenter {
    private View view;
    private RestModel restModel;
    private UserInformationModel userInformation;


    /**
     * constructor for presenter
     * @param incomingView view that created presenter.
     *
     * creates a RestModel for node communication
     */
    public Presenter (View incomingView) {
    view = incomingView;
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
     *  Rest Function sends parameters to RestModel where they are dealt with using switch statement.
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
     *  Rest Function sends parameters to RestModel where they are dealt with using switch statement.
     *
     * @param task to be performed
     * @param toGet data to get
     * @return Currently returns a string to represent what could be returned
     */
    public String restGet(String task, String toGet) {
        restModel.restGet(task, toGet);
        return "restGet()String";
    }


    /**
     * Sets user info called from signUpActivityView
     *
     * sets by using constructor from UserInformationModel
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

        userInformation = new UserInformationModel(name, password, email, major, gradDate);

    }

}
