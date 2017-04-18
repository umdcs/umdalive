package com.example.kevin.umdalive;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests if the presenter does everything properly.
 */

public class PresenterUnitTest {
    private MainView activity = new MainView();
    private RestModel restModel = new RestModel();
    private Presenter presenter1 = new Presenter(activity);
    private Presenter presenter2 = new Presenter();

    @Test
    public void testEquals() {
        assertFalse(presenter1.equals(presenter2));
        presenter2.setActivity(activity);
        assertTrue(presenter2.equals(presenter1));
    }

    @Test
    public void methodTests(){

        ClubInformationModel newClub = new ClubInformationModel("Club", "User", "Keyword", "Description");

        //REST tests
        assertTrue(presenter1.restGet("getAllClubs", "").equals(restModel.restGet("getAllClubs", "")));
        assertTrue(presenter1.restPut("putNewClub", newClub.jsonStringify()).equals(restModel.restPut("putNewClub", newClub.jsonStringify())));
        assertTrue(presenter1.restDelete("","").equals(null));
        assertTrue(presenter1.restPost("","").equals(null));

        //makeClub
        assertTrue(presenter1.makeClub("Club", "User", "Keyword", "Description").equals(newClub));

        //getMainUser
        String userData = restModel.restGet("getUserData", "");
        ArrayList<ClubInformationModel> clubs = new ArrayList<>();
        UserInformationModel user = new UserInformationModel("Billy Joe", "umdAlive1@gmail.com", "2018", "computer science", clubs);
        UserInformationModel newUser = presenter1.getMainUser(userData);
        assertTrue(newUser.equals(user));

        //refreshPosts
        ArrayList<PostInformationModel> posts = presenter1.refreshPosts(presenter1.restGet("getRecentPosts", ""));
        ArrayList<PostInformationModel> mainPosts = MainActivity.refreshPosts(restModel.restGet("getRecentPosts", ""));
        assertTrue(posts.equals(mainPosts));
    }

}
