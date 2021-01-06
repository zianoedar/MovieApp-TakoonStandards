package com.freemovies.watchmoviesonline2020.watching;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.freemovies.watchmoviesonline2020.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class IndianMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_movies);
        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }catch (Exception e){}

        AdView banner1 = findViewById(R.id.indian_movie_banner_1);
        banner1.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner1.loadAd(new AdRequest.Builder().build());

        AdView banner2 = findViewById(R.id.indian_movie_banner_2);
        banner2.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner2.loadAd(new AdRequest.Builder().build());
    }

    public void WatchSelectedMovie(View v) {
        Intent intent= new Intent(IndianMovies.this, WatchMovie.class);
        intent.putExtra("EXTRA_SESSION_ID", "1");

        switch(v.getId()){
//__________________________________Top Bar Start________________________________
            case R.id.hw:
                Intent intent1 = new Intent(this,EnglishMovies.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.bw:
                Intent intent2 = new Intent(this,IndianMovies.class);
                startActivity(intent2);
                finish();
                break;
//__________________________________Top Bar End________________________________

            case R.id.h1: intent.putExtra("id","6FqUN9MO0zc");startActivity(intent);
                break;

            case R.id.h2:
                intent.putExtra("id", "zgaRyYixsDk");
                startActivity(intent);
                break;

            case R.id.h3: intent.putExtra("id","vMFpzomW0Ck");startActivity(intent);
                break;

            case R.id.h4: intent.putExtra("id","1H_IeXxTspg");startActivity(intent);
                break;

            case R.id.h5: intent.putExtra("id","sSb-OtEtlVo");startActivity(intent);
                break;

            case R.id.h6: intent.putExtra("id","lfhkyjNE3EQ");startActivity(intent);
                break;

            case R.id.h7: intent.putExtra("id","LKNHVDPKy7g");startActivity(intent);
                break;

            case R.id.h8: intent.putExtra("id","ZmE6TN9bYQg");startActivity(intent);
                break;

            case R.id.h9: intent.putExtra("id","ZayhnBx5Vx8");startActivity(intent);
                break;

            case R.id.h10: intent.putExtra("id","qPEeo4qGKF4");startActivity(intent);
                break;

            case R.id.h11: intent.putExtra("id","D0O1G5oX5tM");startActivity(intent);
                break;

            case R.id.h12: intent.putExtra("id","cx4Xwae513g");startActivity(intent);
                break;

            case R.id.h13: intent.putExtra("id","NJhfX3HTvcc");startActivity(intent);
                break;

            case R.id.h14: intent.putExtra("id","G9ZV4NZ5Ulw");startActivity(intent);
                break;

            case R.id.h15: intent.putExtra("id","Di1Xgbfwc1g");startActivity(intent);
                break;

            case R.id.h16: intent.putExtra("id","R_BHXsS0E8U");startActivity(intent);
                break;

            case R.id.h17: intent.putExtra("id","pntkYAIBHsA");startActivity(intent);
                break;

            case R.id.h18: intent.putExtra("id","VPTTwpUsjcA");startActivity(intent);
                break;

            case R.id.h19: intent.putExtra("id","vRPtaAaja5I");startActivity(intent);
                break;

            case R.id.h20: intent.putExtra("id","5s0dAbPCduM");startActivity(intent);
                break;

            case R.id.h21: intent.putExtra("id","OI-z8OVzAps");startActivity(intent);
                break;

            case R.id.h22: intent.putExtra("id","TcDmarWK68c");startActivity(intent);
                break;

            case R.id.h23: intent.putExtra("id","0qN4zVvM4HU");startActivity(intent);
                break;

            case R.id.h24: intent.putExtra("id","hBo4Oh8gI58");startActivity(intent);
                break;

            case R.id.h25: intent.putExtra("id","Wqz1WMkcOEc");startActivity(intent);
                break;

            case R.id.h26: intent.putExtra("id","0ztZqOPPKYM");startActivity(intent);
                break;

            case R.id.h27: intent.putExtra("id","_1MO5-R9ApA");startActivity(intent);
                break;

            case R.id.h28: intent.putExtra("id","GZzzqoAh8u0");startActivity(intent);
                break;

            case R.id.h29: intent.putExtra("id","f2EAvRNz4RM");startActivity(intent);
                break;

            case R.id.h30: intent.putExtra("id","QG81vURIDNE");startActivity(intent);
                break;
        }
        finish();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, SelectionActivity.class);
        startActivity(intent);
    }

}