package com.yeminnaing.padc_moviescreenassignment.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

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
        contentValues.put(Contract.GenreEntry.COLUMN_GENRE_ID, id);
        contentValues.put(Contract.GenreEntry.COLUMN_GENRE_NAME, name);
        return contentValues;
    }
    private static GenreVO parseFromCursor(Cursor genreCursor) {
        GenreVO genre = new GenreVO();
        genre.id = genreCursor.getString(genreCursor.getColumnIndex(Contract.GenreEntry.COLUMN_GENRE_ID));
        genre.name = genreCursor.getString(genreCursor.getColumnIndex(Contract.GenreEntry.COLUMN_GENRE_NAME));
        return genre;
    }
}
