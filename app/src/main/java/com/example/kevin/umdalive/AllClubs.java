package com.example.kevin.umdalive;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * This is the activity that displays the list of all clubs.
 */

public class AllClubs extends Activity {
    ListView listView;
    UserInformation this_user = new UserInformation();
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
                    Intent intent = new Intent(AllClubs.this, DisplayClub.class);
                    //Gets the info of the club to display on the DisplayClub activity
                    try {
                        JSONObject jsonParam = null;
                            //Create JSONObject
                            jsonParam = new JSONObject();
                            //adds the name of the club into the JSON object
                            jsonParam.put("club", itemValue);
                        //not sure why they used PUT here... shouldn't it be GET? Nothing is being added to the club/list of clubs at this point.
                        String jsonResponse = new HTTPAsyncTask().execute(this_user.serverAddress + "/getAllClubs", "PUT", jsonParam.toString()).get();
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

    protected void onPause() {
        super.onPause();
    }
    protected void onResume() { //brings activity back to main screen.
        super.onResume();
    }
    protected void onStop() {
        super.onStop();
    }
    protected void onDestroy() {
        super.onDestroy();
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    //typical AsyncTask stuff. Probably shouldnt be controlled by the view...
    private class HTTPAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection serverConnection = null;
            InputStream is = null;

            Log.d("Debug:", "Attempting to connect to: " + params[0]);

            try {
                URL url = new URL(params[0]);
                serverConnection = (HttpURLConnection) url.openConnection();
                serverConnection.setRequestMethod(params[1]);
                if (params[1].equals("POST") ||
                        params[1].equals("PUT") ||
                        params[1].equals("DELETE")) {
                    Log.d("DEBUG POST/PUT/DELETE:", "In post: params[0]=" + params[0] + ", params[1]=" + params[1] + ", params[2]=" + params[2]);
                    serverConnection.setDoOutput(true);
                    serverConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

                    // params[2] contains the JSON String to send, make sure we send the
                    // content length to be the json string length
                    serverConnection.setRequestProperty("Content-Length", "" +
                            Integer.toString(params[2].toString().getBytes().length));

                    // Send POST data that was provided.
                    DataOutputStream out = new DataOutputStream(serverConnection.getOutputStream());
                    out.writeBytes(params[2].toString());
                    out.flush();
                    out.close();
                }


                int responseCode = serverConnection.getResponseCode();
                Log.d("Debug:", "\nSending " + params[1] + " request to URL : " + params[0]);
                Log.d("Debug: ", "Response Code : " + responseCode);

                is = serverConnection.getInputStream();

                if (params[1] == "GET" || params[1] == "POST" || params[1] == "PUT" || params[1] == "DELETE") {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    try {
                        JSONObject jsonData = new JSONObject(sb.toString());
                        return jsonData.toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                serverConnection.disconnect();
            }

            return "Should not get to this if the data has been sent/received correctly!";
        }

        /**
         * @param result the result from the query
         */
        protected void onPostExecute(String result) {

        }
    }


    }


