package com.example.kevin.umdalive;

import android.view.View;

/**
 * Created by rgebh_000 on 3/15/2017.
 * Presenter class to communicate between views and models
 */

public class Presenter {
    private View view;
    //private RestModel restModel;
    private UserInformationModel userInformation;

    public Presenter (View incomingView) {
    view = incomingView;
    //restModel = new restModel(this);

    }

    public void restPost() {
        //restModel.restPost();
    }

    public void restPut() {
        //restModel.restPut();
    }

    public void restDelete() {
        //restModel.restDelete();
    }

    public void restGet() {
        //restModel.restGet();
    }

    public void setUserInfo(String name, String email, String major, String gradDate) {

        userInformation = new UserInformationModel(name, email, major, gradDate);

    }

}
