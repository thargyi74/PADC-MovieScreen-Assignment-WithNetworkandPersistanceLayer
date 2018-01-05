package com.yeminnaing.padc_moviescreenassignment.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.yeminnaing.padc_moviescreenassignment.persistance.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeminnaing on 12/13/17.
 */

public class MoviesVO {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public ContentValues parseToContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.MovieEntry.COLUMN_MOVIE_ID, id);
        contentValues.put(Contract.MovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(Contract.MovieEntry.COLUMN_IS_VIDEO, video ? 1 : 0);
        contentValues.put(Contract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
        contentValues.put(Contract.MovieEntry.COLUMN_TITLE, title);
        contentValues.put(Contract.MovieEntry.COLUMN_POPULARITY, popularity);
        contentValues.put(Contract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(Contract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(Contract.MovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(Contract.MovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
        contentValues.put(Contract.MovieEntry.COLUMN_IS_ADULT, adult ? 1 : 0);
        contentValues.put(Contract.MovieEntry.COLUMN_OVERVIEW, overview);
        contentValues.put(Contract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
        return contentValues;

    }

    public static MoviesVO parseFromCursor(Context context, Cursor cursor){

        MoviesVO movies = new MoviesVO();
        movies.id = cursor.getInt(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_MOVIE_ID));
        movies.voteCount = cursor.getInt(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_VOTE_COUNT));
        movies.video = cursor.getInt(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_IS_VIDEO)) == 1;
        movies.voteAverage = cursor.getFloat(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_VOTE_AVERAGE));
        movies.title = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_TITLE));
        movies.popularity = cursor.getFloat(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_POPULARITY));
        movies.posterPath = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_POSTER_PATH));
        movies.originalLanguage = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        movies.originalTitle = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_ORIGINAL_TITLE));
        movies.backdropPath = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_BACKDROP_PATH));
        movies.adult = cursor.getInt(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_IS_ADULT)) == 1;
        movies.overview = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_OVERVIEW));
        movies.releaseDate = cursor.getString(cursor.getColumnIndex(Contract.MovieEntry.COLUMN_RELEASE_DATE));
        return movies;

    }

    private static List<Integer> loadGenreInMovie(Context context, String movieId) {
        Cursor genreInMovieCursor = context.getContentResolver().query(Contract.MovieGenreEntry.CONTENT_URI,
                null,
                Contract.MovieGenreEntry.COLUMN_MOVIE_ID + " = ?", new String[]{movieId},
                null);

        if (genreInMovieCursor != null && genreInMovieCursor.moveToFirst()) {
            List<Integer> genreInMovie = new ArrayList<>();
            do {
                genreInMovie.add(
                        genreInMovieCursor.getInt(
                                genreInMovieCursor.getColumnIndex(Contract.MovieGenreEntry.COLUMN_GENRE_ID)
                        )
                );
            } while (genreInMovieCursor.moveToNext());
            genreInMovieCursor.close();
            return genreInMovie;
        }
        return null;
    }
}
