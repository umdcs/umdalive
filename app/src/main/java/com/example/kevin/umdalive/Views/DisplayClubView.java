package com.example.kevin.umdalive.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.kevin.umdalive.Presenters.Presenter;
import com.example.kevin.umdalive.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is supposed to display the club that is selected from all clubs but the previous group never did that so it's just
 * blank spaces.
 */
public class DisplayClubView extends AppCompatActivity {


    private Presenter presenter;

    private String clubName;
    private String description;
    private String keywords;
    private String administrator;

    private TextView clubNameSetText;
    private TextView descriptionSetText;
    private TextView keywordSetText;
    private TextView administratorSetText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_club_view);
        presenter = new Presenter(this);
        setView();
    }

    private void setView(){
        String jsonResponse = presenter.restGet("getClub", "");

        try {
            JSONObject object = new JSONObject(jsonResponse);
            JSONObject club = (JSONObject) object.get("clubData");
            clubName = club.get("clubName").toString();
            description = club.get("description").toString();
            keywords = club.get("keywords").toString();
            administrator = club.get("username").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        clubNameSetText = (TextView) findViewById(R.id.display_club_name);
        descriptionSetText = (TextView) findViewById(R.id.display_club_description);
        keywordSetText = (TextView) findViewById(R.id.display_clubs_keyword);
        administratorSetText = (TextView) findViewById(R.id.display_clubs_administrator);

        clubNameSetText.setText(clubName);
        clubNameSetText.setTextSize(45);
        descriptionSetText.setText(description);
        keywordSetText.setText(keywords);
        administratorSetText.setText(administrator);
    }
}
