package com.tulip.movieguide;

import com.tulip.movieguide.details.DetailsComponent;
import com.tulip.movieguide.details.DetailsModule;
import com.tulip.movieguide.favorites.FavoritesModule;
import com.tulip.movieguide.listing.ListingComponent;
import com.tulip.movieguide.listing.ListingModule;
import com.tulip.movieguide.network.NetworkModule;

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
