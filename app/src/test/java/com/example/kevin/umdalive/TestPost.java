package com.example.kevin.umdalive;

import com.example.kevin.umdalive.Models.PostInformationModel;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests to see if posts are created properly.
 */

public class TestPost {

    private PostInformationModel emptyPost = new PostInformationModel("", "", "", "", "", "");
    private PostInformationModel newPost = new PostInformationModel("", "", "", "", "", "");
    private PostInformationModel notThisPost = new PostInformationModel("club", "title", "time", "date", "location", "description");
    private PostInformationModel finalPost = new PostInformationModel
            ("Software Engineering", "Unit Testing", "4:00 pm", "April 13", "MWAH 177", "We Need To Test Our POJOs");


    @Test
    public void testEquals() {

        assertTrue(emptyPost.equals(newPost));

        newPost.setClub("Software Engineering");
        newPost.setTitle("Unit Testing");
        newPost.setTime("4:00 pm");
        newPost.setDate("April 13");
        newPost.setLocation("MWAH 177");
        newPost.setDescription("We Need To Test Our POJOs");

        assertFalse(emptyPost.equals(notThisPost));
        assertTrue(newPost.equals(finalPost));

        newPost.setLocation("Kirby Ballroom");

        assertFalse(newPost.equals(finalPost));

    }
}
