package com.example.user.twitter_url_dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {

    //Declaring Twitter loginButton
    TwitterLoginButton loginButton;
    public static String token;
    public static String secret;
    public static String  userID;


    public static ArrayList<MyUserData> latlnglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing twitter instance
        Twitter.initialize(this);  //Make sure that this statement is added before setContentView() method
        setContentView(R.layout.activity_login);


        //Instantiating Twitter loginButton
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);

        /*
          These statements will execute when loginButton is clicked
         */
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                /*
                  This provides TwitterSession as a result
                  This will execute when the authentication is successful
                 */
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                token= authToken.token;
                secret= authToken.secret;
                userID = session.getUserName();

                login(session,authToken);
                //This method will start a new activity where in all the urls are listed.
                funIntent();
            }

            @Override
            public void failure(TwitterException exception) {
                //Displaying Toast message
                Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void login(TwitterSession session,TwitterAuthToken authToken)
    {
        //OauthResult is a class in which all the variables related to session and authToken are stored.
        OauthResult oauthResult = new OauthResult();
        oauthResult.setOauthToken(authToken.token);
        oauthResult.setUId(Long.toString(session.getUserId()));
        oauthResult.setScName(session.getUserName());
        oauthResult.setOauthTokenSecret(authToken.secret);
        latlnglist = new ArrayList<MyUserData>();
        //BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this).execute();
        new BackgroundTask().execute();
        // loginButton.setVisibility();
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loginButton.setText("Click here to redirect to home page");


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
    void funIntent(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,Homepage.class);
                startActivity(i);
            }
        });

    }
}

