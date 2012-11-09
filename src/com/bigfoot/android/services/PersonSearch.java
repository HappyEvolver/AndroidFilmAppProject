package com.bigfoot.android.services;

import com.bigfoot.android.model.Person;

import java.util.List;

/**
 * Performs a search for people on MovieDb.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:28
 */
public class PersonSearch extends GenericMoviedbSearch<Person> {

    @Override
    protected String getSearchType() {
        return "person";
    }

    protected List<Person> buildListFromJSON(String json) {
        return new PersonJsonAdapter().getPeopleFromJsonExplicitly(json);
    }
}
