package com.example.kevin.umdalive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;

//import com.google.android.gms.auth.api.Auth;
<<<<<<< HEAD
=======
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
>>>>>>> 9ed6c4d486088ab65ef9a97a40ff425ae24f2682

/**
 * Created by Luke on 11/7/2016.
 */

public class LoginActivity extends AppCompatActivity {
<<<<<<< HEAD
            GoogleApiClient.OnConnectionFailedListener,
            View.OnClickListener {
=======


    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this /* FragmentActivity */, (GoogleApiClient.OnConnectionFailedListener) this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();







//probably will be moved to te view

>>>>>>> 9ed6c4d486088ab65ef9a97a40ff425ae24f2682









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

