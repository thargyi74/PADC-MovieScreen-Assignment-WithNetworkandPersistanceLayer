package com.yeminnaing.padc_moviescreenassignment.network.api;

import android.content.Context;

import com.google.gson.Gson;
import com.yeminnaing.padc_moviescreenassignment.event.RestApiEvents;
import com.yeminnaing.padc_moviescreenassignment.network.response.GetPopularMovies;
import com.yeminnaing.padc_moviescreenassignment.network.response.MovieCallBack;
import com.yeminnaing.padc_moviescreenassignment.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class MovieDataAgnetImpl implements MovieDataAgent {

    private static MovieDataAgent objInstance;

    private PopularMovieAPI movieAPI;

    private MovieDataAgnetImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        movieAPI = retrofit.create(PopularMovieAPI.class);
    }

    public static MovieDataAgent getInstance() {
        if(objInstance == null) {
            objInstance = new MovieDataAgnetImpl();
        }
        return objInstance;
    }

    @Override
    public void loadPopularMovies(String accessToken, int pageNo, final Context context) {
        /*Call<GetPopularMovies> loadPopularMoviesCall = movieAPI.loadPopularMovies(accessToken, pageNo);
        loadPopularMoviesCall.enqueue(new Callback<GetPopularMovies>() {
            @Override
            public void onResponse(Call<GetPopularMovies> call, Response<GetPopularMovies> response) {
                GetPopularMovies getMovieResponse = response.body();
                if(getMovieResponse != null && getMovieResponse.getPopularMovies().size() > 0) {
                    RestApiEvents.PopularMoviesDataLoadedEvent popularMoviesDataLoadedEvent = new RestApiEvents.PopularMoviesDataLoadedEvent(getMovieResponse.getPageNo(), getMovieResponse.getPopularMovies(), context);
                    EventBus.getDefault().post(popularMoviesDataLoadedEvent);
                } else {
                    RestApiEvents.EmptyResponseEvent emptyResponseEvent = new RestApiEvents.EmptyResponseEvent("No popular movies could be loaded for now. Please try again later.");
                    EventBus.getDefault().post(emptyResponseEvent);
                }
            }

            @Override
            public void onFailure(Call<GetPopularMovies> call, Throwable t) {
                RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorInvokingAPIEvent);
            }
        });*/

        Call<GetPopularMovies> loadMovieCall = movieAPI.loadPopularMovies(accessToken, pageNo);
        loadMovieCall.enqueue(new MovieCallBack<GetPopularMovies>() {
            @Override
            public void onResponse(Call<GetPopularMovies> call, Response<GetPopularMovies> response) {
                super.onResponse(call, response);
                GetPopularMovies getPopularMoviesResponse = response.body();
                if (getPopularMoviesResponse != null
                        && getPopularMoviesResponse.getPopularMovies().size() > 0) {
                    RestApiEvents.PopularMoviesDataLoadedEvent movieDataLoadedEvent = new RestApiEvents.PopularMoviesDataLoadedEvent
                            (getPopularMoviesResponse.getPageNo(), getPopularMoviesResponse.getPopularMovies(), context);
                    EventBus.getDefault().post(movieDataLoadedEvent);
                }
            }
        });
    }
}
