package com.yeminnaing.padc_moviescreenassignment.network.response;

import com.google.gson.annotations.SerializedName;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;

import java.util.List;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class GetPopularMovies extends MovieResponse {

        @SerializedName("code")
        private int code;

        @SerializedName("message")
        private String message;

        @SerializedName("apiVersion")
        private String apiVersion;

        @SerializedName("pageNo")
        private int pageNo;

        @SerializedName("popular-movies")
        private List<MoviesVO> popularMovies;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public int getPageNo() {
            return pageNo;
        }

        public List<MoviesVO> getPopularMovies() {
            return popularMovies;
        }
}
