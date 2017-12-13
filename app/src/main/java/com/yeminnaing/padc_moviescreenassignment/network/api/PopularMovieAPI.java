package com.yeminnaing.padc_moviescreenassignment.network.api;

import com.yeminnaing.padc_moviescreenassignment.network.response.GetPopularMovies;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yeminnaing on 12/13/17.
 */

public interface PopularMovieAPI {
    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    public Call<GetPopularMovies> loadPopularMovies(@Field("access_token") String accessToken,
                                                    @Field("page") int pageIndex);
}
