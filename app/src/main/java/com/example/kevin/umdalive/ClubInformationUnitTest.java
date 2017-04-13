package com.example.kevin.umdalive;

import android.util.Log;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;

/**
 * Created by Jennacide on 4/12/2017.
 */

public class ClubInformationUnitTest {
    String clubName1="Sassy Club";
    String username1="Sassysquatch";
    String keywords1="Rhonda";
    String description1="fabulous";
    ClubInformationModel testOne = new ClubInformationModel(clubName1, username1, keywords1, description1);


    @Test
    public void TestClub1 (){
//        String clubName1="Sassy Club";
//        String username1="Sassysquatch";
//        String keywords1="Rhonda";
//        String description1="fabulous";
//        //CreateClub testClub1=null;
//        //testClub1.makeClub(clubName1, username1, keywords1, description1);
//        ClubInformationModel testOne = new ClubInformationModel(clubName1, username1, keywords1, description1);

        assertTrue(clubName1.equals(testOne.getClubName()));
        assertTrue(username1.equals(testOne.getUserName()));
        assertTrue(keywords1.equals(testOne.getKeyWords()));
        assertTrue(description1.equals(testOne.getDescription()));
        if(testOne.getClubName().toString().equals(clubName1)) {
            Log.d("Club Test number one", "If this displays, then the club names match!");
        }

        if(testOne.getUserName().toString().equals(username1)){
            Log.d("Club test number two", "If this string dislays, then the club usernames match!");
        }
        if(testOne.getDescription().toString().equals(description1)) {
            Log.d("Club test number three", "If this displays, then the club descriptions match!");
        }

        if(testOne.getKeyWords().toString().equals(keywords1)){
            Log.d("Club test number four", "If this displays, then the club keywords match!");
        }
    }

    public boolean equals(ClubInformationModel testClub)
    {
        boolean isEqual = false;

        if(testClub.getUserName() == this.clubName1) {
            isEqual = true;
        }
        if(testClub.getClubName() == this.username1) {
            isEqual =true;
        }
        if(testClub.getKeyWords() == this.keywords1){
            isEqual = true;
        }
        if(testClub.getDescription() == this.description1){
            isEqual=true;
        }

        return isEqual;
    }



}
