package com.yeminnaing.padc_moviescreenassignment.network.response;

import com.yeminnaing.padc_moviescreenassignment.event.RestApiEvents;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yeminnaing on 1/5/18.
 */

public abstract class MovieCallBack<T extends MovieResponse> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        MovieResponse movieResponse = response.body();
        if (movieResponse == null) {
            RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent
                    = new RestApiEvents.ErrorInvokingAPIEvent("NO data to load for now");
            EventBus.getDefault().post(errorInvokingAPIEvent);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent(t.getMessage());
        EventBus.getDefault().post(errorInvokingAPIEvent);
    }
}