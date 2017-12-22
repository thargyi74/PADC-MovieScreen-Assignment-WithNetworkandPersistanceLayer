package com.yeminnaing.padc_moviescreenassignment.data.vo;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.yeminnaing.padc_moviescreenassignment.persistance.Contract;

/**
 * Created by yeminnaing on 12/15/17.
 */

public class GenreVO {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("popular-movies")
    private MoviesVO moviesVO;

    public MoviesVO getMoviesVO() {
        return moviesVO;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ContentValues parseToContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.GenreIdsEntry.COLUMN_GENRE_IDS, id);
        contentValues.put(Contract.GenreIdsEntry.COLUMN_GENRE_NAME, name);
        contentValues.put(Contract.GenreIdsEntry.COLUMN_MOVIE_ID, moviesVO.getId());
        return contentValues;
    }
}
