package com.bigfoot.android;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.androidquery.util.AQUtility;
import com.bigfoot.android.ui.AboutDialog;

/**
 * Master activity class for film search app.
 */
public class FilmSearchAppActivity extends Activity {
    private EditText searchEditText;
    private RadioButton moviesSearchRadioButton;
    private RadioButton peopleSearchRadioButton;
    private RadioGroup searchRadioGroup;
    private TextView searchTypeTextView;
    private Button searchButton;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Tell AQUtility about context: it can use this globally...which is really handy!
        AQUtility.setContext(getApplication());
        // Load default preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        findAllViewsById();

        View.OnClickListener radioButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                searchTypeTextView.setText("Searching for " + radioButton.getText());
            }
        };

        moviesSearchRadioButton.setOnClickListener(radioButtonListener);
        peopleSearchRadioButton.setOnClickListener(radioButtonListener);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchEditText.getText().toString();
                if (moviesSearchRadioButton.isChecked()) {
                    PerformFilmSearchTask task = new PerformFilmSearchTask(FilmSearchAppActivity.this);
                    task.execute(query);
                } else if (peopleSearchRadioButton.isChecked()) {
                    PerformPersonSearchTask task = new PerformPersonSearchTask(FilmSearchAppActivity.this);
                    task.execute(query);
                }
            }
        });

        int radioId = searchRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioId);
        searchTypeTextView.setText(radioButton.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;

            case R.id.menu_about:
                Dialog aboutDialog = new AboutDialog(this);
                aboutDialog.show();
                return true;

            // Item not recognised...
            default:
                Log.e(getLocalClassName(), "Weird! Unrecognised menu option..." + item.getTitle());
                return super.onOptionsItemSelected(item);
        }
    }

    private void findAllViewsById() {
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        moviesSearchRadioButton = (RadioButton) findViewById(R.id.movie_search_radio_button);
        peopleSearchRadioButton = (RadioButton) findViewById(R.id.people_search_radio_button);
        searchRadioGroup = (RadioGroup) findViewById(R.id.search_radio_group);
        searchTypeTextView = (TextView) findViewById(R.id.search_type_text_view);
        searchButton = (Button) findViewById(R.id.search_button);
    }
}
