package com.example.kevin.umdalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;

//import com.google.android.gms.auth.api.Auth;

/**
 * Created by Luke on 11/7/2016.
 */

public class LoginActivity extends AppCompatActivity {
            GoogleApiClient.OnConnectionFailedListener,
            View.OnClickListener {









//    public void signUpScreen(View view){
//        //Intent intent = new Intent(this, SignUpActivity.class);
//        //startActivity(intent);
//    }
    /*
    Methods for Activity to inherit parent
     */
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
}

