package com.bigfoot.android.model;

import java.io.Serializable;

/**
 * Models a person.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:29
 */
public class Person implements Serializable {
    private int id;
    private String name;
    private String profilePath;

    public Person(int id, String name, String profilePath) {
        this.name = name;
        this.id = id;
        this.profilePath = profilePath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }
}
