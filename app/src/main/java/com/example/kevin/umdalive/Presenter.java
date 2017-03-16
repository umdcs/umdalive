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
    //restModel = new restModel();

    }

    public String restPost(String task, String data) {
        //restModel.restPost(task, data);
        return "restPost()String";
    }

    public String restPut(String task, String data) {
        //restModel.restPut(task, data);
        return "restPut()String";
    }

    public String restDelete(String task, String toDelete) {
        //restModel.restDelete(task, toDelete);
        return "restDelete()String";
    }

    public String restGet(String task, String toGet) {
        //restModel.restGet(task, toGet);
        return "restGet()String";
    }

    public void setUserInfo(String name, String password, String email, String major, String gradDate) {

        userInformation = new UserInformationModel(name, password, email, major, gradDate);

    }

}
