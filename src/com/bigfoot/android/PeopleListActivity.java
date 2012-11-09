package com.bigfoot.android;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.bigfoot.android.model.Person;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
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
        peopleAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, peopleList);

        setListAdapter(peopleAdapter);
    }

    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {
        super.onListItemClick(lv, v, position, id);
        Person person = peopleAdapter.getItem(position);
        Toast.makeText(this, "You selected: " + person.toString(), Toast.LENGTH_LONG).show();
    }
}