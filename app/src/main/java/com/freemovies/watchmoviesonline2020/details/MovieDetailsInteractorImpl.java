package com.freemovies.watchmoviesonline2020.details;

import com.freemovies.watchmoviesonline2020.Review;
import com.freemovies.watchmoviesonline2020.ReviewsWrapper;
import com.freemovies.watchmoviesonline2020.Video;
import com.freemovies.watchmoviesonline2020.VideoWrapper;
import com.freemovies.watchmoviesonline2020.network.TmdbWebService;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author arun
 */
class MovieDetailsInteractorImpl implements MovieDetailsInteractor {

    private TmdbWebService tmdbWebService;

    MovieDetailsInteractorImpl(TmdbWebService tmdbWebService) {
        this.tmdbWebService = tmdbWebService;
    }

    @Override
    public Observable<List<Video>> getTrailers(final String id) {
        return tmdbWebService.trailers(id).map(VideoWrapper::getVideos);
    }

    @Override
    public Observable<List<Review>> getReviews(final String id) {
        return tmdbWebService.reviews(id).map(ReviewsWrapper::getReviews);
    }

}
