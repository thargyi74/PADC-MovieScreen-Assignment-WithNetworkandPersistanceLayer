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

        ContentValues[] moviesCVs = new ContentValues[event.getLoadedMovies().size()];
        for(int index=0; index< moviesCVs.length; index++){
            moviesCVs[index] = event.getLoadedMovies().get(index).parseToContentValues();

        }

       int instertedRow = event.getContext().getContentResolver().bulkInsert(Contract.MovieEntry.CONTENT_URI, moviesCVs);
        Log.d(App.LOG_TAG, "Inserted Row: " + instertedRow);


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
