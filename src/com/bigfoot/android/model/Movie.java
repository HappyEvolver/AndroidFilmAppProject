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
    private double voteAverage;
    private Date releaseDate;
    private String posterPath;

    public Movie() {
    }

    public Movie(String title, int id, String originalTitle, boolean adult, double popularity, double voteAverage, Date releaseDate, String posterPath) {
        this.title = title;
        this.id = id;
        this.originalTitle = originalTitle;
        this.adult = adult;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
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

    /**
     * Gets the original title of the film, if it is non-null and different from
     * the current title. Otherwise returns {@code null}.
     *
     * @return the original title of the film, if different from the current title.
     */
    public String getOriginalTitleIfDifferent() {
        if (originalTitle != null && ! originalTitle.equals(title)) {
            return originalTitle;
        }
        return null;
    }

    public boolean isAdult() {
        return adult;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String toString() {
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        NumberFormat nf = new DecimalFormat("0.000");
        StringBuilder sb = new StringBuilder();
        sb.append(id)
                .append(": ")
                .append(title);
        if (getOriginalTitleIfDifferent() != null) {
            sb.append(" (originally ")
              .append(originalTitle)
              .append(")");
        }
        if (adult) {
            sb.append(". Adult rated");
        }
        sb.append(". Released ");
        if (releaseDate == null) {
            sb.append("(unknown)");
        }
        else {
            sb.append(sdf.format(releaseDate));
        }
        sb.append(". Popularity: ")
            .append(nf.format(popularity))
            .append(", vote: ")
            .append(nf.format(voteAverage));

        return sb.toString();
    }
}
