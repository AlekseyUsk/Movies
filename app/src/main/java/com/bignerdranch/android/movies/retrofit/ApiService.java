package com.bignerdranch.android.movies.retrofit;

import com.bignerdranch.android.movies.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    static final String REQUEST_URL = "movie?token=CGMVVX1-A8EM5GG-H0X78PE-5W5MYDR&field=rating." +
            "kp&search=7-10&sortField=votes.kpsortType=-1&page=2&limit=40";

    @GET(REQUEST_URL)
    Single<MovieResponse> loadMovies();
}
