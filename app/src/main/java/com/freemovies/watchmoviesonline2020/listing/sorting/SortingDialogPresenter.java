package com.freemovies.watchmoviesonline2020.listing.sorting;

/**
 * @author arun
 */
public interface SortingDialogPresenter {
    void setLastSavedOption();

    void onPopularMoviesSelected();

    void onHighestRatedMoviesSelected();

    void onFavoritesSelected();

    void onNewestMoviesSelected();

    void setView(SortingDialogView view);

    void destroy();
}
