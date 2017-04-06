package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivity{

    public static String jsonRequest(String club, String title, String time, String date, String location){
        JSONObject jsonParam = null;

        String post = title + time + date + location;

        try {
            jsonParam = new JSONObject();
            jsonParam.put("clubToPost", club);
            jsonParam.put("postToDisplay", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = jsonParam.toString();
        Log.d("DEBUG [PUT]:", jsonString);
        return jsonString;
    }

}

