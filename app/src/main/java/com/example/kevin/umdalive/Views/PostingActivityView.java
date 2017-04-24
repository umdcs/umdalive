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
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView inputError;
    private String clubName;
    private ImageView displayImage;
    private String errorMessage;
    private String imageString;

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
        displayImage = (ImageView) findViewById(R.id.result_image);
        inputError = (TextView) findViewById(R.id.invalid_input);
        imageSetup();
    }

    public void sendPost(View view) {
        boolean isError = checkStrings();
        ;
        Log.d("Club posting: " + clubName, "New post: " + title.getText().toString());
        if (!isError) {
            presenter.putPost(clubName, title.getText().toString(), time.getText().toString(), date.getText().toString()
                    , location.getText().toString(), addInfo.getText().toString(), imageString);
            Intent intent = new Intent(this, MainView.class);
            startActivity(intent);
        } else inputError.setText(errorMessage);
    }

    /**
     * Checks if strings are invalid
     * @return false if invalid input
     */
    private boolean checkStrings() {
        boolean isError = false;
        errorMessage = "";
        if (title.getText().toString().matches("") || presenter.isPostInfoValid(title.getText().toString())) {
            errorMessage = "You must enter a valid title.";
            isError = true;
        }
        if (time.getText().toString().matches("") || presenter.isPostInfoValid(time.getText().toString())) {
            errorMessage = "You must enter a valid time.";
            isError = true;
        }
        if (date.getText().toString().matches("") || presenter.isPostInfoValid(date.getText().toString())) {
            errorMessage = "You must enter a valid date.";
            isError = true;
        }
        if (location.getText().toString().matches("") || presenter.isPostInfoValid(location.getText().toString())) {
            errorMessage = "You must enter a valid location.";
            isError = true;
        }
        if (addInfo.getText().toString().matches("") || presenter.isPostInfoValid(addInfo.getText().toString())) {
            errorMessage = "You must enter a valid description.";
            isError = true;
        }
        return isError;
    }


    /**
     * Sets up the selected image
     */
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
