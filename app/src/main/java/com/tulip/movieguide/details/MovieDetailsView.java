package com.tulip.movieguide.details;

import com.tulip.movieguide.Movie;
import com.tulip.movieguide.Review;
import com.tulip.movieguide.Video;

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
