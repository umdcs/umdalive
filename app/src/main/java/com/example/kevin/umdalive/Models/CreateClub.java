package com.example.kevin.umdalive.Models;

/**
 * This is the model class for CreateClubView
 */

public class CreateClub{

    /**
     *
     * @param clubName name of club
     * @param userName user creating club
     * @param keyWords keywords to id club
     * @param description of club
     * @return String representation of new club
     */
    public static String makeClub(String clubName, String userName, String keyWords, String description){
        ClubInformationModel newClub = new ClubInformationModel(clubName, userName, keyWords, description);
        return newClub.jsonStringify();
    }
}
