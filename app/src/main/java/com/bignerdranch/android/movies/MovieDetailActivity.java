package com.bignerdranch.android.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private ImageView imageViewPosterDetailActivity;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewYear;

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();
        getIntentData();
    }

    private void initViews() {
        imageViewPosterDetailActivity = findViewById(R.id.imageViewPosterDetailActivity);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewYear = findViewById(R.id.textViewYear);
    }

    private void getIntentData() {
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setupViewsDetailActivity(movie);
    }

    private void setupViewsDetailActivity(Movie movie) {
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPosterDetailActivity);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());
    }
}