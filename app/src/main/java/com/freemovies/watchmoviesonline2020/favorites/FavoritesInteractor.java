package com.freemovies.watchmoviesonline2020.favorites;

import com.freemovies.watchmoviesonline2020.Movie;

import java.util.List;

/**
 * @author arun
 */
public interface FavoritesInteractor {
    void setFavorite(Movie movie);

    boolean isFavorite(String id);

    List<Movie> getFavorites();

    void unFavorite(String id);
}
