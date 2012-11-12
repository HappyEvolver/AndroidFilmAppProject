package com.bigfoot.android.services;

import android.util.Log;
import com.bigfoot.android.model.Person;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Converts a JSON stream from MovieDb to a list of {@link Person} objects.
 * <br/>User: Neil Pattinson
 * Date: 04/11/12
 * Time: 17:02
 */
public class PersonJsonAdapter {

    public List<Person> getPeopleFromJson(String json) {
        if (json == null || (json.length() == 0)) {
            return new ArrayList<Person>(0);
        }

        JSONArray jPeople = null;
        try {
            JSONObject jObject = (JSONObject) new JSONTokener(json).nextValue();
            jPeople = jObject.getJSONArray("results");
        } catch (JSONException e) {
            Log.e(getClass().getSimpleName(), "Error parsing JSON " + json, e);
        }
        if (jPeople == null) {
            return new ArrayList<Person>(0);
        }

        List<Person> people = new ArrayList<Person>(jPeople.length());
        for(int i = 0; i < jPeople.length(); i++) {
            Person person = null;
            try {
                JSONObject p = (JSONObject) jPeople.get(i);
                person = new Person(p.getInt("id"), p.getString("name"), p.getString("profile_path"));
            } catch (JSONException e) {
                Log.e(getClass().getSimpleName(), "Error deciphering JSON " + json, e);
            }
            people.add(person);
        }

        return people;
    }
}
