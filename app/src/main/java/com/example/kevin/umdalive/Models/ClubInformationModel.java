package com.example.kevin.umdalive.Models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ryan on 3/17/2017.
 *
 * Class to define a club.
 *
 * Constructor requires a club name but no other data to instantiate
 */

@SuppressWarnings("unused")
public class ClubInformationModel {
    private String clubName;
    private String userName;
    private String keyWords;
    private String description;

    /**
     * constructor
     *
     * @param clubName name of club
     * @param userName user creating club
     * @param keyWords keywords to id club
     * @param description of club
     */

    public ClubInformationModel(String clubName, String userName, String keyWords, String description) {
        this.clubName = clubName;
        this.userName = userName;
        this.keyWords = keyWords;
        this.description = description;
    }

    /**
     * setter for clubName
     *
     * @param clubName string of new club
     */
    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    /**
     * getter for clubName
     *
     * @return clubName
     */
    public String getClubName() {
        return clubName;
    }

    /**
     * setter for userName
     *
     * assuming this means whoever created the club
     *
     * @param userName String of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * getter for userName
     *
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setter for keywords
     *
     * @param keyWords string of new keyword with '#' in front of it
     */
    public void setKeyWords(String keyWords){
        this.keyWords = keyWords;
    }

    /**
     * getter for keywords
     *
     * @return keywords
     */
    public String getKeyWords() {
        return keyWords;
    }

    /**
     * setter for description
     *
     * @param description new string for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter Description
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Function to create a JSON object of a club
     * JSON object is then made into a string and returned
     *
     * @return jsonString string form of JSON object Club
     */
    public String jsonStringify() {
        JSONObject jsonString = null;
        try {
            jsonString = new JSONObject();
            jsonString.put("clubName", clubName);
            jsonString.put("username", userName);
            jsonString.put("keywords", keyWords);
            jsonString.put("description", description);
            //jsonString.put("post", post);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG:", jsonString.toString());

        return jsonString.toString();
    }

    public static boolean checkAscii(String str){
        boolean isError = false;
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i++){
            Log.d("checking: " , Integer.toString((int)charArray[i]));
            if(((int) charArray[i]) <  32 || (int) charArray[i] > 126) isError = true;
        }
        return isError;
    }

    public static boolean checkClubNameAscii(String str){
        boolean isError = false;
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i++){
            Log.d("checking: " , Integer.toString((int)charArray[i]));
            if(!((46 < charArray[i] && charArray[i] < 58) ||
                    (64 < charArray[i] && charArray[i] < 91) ||
                    (96 < charArray[i] && charArray[i] < 123))) isError = true;
        }
        return isError;
    }
}
