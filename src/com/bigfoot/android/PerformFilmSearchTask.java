package com.bigfoot.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.bigfoot.android.model.Movie;
import com.bigfoot.android.services.GenericMoviedbSearch;
import com.bigfoot.android.services.MovieSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link AsyncTask} that performs a search for films using a remote search service.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 19:37
 */
public class PerformFilmSearchTask extends AsyncTask<String, Void, List<Movie>> {

    private ProgressDialog progress;
    private Context ctx;

    public PerformFilmSearchTask(Context ctx) {
        this.ctx = ctx;
    }

    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Wait!", "Searching for films...", true);
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        String query = params[0];
        GenericMoviedbSearch<Movie> searchService = new MovieSearch();
        return searchService.search(query, 0);
    }

    protected void onPostExecute(final List<Movie> results) {
        progress.dismiss();

        Intent intent = new Intent(ctx, FilmListActivity.class);
        intent.putExtra("films", ((ArrayList<Movie>) results));
        ctx.startActivity(intent);
    }
}
