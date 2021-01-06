package com.freemovies.watchmoviesonline2020.watching;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.freemovies.watchmoviesonline2020.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class EnglishMovies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_movies);

        try {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }catch (Exception e){}

        AdView banner1 = findViewById(R.id.english_banner_1);
        banner1.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner1.loadAd(new AdRequest.Builder().build());

        AdView banner2 = findViewById(R.id.english_banner_2);
        banner2.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner2.loadAd(new AdRequest.Builder().build());
    }

    public void WatchSelectedMovie(View v) {
        Intent intent= new Intent(EnglishMovies.this, WatchMovie.class);

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

            case R.id.e1: intent.putExtra("id","4OL0esXzMFw");startActivity(intent);
                break;

            case R.id.e2: intent.putExtra("id","TBBhDtdWmr0");startActivity(intent);
                break;

            case R.id.e3: intent.putExtra("id","2kK-giOBkLM");startActivity(intent);
                break;

            case R.id.e4: intent.putExtra("id","6qaGifRpQbk");startActivity(intent);
                break;

            case R.id.e5: intent.putExtra("id","CDDVsg7Uf9s");startActivity(intent);
                break;

            case R.id.e6: intent.putExtra("id","sWfgTiJ3sCs");startActivity(intent);
                break;

            case R.id.e7: intent.putExtra("id","S5SrOgwSetU");startActivity(intent);
                break;

            case R.id.e8: intent.putExtra("id","JKnPhSb9lhg");startActivity(intent);
                break;

            case R.id.e9: intent.putExtra("id","INC_xN6Jx88");startActivity(intent);
                break;

            case R.id.e10: intent.putExtra("id","TGT1uS7xZHk");startActivity(intent);
                break;

            case R.id.e11: intent.putExtra("id","Jk4gXKz9wIc");startActivity(intent);
                break;

            case R.id.e12: intent.putExtra("id","uj-lGUJerBY");startActivity(intent);
                break;

            case R.id.e13: intent.putExtra("id","VvUY3DDo8E4");startActivity(intent);
                break;

            case R.id.e14: intent.putExtra("id","u5Y5veSoz5o");startActivity(intent);
                break;

            case R.id.e15: intent.putExtra("id","Gd7p7R5Q8Pw");startActivity(intent);
                break;

            case R.id.e16: intent.putExtra("id","laO5FyhWuQE");startActivity(intent);
                break;

            case R.id.e17: intent.putExtra("id","JUFNhFtXtXw");startActivity(intent);
                break;

            case R.id.e18: intent.putExtra("id","mjHDFuiyKRU");startActivity(intent);
                break;

            case R.id.e19: intent.putExtra("id","UkMLQOMEIew");startActivity(intent);
                break;

            case R.id.e20: intent.putExtra("id","MFpssQb54OY");startActivity(intent);
                break;

            case R.id.e21: intent.putExtra("id","Ufucgn-bf1g");startActivity(intent);
                break;

            case R.id.e22: intent.putExtra("id","ITsNkFI7rmo");startActivity(intent);
                break;

            case R.id.e23: intent.putExtra("id","P7GV28aAIr0");startActivity(intent);
                break;

            case R.id.e24: intent.putExtra("id","Cu0y2nDF4i0");startActivity(intent);
                break;

            case R.id.e25: intent.putExtra("id","f0o2iliEE3k");startActivity(intent);
                break;

            case R.id.e26: intent.putExtra("id","MWEn64JhpYo");startActivity(intent);
                break;

            case R.id.e27: intent.putExtra("id","HDH1r61ITyU");startActivity(intent);
                break;

            case R.id.e28: intent.putExtra("id","P7dOu4TR3k0");startActivity(intent);
                break;

            case R.id.e29: intent.putExtra("id","88N7_ylB2HE");startActivity(intent);
                break;

            case R.id.e30: intent.putExtra("id","aTYrGuswisE");startActivity(intent);
                break;
        }
        finish();
    }
    public void onBackPressed() {
        Intent intent = new Intent(this, SelectionActivity.class);
        startActivity(intent);
    }
}
