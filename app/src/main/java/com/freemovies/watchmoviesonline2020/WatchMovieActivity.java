package com.freemovies.watchmoviesonline2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class WatchMovieActivity extends AppCompatActivity {
    ProgressBar progressBar;
    InterstitialAd ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_movie);

        progressBar  = findViewById(R.id.progressbar);

        AdView secondbig = (AdView) findViewById(R.id.smartmovie);
        secondbig.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        secondbig.loadAd(new AdRequest.Builder().build());

        ad = new InterstitialAd(this);
        ad.setAdUnitId(getString(R.string.interstitial));
        ad.loadAd(new AdRequest.Builder().build());
        ad.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }
        });

        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movie");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(movieName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WatchMovieActivity.this, "Your Internet Service Provider is blocking Network Connectivity!", Toast.LENGTH_SHORT).show();
            }
        }, 4500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(ad.isLoaded()) {
            ad.show();
        }
    }
}
