package com.yeminnaing.padc_moviescreenassignment.event;

import android.content.Context;

import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;

import java.util.List;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class RestApiEvents {

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg){
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg(){
            return errorMsg;
        }
    }

    public static class EmptyResponseEvent {
        private String emptyMsg;

        public EmptyResponseEvent(String emptyMsg){
            this.emptyMsg = emptyMsg;
        }

        public String getEmptyMsg(){
            return emptyMsg;
        }
    }

    public static class PopularMoviesDataLoadedEvent {
        private int loadedPageIndex;
        private List<MoviesVO> loadedMovies;
        private Context context;

        public PopularMoviesDataLoadedEvent(int loadedPageIndex, List<MoviesVO> loadedMovies, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedMovies = loadedMovies;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<MoviesVO> getLoadedMovies() {
            return loadedMovies;
        }

        public Context getContext() {
            return context;
        }
    }

}

