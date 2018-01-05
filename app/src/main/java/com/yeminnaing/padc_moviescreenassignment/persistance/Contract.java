package com.yeminnaing.padc_moviescreenassignment.persistance;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.yeminnaing.padc_moviescreenassignment.App;

/**
 * Created by yeminnaing on 12/14/17.
 */

public class Contract {

    public static final String CONTENT_AUTHORITY = App.class.getPackage().getName();
    //com.yeminnaing.padc_moviescreenassignment
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //content://com.yeminnaing.padc_moviescreenassignment

    public static final String PATH_MOVIES = "movies";
    public static final String PATH_GENRE = "genre";
    public static final String PATH_MOVIE_GENRE = "movie_genre";


    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_IS_VIDEO = "is_video";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_IS_ADULT = "is_adult";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieUriWithTitle(String movieTitle) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, movieTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }
    public static final class GenreEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE;

        public static final String TABLE_NAME = "genre";

        public static final String COLUMN_GENRE_ID = "genre_id";
        public static final String COLUMN_GENRE_NAME = "genre_name";

        public static Uri buildGenreUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildGenriUriWithGenreName(String genreName) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_GENRE_NAME, genreName)
                    .build();
        }

        public static String getGenreNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_GENRE_NAME);
        }
    }
    public static final class MovieGenreEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_GENRE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_GENRE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE_GENRE;

        public static final String TABLE_NAME = "movie_genre";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_GENRE_ID = "genre_id";

        public static Uri buildMovieGenreUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieGenreUriWithMovieId(String movieId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_MOVIE_ID, movieId)
                    .build();
        }

        public static String getMovieIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_MOVIE_ID);
        }

        public static Uri buildMovieGenreUriWithGenreId(String genreId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_GENRE_ID, genreId)
                    .build();
        }

        public static String getGenreIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_GENRE_ID);
        }
    }
}

