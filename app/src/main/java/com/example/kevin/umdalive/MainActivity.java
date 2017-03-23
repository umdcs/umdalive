package com.example.kevin.umdalive;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity {

    public static UserInformationModel getUser(String userData) {
        try {
            JSONObject user = new JSONObject(userData);
            ArrayList<ClubInformationModel> list = new ArrayList<>();
            JSONArray jArray = user.getJSONArray("clubs");

            if (jArray != null) {
                int len = jArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject clubObject = jArray.getJSONObject(i);
                    //create new club object from server data
                    ClubInformationModel tempClub = new ClubInformationModel(clubObject.get("clubname").toString(),
                            clubObject.get("username").toString(), clubObject.get("keywords").toString(),
                            clubObject.get("description").toString());

                    Log.d("club name: ", clubObject.get("clubname").toString());
                    //add new club object to array
                    list.add(tempClub);
                }
            }
            Log.d("userData", userData);
            //will obtain json string from textview and take value out from string
            return new UserInformationModel(user.getString("name"), user.getString("major"),
                    user.getString("email"), user.getString("graduationDate"), list);
        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> refreshPosts(String jsonString) {
        ArrayList<String> list = new ArrayList<>();
        //converting Json string to ArrayList
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray jsonArray = object.getJSONArray("items");
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    list.add(jsonArray.get(i).toString());
                    Log.d(jsonArray.get(i).toString(), jsonArray.get(i).toString());
                }
                Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String displayPosts(ArrayList<String> list) {
        String displayPosts = "";
        for (int i = 0; i < list.size(); i++) {
            displayPosts += " \n" + list.get(i);
        }
        return displayPosts;
    }
}


