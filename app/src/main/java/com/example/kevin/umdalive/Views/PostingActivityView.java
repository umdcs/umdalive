package com.example.kevin.umdalive.Views;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kevin.umdalive.Models.PostForClubActivity;
import com.example.kevin.umdalive.Models.PostingActivity;
import com.example.kevin.umdalive.Presenters.Presenter;
import com.example.kevin.umdalive.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class PostingActivityView extends AppCompatActivity implements IPickResult {


    private Presenter presenter;
    private static String clubToPost;
    private EditText title;
    private EditText location;
    private EditText time;
    private EditText date;
    private EditText addInfo;
    private String clubName;
    private ImageView displayImage;
    String imageString;

    protected void onCreate(Bundle savedInstanceState) {
        clubName = getIntent().getStringExtra(PostForClubActivityView.CLUB_NAME);
        presenter = new Presenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_activity);
        title = (EditText) findViewById(R.id.event_title);
        time = (EditText) findViewById(R.id.event_time);
        date = (EditText) findViewById(R.id.event_date);
        location = (EditText) findViewById(R.id.event_location);
        addInfo = (EditText) findViewById(R.id.additional_info);
         displayImage = ((ImageView) findViewById(R.id.result_image));
        imageSetup();
    }

    public void sendPost(View view) {
        Log.d("Club posting: " + clubName, "New post: " + title.getText().toString());
        presenter.putPost(clubName, title.getText().toString(), time.getText().toString(), date.getText().toString()
                , location.getText().toString(), addInfo.getText().toString(), imageString);
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }


    private void imageSetup() {
         displayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickSetup setup = new PickSetup();
                setup.setTitle("Select a picture source.");
                setup.setGalleryIcon(R.mipmap.gallery_colored);
                setup.setCameraIcon(R.mipmap.camera_colored);
                PickImageDialog.build(setup).show(PostingActivityView.this);
            }
        });
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            displayImage.setImageBitmap(r.getBitmap());
            ByteArrayOutputStream bitStream = new ByteArrayOutputStream();
            Bitmap imageMap = r.getBitmap();
            imageMap.compress(Bitmap.CompressFormat.JPEG, 70, bitStream);
            imageString = Base64.encodeToString(bitStream.toByteArray(), Base64.NO_WRAP);
        } else {
            Log.d("ERROR in image: ", r.getError().getMessage());
        }
    }

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
}
