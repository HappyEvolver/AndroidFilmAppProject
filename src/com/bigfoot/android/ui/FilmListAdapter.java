package com.bigfoot.android.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigfoot.android.R;
import com.bigfoot.android.model.Movie;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * User: Neil Pattinson
 * Date: 13/11/12
 * Time: 12:40
 */
public class FilmListAdapter extends ArrayAdapter<Movie> {

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
            DateFormat df = new SimpleDateFormat("dd MMM yyyy");
            NumberFormat nf = new DecimalFormat("0.00");

            // Title
            TextView titleView = (TextView) view.findViewById(R.id.name_text_view);
            titleView.setText(film.getTitle());

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

            // Original title
            TextView originalTitleView = (TextView) view.findViewById(R.id.alternate_name_text_view);
            if (film.getOriginalTitleIfDifferent() != null) {
                originalTitleView.setText("Original title: " + film.getOriginalTitle());
            }
            else {
                originalTitleView.setVisibility(View.GONE);
            }

            // Vote
            TextView voteView = (TextView) view.findViewById(R.id.vote_text_view);
            voteView.setText("Vote average: " + nf.format(film.getVoteAverage()));

            // Poster thumbnail
            ImageView posterView = (ImageView) view.findViewById(R.id.film_poster_icon);
            posterView.setImageBitmap(null);
        }

        return view;
    }
}
