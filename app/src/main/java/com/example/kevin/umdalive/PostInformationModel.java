package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew Miller on 4/9/2017.
 */

public class PostInformationModel {
    private String club;
    private String title;
    private String time;
    private String date;
    private String location;
    private String description;

    public PostInformationModel(String jsonData){
        try {
            JSONObject postInfo = new JSONObject(jsonData);
            club = postInfo.get("club").toString();
            title = postInfo.get("title").toString();
            time = postInfo.get("time").toString();
            date = postInfo.get("date").toString();
            location = postInfo.get("location").toString();
            description = postInfo.get("description").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public PostInformationModel(String club, String title, String time, String date, String location, String description){
        this.club = club;
        this.title = title;
        this.time = time;
        this.date = date;
        this.location = location;
        this.description = description;

    }

    public String getClub() {
        return club;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Function to create a JSON object of a post
     * JSON object is then made into a string and returned
     *
     * @return jsonString string form of JSON object Club
     */
    public String jsonStringify() {
        JSONObject jsonString = null;
        try {
            jsonString = new JSONObject();
            jsonString.put("clubName", club);
            jsonString.put("title", title);
            jsonString.put("time", time);
            jsonString.put("date", date);
            jsonString.put("location", location);
            jsonString.put("description", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonString.toString());

        return jsonString.toString();
    }

    /**
     * Static version of stringify used for sending an uninitialized PostInformationModel object through a put request
     *
     * @return jsonString string form of JSON object Club
     */
    public static String jsonStringify(String club, String title, String time, String date, String location, String description) {
        JSONObject jsonString = null;
        try {
            jsonString = new JSONObject();
            jsonString.put("clubName", club);
            jsonString.put("title", title);
            jsonString.put("time", time);
            jsonString.put("date", date);
            jsonString.put("location", location);
            jsonString.put("description", description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonString.toString());

        return jsonString.toString();
    }
}
