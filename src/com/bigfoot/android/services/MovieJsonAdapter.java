package com.bigfoot.android.services;

import android.util.Log;
import com.bigfoot.android.model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: Neil Pattinson
 * Date: 06/11/12
 * Time: 13:04
 */
public class MovieJsonAdapter {

    public List<Movie> getFilmsFromJsonExplicitly(String json) {
        if (json == null || (json.length() == 0)) {
            return Collections.emptyList();
        }

        JSONArray jFilms = null;
        try {
            JSONObject jObject = (JSONObject) new JSONTokener(json).nextValue();
            jFilms = jObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.e(getClass().getSimpleName(), "Error parsing JSON: " + json, e);
        }
        if (jFilms == null) {
            return Collections.emptyList();
        }

        List<Movie> films = new ArrayList<Movie>(jFilms.length());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < jFilms.length(); i++) {
            String dateStr = null;
            try {
                JSONObject f = (JSONObject) jFilms.get(i);
                dateStr = f.getString("release_date");
                Date releaseDate = sdf.parse(dateStr);
                Movie film = new Movie(f.getString("title"),
                                       f.getInt("id"),
                                       f.getString("original_title"),
                                       f.getBoolean("adult"),
                                       f.getDouble("popularity"),
                                       releaseDate);

                films.add(film);
            } catch (JSONException e) {
                Log.e(getClass().getSimpleName(), "Error deciphering JSON " + json, e);
            } catch (ParseException e) {
                Log.e(getClass().getSimpleName(), "Error parsing JSON date: " + dateStr, e);
            }
        }

        return films;
    }
}
