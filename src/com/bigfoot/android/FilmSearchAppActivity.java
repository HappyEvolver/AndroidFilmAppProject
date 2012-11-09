package com.bigfoot.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

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

    private void findAllViewsById() {
        searchEditText = (EditText) findViewById(R.id.search_edit_text);
        moviesSearchRadioButton = (RadioButton) findViewById(R.id.movie_search_radio_button);
        peopleSearchRadioButton = (RadioButton) findViewById(R.id.people_search_radio_button);
        searchRadioGroup = (RadioGroup) findViewById(R.id.search_radio_group);
        searchTypeTextView = (TextView) findViewById(R.id.search_type_text_view);
        searchButton = (Button) findViewById(R.id.search_button);
    }
}
