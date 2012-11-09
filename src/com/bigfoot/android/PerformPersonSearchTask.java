package com.bigfoot.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

    public PerformPersonSearchTask(Context ctx) {
        this.ctx = ctx;
    }

    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx,"Wait!","Searching for people...",true);
    }

    @Override
    protected List<Person> doInBackground(String... params) {
        String queryArg = params[0];
        GenericMoviedbSearch<Person> searchService = new PersonSearch();
        return searchService.search(queryArg, 0);
    }

    protected void onPostExecute(final List<Person> results) {
        progress.dismiss();

        Intent intent = new Intent(ctx, PeopleListActivity.class);
        intent.putExtra("people", ((ArrayList<Person>) results));
        ctx.startActivity(intent);
    }
}
