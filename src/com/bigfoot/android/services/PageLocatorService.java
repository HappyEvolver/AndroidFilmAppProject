package com.bigfoot.android.services;

/**
 * A service to locate the URL of a webpage for a film or person.
 * User: Neil Pattinson
 * Date: 13/11/12
 * Time: 18:10
 */
public class PageLocatorService {

    /**
     * Builds the URI (as a string) for the requested resource.
     * @param type the type of resource to locate.
     * @param id   the ID of the resource.
     * @return the URI of the resource, as a {@code String}.
     */
    public String buildUri(SearchType type, String id) {
        StringBuilder sb = new StringBuilder("http://www.themoviedb.org/");
        switch (type) {
            case FILM:
                sb.append("movie/").append(id);
                break;
            case PERSON:
                sb.append("person/").append(id);
                break;
            default:
                break;
        }
        return sb.toString();
    }
}
