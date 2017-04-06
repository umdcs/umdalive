package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivity{

    public static String jsonRequest(String club, String title, String time, String date, String location){
        JSONObject jsonParam = null;

        String post = title + time + date + location; // this will smush the words together but its just a start - Ryan

        try {
            jsonParam = new JSONObject();
            jsonParam.put("clubToPost", club);
            jsonParam.put("postToDisplay", post);
            // would like to see .put for each parameter instead of the smushed post - Ryan
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonString = jsonParam.toString();
        Log.d("DEBUG [PUT]:", jsonString);
        return jsonString;
    }

}

