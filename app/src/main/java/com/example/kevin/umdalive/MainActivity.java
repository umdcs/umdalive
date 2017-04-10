package com.example.kevin.umdalive;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity {

    public static UserInformationModel getUser(String userData) {
        try {
            JSONObject user = new JSONObject(userData);
            ArrayList<ClubInformationModel> list = new ArrayList<>();
            JSONArray jArray = user.getJSONArray("clubs");

            if (jArray != null) {
                int len = jArray.length();
                for (int i = 0; i < len; i++) {
                    JSONObject clubObject = jArray.getJSONObject(i);
                    //create new club object from server data
                    ClubInformationModel tempClub = new ClubInformationModel(clubObject.get("clubname").toString(),
                            clubObject.get("username").toString(), clubObject.get("keywords").toString(),
                            clubObject.get("description").toString());

                    Log.d("club name: ", clubObject.get("clubname").toString());
                    //add new club object to array
                    list.add(tempClub);
                }
            }
            Log.d("userData", userData);
            //will obtain json string from textview and take value out from string
            return new UserInformationModel(user.getString("name"), user.getString("major"),
                    user.getString("email"), user.getString("graduationDate"), list);
        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public static ArrayList<PostInformationModel> refreshPosts(String jsonString) {
        ArrayList<PostInformationModel> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null) {
                int len = jsonArray.length();
                for (int i = len - 1; i >= 0; i--) {
                    JSONObject currentJSON = jsonArray.getJSONObject(i);
                    list.add(new PostInformationModel(currentJSON));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}

class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private ArrayList<PostInformationModel> postData;
    private RecyclerView recyclerView;
    int expandedPosition = -1;

    public PostAdapter(ArrayList<PostInformationModel> postData, RecyclerView recyclerView) {
        this.postData = postData;
        this.recyclerView = recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        LinearLayout expandedView;
        Button expandButton;

        public ViewHolder(CardView cView) {
            super(cView);
            expandedView = (LinearLayout) itemView.findViewById(R.id.extended_view);
            expandButton = (Button) itemView.findViewById(R.id.card_button);
            cardView = cView;
        }
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(cView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PostInformationModel curPost = postData.get(position);
        TextView titleText = (TextView) holder.cardView.findViewById(R.id.post_title_text);
        TextView clubName = (TextView) holder.cardView.findViewById(R.id.post_club_name);
        TextView eventDate = (TextView) holder.cardView.findViewById(R.id.post_date);
        TextView eventTime = (TextView) holder.cardView.findViewById(R.id.post_time);
        TextView eventLocation = (TextView) holder.cardView.findViewById(R.id.post_location);
        TextView description = (TextView) holder.cardView.findViewById(R.id.description_content);

        titleText.setText(curPost.getTitle());
        clubName.setText(curPost.getClub());
        eventDate.setText(curPost.getDate());
        eventTime.setText(curPost.getTime());
        eventLocation.setText(curPost.getLocation());
        description.setText(curPost.getDescription());

        final boolean isExpanded = position== expandedPosition;
        holder.expandedView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.expandButton.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        holder.itemView.setActivated(isExpanded);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                expandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }

}



