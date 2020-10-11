package com.freemovies.watchmoviesonline2020.listing;

import com.freemovies.watchmoviesonline2020.Movie;

import java.util.List;

/**
 * @author arun
 */
interface MoviesListingView {
    void showMovies(List<Movie> movies);

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void onMovieClicked(Movie movie);
}
