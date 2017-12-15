package com.yeminnaing.padc_moviescreenassignment.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeminnaing on 12/15/17.
 */

public class MovieReviewVO {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
