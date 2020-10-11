package com.freemovies.watchmoviesonline2020.details;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.freemovies.watchmoviesonline2020.R;
import com.freemovies.watchmoviesonline2020.listing.MoviesListingActivity;
import com.freemovies.watchmoviesonline2020.watching.SelectionActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Movies extends AppCompatActivity {
    InterstitialAd ad;
    Intent intent;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ad = new InterstitialAd(this);
        ad.setAdUnitId(getString(R.string.interstitial));
        ad.loadAd(new AdRequest.Builder().build());
        ad.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(intent);
            }
        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Movie App");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        intent = new Intent(this, SelectionActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(ad.isLoaded()) {
                    ad.show();
                }
                else
                    startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
