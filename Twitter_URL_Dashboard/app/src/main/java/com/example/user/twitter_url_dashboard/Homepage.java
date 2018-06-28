package com.example.user.twitter_url_dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;


public class Homepage extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public static ArrayAdapter<MyUserData> arrayAdapter;
    //final String URL_TWITTER_SIGN_IN = "http://androidsmile.com/lab/twitter/sign_in.php";
    //final String URL_TWITTER_GET_USER_TIMELINE = "http://androidsmile.com/lab/twitter/get_user_timeline.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);



        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(LoginActivity.latlnglist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //((Activity)Homepage.this).recreate();



    }

}


