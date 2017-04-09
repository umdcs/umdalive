package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class PostingActivity{

    /**
     * Function to create a JSON object of a club
     * JSON object is then made into a string and returned
     *
     * @return jsonString string form of JSON object Club
     */
    public String jsonStringify(String club, String title, String time, String date, String location, String addInfo) {
        JSONObject jsonString = null;
        try {
            jsonString = new JSONObject();
            jsonString.put("clubname", club);
            jsonString.put("title", title);
            jsonString.put("time", time);
            jsonString.put("date", date);
            jsonString.put("location", location);
            jsonString.put("description", addInfo);
            //jsonString.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonString.toString());

        return jsonString.toString();
    }

}

