package com.example.kevin.umdalive;

import android.util.Log;

/**
 * Created by Jennacide on 4/12/2017.
 */

public class ClubInformationUnitTest {

public void TestClub1 (){
    String clubName1="Sassy Club";
    String username1="Sassysquatch";
    String keywords1="Rhonda";
    String description1="fabulous";
    CreateClub testClub1=null;
    testClub1.makeClub(clubName1, username1, keywords1, description1);
    ClubInformationModel testOne = new ClubInformationModel(clubName1, username1, keywords1, description1);
    String nameMatch1;
    String usernameMatch1;
    String keywordsMatch1;
    String descriptionMatch1;

    if(testOne.getClubName().toString().equals(clubName1))
    {
        String thing1 = "If this displays, then the club names match!";
       // Log.d(thing1);
    }
}

}
