package com.bignerdranch.android.movies.retrofit;

import com.bignerdranch.android.movies.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**Внутрь запроса можно передавать ДИНАМИЧЕСКИ какие то данные к примеру как тут постоянно загружается первая страница - page
     * для этого создаем анотацию - @Query("page") по каторой будет вставлять данное значение в запрос int page)
     *таких параметров можно передавать сколько захочешь*/

    static final String REQUEST_URL = "movie?token=CGMVVX1-A8EM5GG-H0X78PE-5W5MYDR&field=rating." +
            "kp&search=4-10&sortField=votes.kpsortType=-1&limit=40";

    @GET(REQUEST_URL)
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
