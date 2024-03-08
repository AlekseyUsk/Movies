package com.bignerdranch.android.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bignerdranch.android.movies.retrofit.ApiFactory;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewMode";
    private int page = 1;

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> progressBarIsLoaded = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Boolean> getProgressBarIsLoaded() {
        return progressBarIsLoaded;
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public void loadMovies() {
        Boolean loading = progressBarIsLoaded.getValue();
        if (loading != null && loading) {
            return;
        }
        Disposable disposable = ApiFactory.getApiService().loadMovies(page) //передал ее в запрос
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        progressBarIsLoaded.setValue(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        progressBarIsLoaded.setValue(false);
                    }
                })
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Throwable {
                        List<Movie> loadedMovies = movies.getValue();
                        if (loadedMovies != null) {
                            loadedMovies.addAll(movieResponse.getMovies());
                            movies.setValue(loadedMovies);
                        } else {
                            movies.setValue(movieResponse.getMovies());
                        }
                        page++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
