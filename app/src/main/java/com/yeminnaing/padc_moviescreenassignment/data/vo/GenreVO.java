package com.yeminnaing.padc_moviescreenassignment.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeminnaing on 12/15/17.
 */

public class GenreVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
