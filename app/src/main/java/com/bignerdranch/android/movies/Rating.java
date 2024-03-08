package com.bignerdranch.android.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    private double ratingKp;

    public Rating(double kp) {
        this.ratingKp = ratingKp;
    }

    public double getRatingKp() {
        return ratingKp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingKp=" + ratingKp +
                '}';
    }
}
