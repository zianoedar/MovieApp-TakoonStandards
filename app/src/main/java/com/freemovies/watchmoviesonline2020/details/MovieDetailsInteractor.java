package com.freemovies.watchmoviesonline2020.details;

import com.freemovies.watchmoviesonline2020.Review;
import com.freemovies.watchmoviesonline2020.Video;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MovieDetailsInteractor {
    Observable<List<Video>> getTrailers(String id);

    Observable<List<Review>> getReviews(String id);
}
