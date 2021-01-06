package com.freemovies.watchmoviesonline2020.watching;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.freemovies.watchmoviesonline2020.R;
import com.freemovies.watchmoviesonline2020.listing.MoviesListingActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class SelectionActivity extends AppCompatActivity implements RewardedVideoAdListener {
    Intent intent;
    InterstitialAd ad;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        MobileAds.initialize(this, getString(R.string.app_id));
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        AdView banner = findViewById(R.id.selectionbanner);
        banner.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner.loadAd(new AdRequest.Builder().build());

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

    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(getString(R.string.reward),
                new AdRequest.Builder().build());
    }

    public void ButtonSelected(View v) {
        Toast.makeText(this, "Loading ....", Toast.LENGTH_SHORT).show();

        switch(v.getId()){
            case R.id.explore:
                intent = new Intent(this, MoviesListingActivity.class);
                if(ad.isLoaded()) {
                    ad.show();
                }
                else
                    try {
                        startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(this, "Error Loading! Try Again in a while.", Toast.LENGTH_SHORT).show();
                    }
                break;

            case R.id.watch:
                intent = new Intent(this, IndianMovies.class);
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                } else if(ad.isLoaded()) {
                    ad.show();
                }
                else {
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.feedback:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "takoonstandards@gmail.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "MovieApp Suggestion");
                intent.putExtra(Intent.EXTRA_TEXT, "Please add this movie... ");
                try {
                    startActivity(Intent.createChooser(intent, ""));
                }catch (Exception e){
                    Toast.makeText(this, "Error Loading! Try Again in a while.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.moreapps:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Bright+Eye+Apps")));
                }catch (Exception e){
                    Toast.makeText(this, "Error Loading! Try Again in a while.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.rateus:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.freemovies.watchmoviesonline2020")));
                } catch (Exception e) {
                    Toast.makeText(this, "Error Loading! Try Again in a while.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.privacypolicy:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/bright-eye-apps-policy/home")));
                }catch (Exception e){
                    Toast.makeText(this, "Error Loading! Try Again in a while.", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    public void onBackPressed() {

        // TODO Auto-generated method stub
        androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(this);
        // builder.setCancelable(false);
        builder.setTitle("Rate Us if u like this");
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("yes",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
//                Toast.makeText(MoviesListingActivity.this, "Yes i wanna exit", Toast.LENGTH_LONG).show();

                finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
//                Toast.makeText(MoviesListingActivity.this, "i wanna stay on this page", Toast.LENGTH_LONG).show();
                dialog.cancel();

            }
        });
        builder.setNeutralButton("Rate",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }

            }
        });
        androidx.appcompat.app.AlertDialog alert=builder.create();
        alert.show();
        alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
        alert.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
        alert.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        intent = new Intent(this, IndianMovies.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {
        intent = new Intent(this, IndianMovies.class);
        startActivity(intent);
        finish();
    }
}