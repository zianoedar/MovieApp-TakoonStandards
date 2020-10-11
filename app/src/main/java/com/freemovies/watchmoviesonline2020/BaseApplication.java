package com.freemovies.watchmoviesonline2020;

import android.app.Application;
import android.os.StrictMode;

import com.freemovies.watchmoviesonline2020.details.DetailsComponent;
import com.freemovies.watchmoviesonline2020.details.DetailsModule;
import com.freemovies.watchmoviesonline2020.favorites.FavoritesModule;
import com.freemovies.watchmoviesonline2020.listing.ListingComponent;
import com.freemovies.watchmoviesonline2020.listing.ListingModule;
import com.freemovies.watchmoviesonline2020.network.NetworkModule;

import io.realm.Realm;

/**
 * @author arun
 */
public class BaseApplication extends Application {
    private AppComponent appComponent;
    private DetailsComponent detailsComponent;
    private ListingComponent listingComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
        initRealm();
        appComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .favoritesModule(new FavoritesModule())
                .build();
    }

    private void initRealm() {
        Realm.init(this);
    }

    public DetailsComponent createDetailsComponent() {
        detailsComponent = appComponent.plus(new DetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }

    public ListingComponent createListingComponent() {
        listingComponent = appComponent.plus(new ListingModule());
        return listingComponent;
    }

    public void releaseListingComponent() {
        listingComponent = null;
    }

    public ListingComponent getListingComponent() {
        return listingComponent;
    }
}
