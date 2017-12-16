package com.yeminnaing.padc_moviescreenassignment.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.adapters.TrailerMovieAdapter;

public class MovieDetailsActivity extends Activity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context.getApplicationContext(), MovieDetailsActivity.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        RecyclerView rvTrailerMovies = findViewById(R.id.rv_trailers);
        rvTrailerMovies.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        TrailerMovieAdapter trailerMovieAdapter = new TrailerMovieAdapter(getApplicationContext());
        rvTrailerMovies.setAdapter(trailerMovieAdapter);
    }
}
