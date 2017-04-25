package com.example.kevin.umdalive.Models;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.kevin.umdalive.Presenters.Presenter;
import com.example.kevin.umdalive.R;
import com.example.kevin.umdalive.Views.MainView;
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

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private String mFullName;
   private  String mEmail;

    TextView userEmail;
    NavigationView mNavigationView;
    Presenter presenter; //there shouldn't be an error here after merging with the Presenter branch to gain the presenter class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
initializeButtons();
        GoogleSignInitializer();
presenter=new Presenter(this);




















    }

    private Button signedOutButton;
    private  SignInButton signInButton;
    private RelativeLayout Rlayout;

    private static final String TAG ="SignInActivity" ;

    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;

    public void initializeButtons(){
        signedOutButton = (Button) findViewById(R.id.btn_sign_out);
        signInButton= (SignInButton) findViewById(R.id.sign_in_button);
        Rlayout=(RelativeLayout) findViewById(R.id.RLayout);



        signedOutButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);


    }



    public void GoogleSignInitializer(){

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        // Set the dimensions of the sign-in button.
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


    }




    public void signUp() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        startActivityForResult(signInIntent, RC_SIGN_IN);



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            mFullName=acct.getDisplayName();
            mEmail=acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            Log.d("presenters", "planning to user data to server");



            Intent intent= new Intent(this, MainView.class);

            intent.putExtra("Email", mEmail);
            intent.putExtra("Name",mFullName);
            intent.putExtra("pic",personPhoto.toString());

            intent.setData(personPhoto);

            startActivity(intent);

            updateUI(true);
        } else {

            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    /**
     * this method determines whether or not the sign in or sign out button is visible based on the param
     * @param signedIn whether or not the user is signed in
     */
    private void updateUI(boolean signedIn) {
        if (signedIn) {
            signInButton.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);
        }

        else {

            signInButton.setVisibility(View.VISIBLE);
            signedOutButton.setVisibility(View.GONE);

        }
    }






    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    // [START signIn]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END signOut]


    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
             
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }




    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btn_sign_out:
                signOut();
                break;

        }




    }


   public String getmEmail(){
       return mEmail;
   }

   public String getmFullName(){

       return mFullName;

   }


}

































