package com.bigfoot.android.services;

import android.preference.PreferenceManager;
import android.util.Log;
import com.androidquery.util.AQUtility;
import com.bigfoot.android.SettingsActivity;
import com.bigfoot.android.model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Neil Pattinson
 * Date: 06/11/12
 * Time: 13:04
 */
public class MovieJsonAdapter {

    public List<Movie> getFilmsFromJson(String json) {
        if (json == null || (json.length() == 0)) {
            return new ArrayList<Movie>(0);
        }

        JSONArray jFilms = null;
        try {
            JSONObject jObject = (JSONObject) new JSONTokener(json).nextValue();
            jFilms = jObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.e(getClass().getSimpleName(), "Error parsing JSON: " + json, e);
        }
        if (jFilms == null) {
            return new ArrayList<Movie>(0);
        }

        List<Movie> films = new ArrayList<Movie>(jFilms.length());
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Check for 'this century' option
        boolean thisCentury = PreferenceManager.getDefaultSharedPreferences(AQUtility.getContext()).getBoolean(SettingsActivity.PREF_THIS_CENTURY, false);
        for (int i = 0; i < jFilms.length(); i++) {
            String dateStr = null;
            Date releaseDate = null;
            try {
                JSONObject f = (JSONObject) jFilms.get(i);
                dateStr = f.getString("release_date");
                if (dateStr != null && !dateStr.equalsIgnoreCase("null")) {
                    releaseDate = sdf.parse(dateStr);
                }

                // If 'this century' option set and film identifiably this century, then skip to next
                // Bit cludgey, but it works.
                if (thisCentury && releaseDate != null && releaseDate.before(sdf.parse("2000-01-01"))) {
                    continue;
                }

                Movie film = new Movie(f.getString("title"),
                                       f.getInt("id"),
                                       f.getString("original_title"),
                                       f.getBoolean("adult"),
                                       f.getDouble("popularity"),
                                       f.getDouble("vote_average"),
                                       releaseDate,
                                       f.getString("poster_path"));

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
