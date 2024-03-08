package com.bignerdranch.android.movies;

import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        installingTheReceivedDataOnTheScreen();
        viewModel.loadMovies();
        startOfANewCardDownloadWhenScrolling();
    }

    private void installingTheReceivedDataOnTheScreen() {
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapterMovies.setMovies(movies);
            }
        });
    }

    private void initRecyclerViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
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
}