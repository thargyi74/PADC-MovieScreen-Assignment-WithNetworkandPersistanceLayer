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
    public static final String PATH_MOVIES_GENREIDS = "movies_genreids";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ORIGINL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static Uri buildMoviesUri(long id) {
            //content://com.yeminnaing.padc_moviescreenassignment/movies/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMoviesUriWithTitle(String moviesTitle) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, moviesTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }

        /*public static long getIdFromParam(Uri uri){
            return uri
        }*/

    }

    public static final class GenreIdsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES_GENREIDS)
                .build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES_GENREIDS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES_GENREIDS;

        public static final String TABLE_NAME = "genreIds";

        public static final String COLUMN_GENRE_IDS = "genre_ids";

        public static Uri buildGenreIdsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }


}
