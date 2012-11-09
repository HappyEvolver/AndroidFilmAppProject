package com.bigfoot.android.services;

import com.bigfoot.android.model.Movie;

import java.util.List;

/**
 * Performs a search for films on MovieDb.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:50
 */
public class MovieSearch extends GenericMoviedbSearch<Movie> {

    @Override
    protected String getSearchType() {
        return "movie";
    }

    @Override
    protected List<Movie> buildListFromJSON(String json) {
        return new MovieJsonAdapter().getFilmsFromJson(json);
    }
}
