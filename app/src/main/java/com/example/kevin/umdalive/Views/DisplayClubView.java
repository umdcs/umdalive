package com.example.kevin.umdalive.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        try {
            clubName = getIntent().getStringExtra(AllClubsView.CLUB_NAME);
            String jsonResponse = presenter.restGet("getClub", clubName);
            Log.d("DisplayClub response: ",jsonResponse);
            JSONObject clubObject = new JSONObject(jsonResponse);
            description = clubObject.get("description").toString();
            keywords = clubObject.get("keywords").toString();
            administrator = clubObject.get("username").toString();
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
