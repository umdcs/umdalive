package com.example.kevin.umdalive;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity{

    //Will stay here in the model
    public static UserInformationModel getUser(String userData) {
        try {
            JSONObject user = new JSONObject(userData);
            ArrayList<ClubInformationModel> list = new ArrayList<ClubInformationModel>();
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
}


