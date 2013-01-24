package com.bigfoot.android.services;

import android.preference.PreferenceManager;
import android.util.Log;
import com.androidquery.util.AQUtility;
import com.bigfoot.android.SettingsActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic template for retrieval of data from MovieDb.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:09
 */
public abstract class GenericMoviedbSearch<E> {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String API_KEY = "c615d8f79f88d6ede76a61331cd7ef54";
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * Performs a search on The Movie Db.
     * @param query a query string.
     * @param maxResults the max number of results to return. Unlimited if {@code 0}.
     * @return a list of the appropriate type.
     */
    public List<E> search(String query, int maxResults) {
        List<E> fullList = retrieveData(query);

        if (maxResults == 0) {
            return fullList;
        }
        int max = Math.min(maxResults, fullList.size());
        List<E> shortenedList = new ArrayList<E>(max);
        for (int i = 0; i < max; i++) {
            shortenedList.add(fullList.get(i));
        }
        return shortenedList;
    }

    protected abstract List<E> buildListFromJSON(String json);

    protected abstract String getSearchType();

    protected List<E> retrieveData(String query) {
        String jsonResponse = requestJsonFromMdb(query);
        return buildListFromJSON(jsonResponse);
    }

    private String requestJsonFromMdb(String query) {
        String encodedQuery;
        try {
            encodedQuery = URLEncoder.encode(query, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            Log.e(getClass().getSimpleName(), "Fatal error: ", e);
            throw new RuntimeException(e);
        }
        String url = BASE_URL + getSearchType() + "?api_key=" + API_KEY + "&query=" + encodedQuery + "&page=1";

        if (PreferenceManager.getDefaultSharedPreferences(AQUtility.getContext()).getBoolean(SettingsActivity.PREF_EXCLUDE_ADULT, false)) {
            Log.i(getClass().getSimpleName(), "Excluding adult titles");
            url += "&include_adult=false";
        }

        Log.i(getClass().getSimpleName(), "Request: " + url);
        String response = new RemoteDataService().getStringFrom(url);
        Log.i(getClass().getSimpleName(), "Response: " + response);
        return response;
    }
}
