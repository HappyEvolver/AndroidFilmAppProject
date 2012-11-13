package com.bigfoot.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.bigfoot.android.services.PageLocatorService;
import com.bigfoot.android.services.SearchType;

/**
 * An asynchronous task to locate the URL of a person or film, and to launch
 * a web browser to render said URL.
 * User: Neil Pattinson
 * Date: 13/11/12
 * Time: 17:49
 */
public class LocateWebPageTask extends AsyncTask<String, Void, String> {

    private Context context;
    private ProgressDialog progress;

    public LocateWebPageTask(Context ctx) {
        this.context = ctx;
    }

    protected void onPreExecute() {
        progress = ProgressDialog.show(context, null, null, true);
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String id = params[1];
        SearchType st = SearchType.valueOf(type);
        return new PageLocatorService().buildUri(st, id);
    }

    protected void onPostExecute(final String result) {
        progress.dismiss();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
        context.startActivity(intent);
    }
}
