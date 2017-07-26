package com.example.looser43.facebookloginfromscratch;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FacebookSignInActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    TextView greeting;
    ProfilePictureView profilePictureView;
    LoginButton loginButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);*/


        callbackManager = CallbackManager.Factory.create();
        // If using in a fragment
        //loginButton.setFragment(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook_sign_in);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profile_pic);
        greeting = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.setReadPermissions("email", "public_profile", "user_friends");


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                greeting.setText("User ID:  " +
                        loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken());
/*                boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
                Profile profile = Profile.getCurrentProfile();
                if (enableButtons && profile != null) {
                    profilePictureView.setProfileId(profile.getId());
                    greeting.setText("User ID:  " +
                            loginResult.getAccessToken().getUserId() + "\n" +
                            "Auth Token: " + loginResult.getAccessToken().getToken());
                    //greeting.setText(getString(R.string.demo_text, profile.getFirstName()));
                } else {
                    profilePictureView.setProfileId(null);
                    greeting.setText("Error");

                    D:\AndroidStudioProjects\KeyStore

                }*/
            }

            @Override
            public void onCancel() {
                greeting.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                greeting.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

/*        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;
                        Profile profile = Profile.getCurrentProfile();
                        if (enableButtons && profile != null) {
                            profilePictureView.setProfileId(profile.getId());
                            //greeting.setText(Resources.getSystem().getString(R.string.demo_text, profile.getFirstName()));
                        } else {
                            profilePictureView.setProfileId(null);
                            greeting.setText(null);
                        }
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        //setContentView(R.layout.main);

        setContentView(R.layout.activity_facebook_sign_in);
        profilePictureView = (ProfilePictureView) findViewById(R.id.profile_pic);
        greeting = (TextView) findViewById(R.id.tv_greetings);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }*/
}
