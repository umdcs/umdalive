package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivity{

    public String jsonRequest(String club, String post){
        JSONObject jsonParam = null;
        try {
            //Create JSONObject here
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

