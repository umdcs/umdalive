package com.example.kevin.umdalive;

/**
 * This is the model class for CreateClubView
 */

public class CreateClub{
    public static String makeClub(String clubName, String userName, String keyWords, String description, String initialPost){
        ClubInformationModel newClub = new ClubInformationModel(clubName, userName, keyWords, description, initialPost);
        return newClub.jsonStringify();
    }
}
