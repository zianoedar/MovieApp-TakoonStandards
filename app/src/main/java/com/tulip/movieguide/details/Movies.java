package com.tulip.movieguide.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tulip.movieguide.R;
import com.tulip.movieguide.listing.MoviesListingActivity;

public class Movies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Intent intent=new Intent(this, MoviesListingActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(intent);
                finish();
            }
        }, 1000);


    }
}
