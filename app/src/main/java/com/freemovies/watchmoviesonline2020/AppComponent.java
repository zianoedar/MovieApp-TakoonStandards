package com.freemovies.watchmoviesonline2020;

import com.freemovies.watchmoviesonline2020.details.DetailsComponent;
import com.freemovies.watchmoviesonline2020.details.DetailsModule;
import com.freemovies.watchmoviesonline2020.favorites.FavoritesModule;
import com.freemovies.watchmoviesonline2020.listing.ListingComponent;
import com.freemovies.watchmoviesonline2020.listing.ListingModule;
import com.freemovies.watchmoviesonline2020.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arunsasidharan
 * @author pulkitkumar
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        FavoritesModule.class})
public interface AppComponent {
    DetailsComponent plus(DetailsModule detailsModule);

    ListingComponent plus(ListingModule listingModule);
}
