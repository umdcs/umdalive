package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class AllClubsView extends AppCompatActivity {

    ListView listView;
    //Presenter presenter;  //will be used when presenter class is merged
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_clubs);
        //this is the ListView for the all clubs activity listing all of the clubs
        listView = (ListView) findViewById(R.id.list2);
        //gets the list of clubs
        ArrayList<String> values = MainActivity.getUserInformation().getLocal_club_Names();


        try {
            /* Im not entirely sure but I think the way that this works is it uses a default Android list layout(simple_list_item_1)
             * to fill in the space of the listView with the contents of values.
             */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            //applies the adapter we just created to the ListView
            listView.setAdapter(adapter);
            /*
             * The following is what happens when a user selects something the ListView
             */
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String  itemValue  = (String) listView.getItemAtPosition(position);
                    DisplayClub.setClubName(itemValue);
                    //creates an intent that will become the view of the selected club
                    Intent intent = new Intent(AllClubsView.this, DisplayClub.class);
                    //Gets the info of the club to display on the DisplayClub activity
                    try {
                        JSONObject jsonParam = null;
                        //Create JSONObject
                        jsonParam = new JSONObject();
                        //adds the name of the club into the JSON object
                        jsonParam.put("club", itemValue);
                        //not sure why they used PUT here... shouldn't it be GET? Nothing is being added to the club/list of clubs at this point.
                        String jsonResponse = new AllClubs.HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "PUT", jsonParam.toString()).get();
                        JSONObject object = new JSONObject(jsonResponse);
                        //gets off the club info from the jsonResponse
                        String clubFromServer = object.getString("club");
                        String descriptionFromServer = object.getString("description");
                        String userNameFromServer = object.getString("username");
                        String keywordFromServer = object.getString("keywords");
                        //sets the club parameters on the DisplayClub view
                        DisplayClub.setClubName(clubFromServer);
                        DisplayClub.setAdministrator(userNameFromServer);
                        DisplayClub.setDescription(descriptionFromServer);
                        DisplayClub.setKeywords(keywordFromServer);

                        Log.d(clubFromServer,clubFromServer);
                        Log.d(descriptionFromServer,descriptionFromServer);
                        Log.d(userNameFromServer,userNameFromServer);
                        Log.d(keywordFromServer,keywordFromServer);
                        Log.d(jsonResponse,jsonResponse);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    startActivity(intent);

                    // Show Alert (I think this is for debugging, idk why the user would need to see this)
                    Toast.makeText(getApplicationContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }

            });

        }catch (Exception e)
        {
        }}
}
