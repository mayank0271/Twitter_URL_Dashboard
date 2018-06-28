package com.example.user.twitter_url_dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OauthResult  {

    @SerializedName("oauth_token")
    @Expose
    private static String oauthToken;
    @SerializedName("oauth_token_secret")
    @Expose
    private static String oauthTokenSecret;
    @SerializedName("user_id")
    @Expose
    private static String userId;
    @SerializedName("screen_name")
    @Expose
    private static String screenName;


    public static String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String ot) {
        oauthToken = ot;
    }

    public static String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oTS) {
        oauthTokenSecret = oTS;
    }

    public static String getUId() {
        return userId;
    }

    public void setUId(String uId) {
        userId = uId;
    }

    public static String getScName() {
        return screenName;
    }

    public void setScName(String scName) {
        screenName = scName;
    }


}

