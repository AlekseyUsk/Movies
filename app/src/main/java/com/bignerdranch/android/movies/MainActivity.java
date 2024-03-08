package com.bignerdranch.android.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;
    private Adapter adapterMovies;
    private MainViewModel viewModel;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRecyclerViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        installingTheReceivedDataOnTheScreen();
        startOfANewCardDownloadWhenScrolling();
        displayingTheProgressBarWhenLoadingData();
        onClickItemToAdapter();
    }

    private void installingTheReceivedDataOnTheScreen() {
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapterMovies.setMovies(movies);
            }
        });
    }

    private void displayingTheProgressBarWhenLoadingData() {
        viewModel.getProgressBarIsLoaded().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBarLoading.setVisibility(View.VISIBLE);
                } else {
                    progressBarLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initRecyclerViews() {
        adapterMovies = new Adapter();
        recyclerViewMovies.setAdapter(adapterMovies);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void startOfANewCardDownloadWhenScrolling() {
        adapterMovies.setOnReachEndListener(new Adapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadMovies();
            }
        });
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBarLoading = findViewById(R.id.progressBarLoading);
    }

    private void onClickItemToAdapter() {
        adapterMovies.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(MainActivity.this, movie);
                startActivity(intent);
            }
        });
    }
}