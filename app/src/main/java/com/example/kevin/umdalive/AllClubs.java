package com.example.kevin.umdalive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Andy Miller 3/21/2017
 */

public class AllClubs{
    /**
     * This is used to convert a JSON response into a list of strings containing the names of the clubs.
     * ...I really hope this works :)
     * @param jsonResponse the response from the REST get call to the server retrieving all clubs.
     * @return ArrayList of all club names
     */
    public ArrayList<String> getClubNames(String jsonResponse) {
        ArrayList<String> clubArray = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            for(int i = 0; !jsonArray.isNull(i); ++i){
                JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                clubArray.add(jsonObject.getString("club"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clubArray;
    }
}


