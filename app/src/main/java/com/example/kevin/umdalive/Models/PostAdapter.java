package com.example.kevin.umdalive.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.umdalive.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
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
        TextView expandText;

        public ViewHolder(CardView cView) {
            super(cView);
            expandedView = (LinearLayout) itemView.findViewById(R.id.extended_view);
            expandText = (TextView) itemView.findViewById(R.id.card_expand);
            cardView = cView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        ImageView image = (ImageView) holder.cardView.findViewById(R.id.post_image);

        titleText.setText(curPost.getTitle());
        clubName.setText(curPost.getClub());
        eventDate.setText(curPost.getDate());
        eventTime.setText(curPost.getTime());
        eventLocation.setText(curPost.getLocation());
        description.setText(curPost.getDescription());

        byte[] bitmapData = Base64.decode(curPost.getImage(), Base64.NO_WRAP);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }

        final boolean isExpanded = position == expandedPosition;
        holder.expandedView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.expandText.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
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
