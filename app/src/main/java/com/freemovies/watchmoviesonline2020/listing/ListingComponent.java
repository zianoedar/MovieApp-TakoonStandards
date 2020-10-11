package com.freemovies.watchmoviesonline2020.listing;

import com.freemovies.watchmoviesonline2020.listing.sorting.SortingDialogFragment;
import com.freemovies.watchmoviesonline2020.listing.sorting.SortingModule;

import dagger.Subcomponent;

/**
 * @author arunsasidharan
 */
@ListingScope
@Subcomponent(modules = {ListingModule.class, SortingModule.class})
public interface ListingComponent {
    MoviesListingFragment inject(MoviesListingFragment fragment);

    SortingDialogFragment inject(SortingDialogFragment fragment);
}
