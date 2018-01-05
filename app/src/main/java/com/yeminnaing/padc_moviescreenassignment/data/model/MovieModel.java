package com.yeminnaing.padc_moviescreenassignment.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.yeminnaing.padc_moviescreenassignment.App;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.event.RestApiEvents;
import com.yeminnaing.padc_moviescreenassignment.network.api.MovieDataAgnetImpl;
import com.yeminnaing.padc_moviescreenassignment.persistance.Contract;
import com.yeminnaing.padc_moviescreenassignment.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<MoviesVO> mMovies;
    private int mMoviesPageIndex = 1;

    private MovieModel() {
        EventBus.getDefault().register(this);
        mMovies = new ArrayList<>();
    }

    public static MovieModel getInstance() {
        if(objInstance == null){
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public void startLoadingPopularMovies(Context context){
        MovieDataAgnetImpl.getInstance().loadPopularMovies(AppConstants.ACCESS_TOKEN, mMoviesPageIndex, context);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMoviesDataLoaded(RestApiEvents.PopularMoviesDataLoadedEvent event){
        mMovies.addAll(event.getLoadedMovies());
        mMoviesPageIndex = event.getLoadedPageIndex() + 1;

        ContentValues[] movieCVs = new ContentValues[event.getLoadedMovies().size()];
        List<ContentValues> genreCVList = new ArrayList<>();
        List<ContentValues> movieGenreCVList = new ArrayList<>();

        for (int index = 0; index < movieCVs.length; index++) {

            MoviesVO movies = event.getLoadedMovies().get(index);
            movieCVs[index] = movies.parseToContentValues();

            for (int genreId : movies.getGenreIds()) {
                ContentValues genreIdInMovieCV = new ContentValues();
                genreIdInMovieCV.put(Contract.GenreEntry.COLUMN_GENRE_ID, genreId);
                genreCVList.add(genreIdInMovieCV);
            }

            for (int i = 0; i < movies.getGenreIds().size(); i++) {
                ContentValues movieGenreCV = new ContentValues();
                movieGenreCV.put(Contract.MovieGenreEntry.COLUMN_GENRE_ID, String.valueOf(movies.getGenreIds()));
                movieGenreCV.put(Contract.MovieGenreEntry.COLUMN_MOVIE_ID, movies.getId());
                movieGenreCVList.add(movieGenreCV);
            }
        }

        int insertedGenre = event.getContext().getContentResolver().bulkInsert(Contract.GenreEntry.CONTENT_URI,
                genreCVList.toArray(new ContentValues[0]));
        Log.d(App.LOG_TAG, "insertedGenre" + insertedGenre);

        int insertedMovieGenre = event.getContext().getContentResolver().bulkInsert(Contract.MovieGenreEntry.CONTENT_URI,
                movieGenreCVList.toArray(new ContentValues[0]));
        Log.d(App.LOG_TAG, "insertedMovieGenre" + insertedMovieGenre);

        int insertedMovies = event.getContext().getContentResolver().bulkInsert(Contract.MovieEntry.CONTENT_URI,
                movieCVs);
        Log.d(App.LOG_TAG, "Inserted News" + insertedMovies);


    }

    public MoviesVO getMovieById(int id){
        for(MoviesVO movies : mMovies){
            if(movies.getId()== id){
                return movies;
            }
        }
        return null;
    }
}
