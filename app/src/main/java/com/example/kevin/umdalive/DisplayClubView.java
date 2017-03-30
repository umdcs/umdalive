package com.example.kevin.umdalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * This class is supposed to display the club that is selected from all clubs but the previous group never did that so it's just
 * blank spaces.
 */
public class DisplayClubView extends AppCompatActivity {

    private static String clubName;
    private static String description;
    private static ArrayList<String> posts;
    private static String administrator;
    private static String keywords;

    private Presenter presenter;

    protected void onCreate(Bundle savedInstanceState) {

        presenter = new Presenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_club_view);
        // ALSO, nothing the user puts in here saves at this point so thats something that definitely should be fixed.
        TextView clubNameSetText = (TextView) findViewById(R.id.display_club_name);
        TextView descriptionSetText = (TextView) findViewById(R.id.display_club_description);
        TextView keywordSetText = (TextView) findViewById(R.id.display_clubs_keyword);
        TextView administratorSetText = (TextView) findViewById(R.id.display_clubs_administator);


        //presenter.getClub();
        clubNameSetText.setText(clubName);
        descriptionSetText.setText(description);
        keywordSetText.setText(keywords);
        administratorSetText.setText(administrator);
    }
}
