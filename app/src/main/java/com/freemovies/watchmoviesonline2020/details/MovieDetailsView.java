package com.freemovies.watchmoviesonline2020.details;

import com.freemovies.watchmoviesonline2020.Movie;
import com.freemovies.watchmoviesonline2020.Review;
import com.freemovies.watchmoviesonline2020.Video;

import java.util.List;

/**
 * @author arun
 */
interface MovieDetailsView {
    void showDetails(Movie movie);

    void showTrailers(List<Video> trailers);

    void showReviews(List<Review> reviews);

    void showFavorited();

    void showUnFavorited();
}
