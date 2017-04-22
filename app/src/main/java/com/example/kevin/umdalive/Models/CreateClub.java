package com.example.kevin.umdalive.Models;

/**
 * This is the model class for CreateClubView
 */

public class CreateClub{
    public static String makeClub(String clubName, String userName, String keyWords, String description){
        ClubInformationModel newClub = new ClubInformationModel(clubName, userName, keyWords, description);
        return newClub.jsonStringify();
    }
}
