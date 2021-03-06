package com.yeminnaing.padc_moviescreenassignment;

import android.app.Application;

import com.yeminnaing.padc_moviescreenassignment.data.model.MovieModel;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class App extends Application {

    public static final String LOG_TAG = "MoviesApp";

    @Override public void onCreate() {
        super.onCreate();
        MovieModel.getInstance().startLoadingPopularMovies(getApplicationContext());
    }

}
