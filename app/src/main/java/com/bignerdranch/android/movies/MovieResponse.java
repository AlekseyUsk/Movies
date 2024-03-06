package com.bignerdranch.android.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    /**Устранение Апфускации*/
    @SerializedName("docs")
    private List<Movie> movies;


    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
