package com.bigfoot.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bigfoot.android.model.Person;
import com.bigfoot.android.services.SearchType;
import com.bigfoot.android.ui.PersonListAdapter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Activity to list a set of people, typically the results of a search.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 19:53
 */
public class PeopleListActivity extends ListActivity {

    private ArrayAdapter<Person> peopleAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_layout);

        Serializable extras = getIntent().getSerializableExtra("people");
        List<Person> peopleList;
        if (extras == null) {
            peopleList = Collections.emptyList();
        }
        else {
        //noinspection unchecked
        peopleList = (List<Person>) extras;
        }
        peopleAdapter = new PersonListAdapter(this, R.layout.person_row, peopleList);

        setListAdapter(peopleAdapter);
        peopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        super.onListItemClick(lv, v, position, id);
        Person person = peopleAdapter.getItem(position);
        LocateWebPageTask task = new LocateWebPageTask(this);
        task.execute(SearchType.PERSON.name(), Integer.toString(person.getId()));
    }
}