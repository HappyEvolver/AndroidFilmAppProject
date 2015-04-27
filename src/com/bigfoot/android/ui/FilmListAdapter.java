package com.bigfoot.android.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.androidquery.AQuery;
import com.bigfoot.android.R;
import com.bigfoot.android.model.Movie;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * User: Neil Pattinson
 * Date: 13/11/12
 * Time: 12:40
 */
public class FilmListAdapter extends ArrayAdapter<Movie> {

    public static final String IMG_THUMB_BASE_URL = "http://cf2.imgobject.com/t/p";
    private static final String IMAGE_SIZE = "/w92";
    private Activity context;
    private List<Movie> films;

    public FilmListAdapter(Context context, int textViewResourceId, List<Movie> filmData) {
        super(context, textViewResourceId, filmData);
        this.context = (Activity) context;
        this.films = filmData;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.film_data_row, null);
        }

        Movie film = films.get(position);
        if (film != null) {
            DateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
            NumberFormat nf = new DecimalFormat("0.00");
            // AQuery handles all image retrieval, decoding and caching transparently.
            AQuery aq = new AQuery(view);

            // Title
            TextView titleView = (TextView) view.findViewById(R.id.film_title_text_view);
            titleView.setText(film.getTitle());

            // Original title
            TextView originalTitleView = (TextView) view.findViewById(R.id.alternate_title_text_view);
            if (film.getOriginalTitleIfDifferent() != null) {
                originalTitleView.setText("Original title: " + film.getOriginalTitle());
            }
            else {
                originalTitleView.setVisibility(View.GONE);
            }

            // Release date
            TextView releaseDateView = (TextView) view.findViewById(R.id.release_date_text_view);
            StringBuilder sb = new StringBuilder("Release date: ");
            if (film.getReleaseDate() == null) {
                sb.append("(unknown)");
            }
            else {
                sb.append(df.format(film.getReleaseDate()));
            }
            releaseDateView.setText(sb.toString());

            // Vote
            TextView voteView = (TextView) view.findViewById(R.id.vote_text_view);
            voteView.setText("Vote average: " + nf.format(film.getVoteAverage()));

            // Poster thumbnail
            String url = IMG_THUMB_BASE_URL + IMAGE_SIZE + film.getPosterPath();
            aq.id(R.id.film_poster_icon).image(url, true, false);
        }

        return view;
    }
}
