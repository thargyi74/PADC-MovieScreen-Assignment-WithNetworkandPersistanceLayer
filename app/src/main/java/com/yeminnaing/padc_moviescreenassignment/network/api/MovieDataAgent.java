package com.yeminnaing.padc_moviescreenassignment.network.api;

import android.content.Context;

/**
 * Created by yeminnaing on 12/13/17.
 */

public interface MovieDataAgent {
    void loadPopularMovies(String accessToken, int pageNo, final Context context);
}