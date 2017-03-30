package com.example.kevin.umdalive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by Luke on 11/7/2016.
 */

public class LoginActivity extends AppCompatActivity {
    private GoogleApiClient mGoogleApiClient;
    /**
     * this method builds the google sign-in api client.
     *
     */
    public void GoogleApiBuilder(){}


    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, (GoogleApiClient.OnConnectionFailedListener) this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();







//probably will be moved to te view


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_activity);
//    }
//
    /*
    This is the onClick for the Login button
    current functionality: currently all this does is check for "@" symbol and go to next page.
    The next page is the Main Activity.

    Replace with OAuth
     */

    //This function will be moved to the view



    /*
    This is the onClick for the signup button of the lower right corner.
    current functionality: All this does is bring you to a new activity
    that will help create a new user.

    This brings you to SignUpActivity
     */
    //stays here













    public void signUpScreen(View view){
        //Intent intent = new Intent(this, SignUpActivity.class);
        //startActivity(intent);
    }
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

