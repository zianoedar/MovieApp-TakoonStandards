package com.tulip.movieguide.details;

import com.tulip.movieguide.Review;
import com.tulip.movieguide.Video;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
public interface MovieDetailsInteractor {
    Observable<List<Video>> getTrailers(String id);

    Observable<List<Review>> getReviews(String id);
}
