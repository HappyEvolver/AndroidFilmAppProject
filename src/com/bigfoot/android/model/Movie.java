package com.bigfoot.android.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Models a film.
 * User: Neil Pattinson
 * Date: 01/11/12
 * Time: 18:48
 */
public class Movie implements Serializable {
    private String title;
    private int id;
    private String originalTitle;
    private boolean adult;
    private double popularity;
    private Date releaseDate;

    public Movie() {
    }

    public Movie(String title, int id, String originalTitle, boolean adult, double popularity, Date releaseDate) {
        this.title = title;
        this.id = id;
        this.originalTitle = originalTitle;
        this.adult = adult;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public boolean isAdult() {
        return adult;
    }

    public double getPopularity() {
        return popularity;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String toString() {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NumberFormat nf = new DecimalFormat("0.000");
        StringBuilder sb = new StringBuilder();
        sb.append(id)
                .append(": ")
                .append(title);
        if (originalTitle != null && ! originalTitle.equals(title)) {
            sb.append(" (originally ")
              .append(originalTitle)
              .append(")");
        }
        if (adult) {
            sb.append(". Adult rated");
        }
        sb.append(". Released ")
                .append(sdf.format(releaseDate))
                .append(". Popularity: ")
                .append(nf.format(popularity));

        return sb.toString();
    }
}
