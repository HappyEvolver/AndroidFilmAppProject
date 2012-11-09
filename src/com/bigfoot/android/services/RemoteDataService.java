package com.bigfoot.android.services;

import android.net.http.AndroidHttpClient;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

/**
 * Access data from a remote (internet) resource. Simple wrapper allowing
 * HTML GET of resources at specified URLs.
 *
 * User: Neil Pattinson
 * Date: 04/11/12
 * Time: 12:45
 */
public class RemoteDataService {

    /**
     * Gets a resource as a string.
     * @param url the URL of the target resource.
     * @return a {@code String} representation of the resource.
     */
    public String getStringFrom(String url) {

        HttpClient client = AndroidHttpClient.newInstance("Android");
        HttpGet request = new HttpGet(url);
        request.addHeader("Accept", "application/json");

        try {
            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                Log.w(getClass().getSimpleName(), "Error " + statusCode + " for URL " + url);
                return null;
            }

            if (response.getEntity() != null) {
                return EntityUtils.toString(response.getEntity());
            }
        }
        catch (Exception e) {
            request.abort();
            Log.e(getClass().getSimpleName(), "Unable to access/retrieve resource '" + url + ": ", e);
        }
        finally {
            if (client != null) {
                ((AndroidHttpClient) client).close();
            }
        }

        return null;
    }
}
