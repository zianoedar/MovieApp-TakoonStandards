package com.tulip.movieguide.listing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.IdlingResource;

import com.google.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.tulip.movieguide.Constants;
import com.tulip.movieguide.Movie;
import com.tulip.movieguide.R;
import com.tulip.movieguide.details.MovieDetailsActivity;
import com.tulip.movieguide.details.MovieDetailsFragment;
import com.tulip.movieguide.util.EspressoIdlingResource;
import com.tulip.movieguide.util.RxUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;


public class MoviesListingActivity extends AppCompatActivity implements MoviesListingFragment.Callback {
    public static final String DETAILS_FRAGMENT = "DetailsFragment";
    private boolean twoPaneMode;
    private Disposable searchViewTextSubscription;
    InterstitialAd ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        if(!isNetworkAvailable()){
            Toast.makeText(this, "Please connect to Internet", Toast.LENGTH_LONG).show();
        }
        AdView banner = findViewById(R.id.banner);
        banner.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(this);
        banner.loadAd(new AdRequest.Builder().build());

        ad = new InterstitialAd(this);
        ad.setAdUnitId(getString(R.string.interstitial));
        ad.loadAd(new AdRequest.Builder().build());
        ad.loadAd(new AdRequest.Builder().build());

        if (findViewById(R.id.movie_details_container) != null) {
            twoPaneMode = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, new MovieDetailsFragment())
                        .commit();
            }
        } else {
            twoPaneMode = false;
        }
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.movie_guide);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        final MoviesListingFragment mlFragment = (MoviesListingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_listing);
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                MoviesListingFragment mlFragment = (MoviesListingFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_listing);
                mlFragment.searchViewBackButtonClicked();
                return true;
            }
        });

        searchViewTextSubscription = RxSearchView.queryTextChanges(searchView)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(charSequence -> {
                    if (charSequence.length() > 0) {
                        mlFragment.searchViewClicked(charSequence.toString());
                    }
                });

        return true;
    }

    @Override
    public void onMoviesLoaded(Movie movie) {
        if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {
            // Do not load in single pane view
        }
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        Bundle extras = new Bundle();
        extras.putParcelable(Constants.MOVIE, movie);
        intent.putExtras(extras);
        if (twoPaneMode) {
            loadMovieFragment(movie);
        } else {
            if(ad.isLoaded()){
                ad.show();
                ad.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        startActivity(intent);
                    }
                });
            }else {
                startActivity(intent);
            }
        }
    }


    private void loadMovieFragment(Movie movie) {
        MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movie_details_container, movieDetailsFragment, DETAILS_FRAGMENT)
                .commit();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    protected void onDestroy() {
        RxUtils.unsubscribe(searchViewTextSubscription);
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        rate();
    }

    private void rate() {

        new AlertDialog.Builder(this)
                .setMessage("Please Rate Us:")
                .setCancelable(true)
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int id) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.tulip.movieguide")));
                }}).setNeutralButton("No", new DialogInterface.OnClickListener() { public void onClick(DialogInterface dialog, int which) {
            finish();
        }}).show();

    }

}
