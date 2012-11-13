package com.bigfoot.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.bigfoot.android.model.Movie;
import com.bigfoot.android.ui.FilmListAdapter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Activity to list a set of films, typically the results of a search.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 19:51
 */
public class FilmListActivity extends ListActivity {

    private ArrayAdapter<Movie> filmsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.films_layout);

        Serializable extras = getIntent().getSerializableExtra("films");
        List<Movie> filmList;
        if (extras == null) {
            filmList = Collections.emptyList();
        }
        else {
            //noinspection unchecked
            filmList = (List<Movie>) extras;
        }
        filmsAdapter = new FilmListAdapter(this, R.layout.film_data_row, filmList);

        setListAdapter(filmsAdapter);
        filmsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        super.onListItemClick(lv, v, position, id);
        Movie film = filmsAdapter.getItem(position);
        Toast.makeText(this, "Now showing: " + film.toString(), Toast.LENGTH_LONG).show();
    }
}