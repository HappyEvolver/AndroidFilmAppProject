package com.bigfoot.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.bigfoot.android.model.Person;
import com.bigfoot.android.services.GenericMoviedbSearch;
import com.bigfoot.android.services.PersonSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link AsyncTask} that performs a search for people using a remote search service.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:36
 */
public class PerformPersonSearchTask extends AsyncTask<String, Void, List<Person>> {

    private ProgressDialog progress;
    private Context ctx;
    private String query;

    public PerformPersonSearchTask(Context ctx) {
        this.ctx = ctx;
    }

    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx,"Wait!","Searching for people...",true);
        super.onPreExecute();
    }

    @Override
    protected List<Person> doInBackground(String... params) {
        query = params[0];
        GenericMoviedbSearch<Person> searchService = new PersonSearch();
        return searchService.search(query, 0);
    }

    @Override
    protected void onPostExecute(final List<Person> results) {
        super.onPostExecute(results);
        progress.dismiss();

        Intent intent = new Intent(ctx, PeopleListActivity.class);
        intent.putExtra("people", ((ArrayList<Person>) results));
        intent.putExtra("query", query);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCancelled() {
        Log.i(getClass().getName(), "Cancelled");
        progress.dismiss();
        super.onCancelled();
    }
}
