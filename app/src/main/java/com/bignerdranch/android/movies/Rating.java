package com.bignerdranch.android.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private String ratingKp;

    public Rating(String kp) {
        this.ratingKp = ratingKp;
    }

    public String getRatingKp() {
        return ratingKp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + ratingKp + '\'' +
                '}';
    }
}
