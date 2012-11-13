package com.bigfoot.android.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.androidquery.AQuery;
import com.bigfoot.android.R;
import com.bigfoot.android.model.Person;

import java.util.List;

/**
 * User: Neil Pattinson
 * Date: 13/11/12
 * Time: 16:45
 */
public class PersonListAdapter extends ArrayAdapter<Person> {

    private static final String IMAGE_SIZE = "/w45";
    private Activity context;
    private List<Person> people;

    public PersonListAdapter(Context context, int textViewResourceId, List<Person> personData) {
        super(context, textViewResourceId, personData);
        this.context = (Activity) context;
        this.people = personData;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.person_row, null);
        }

        Person person = people.get(position);
        if (person != null) {
            AQuery aq = new AQuery(view);

            // Image
            String url = FilmListAdapter.IMG_THUMB_BASE_URL + IMAGE_SIZE + person.getProfilePath();
            aq.id(R.id.person_thumb).image(url, true, false, 0, 0, null, AQuery.FADE_IN_NETWORK);

            // Name
            aq.id(R.id.person_name_text_view).text(person.getName());
        }

        return view;
    }
}
