package com.yeminnaing.padc_moviescreenassignment.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeminnaing on 12/14/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "movies.db";


    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + Contract.MovieEntry.TABLE_NAME + " (" +
            Contract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            Contract.MovieEntry.COLUMN_MOVIE_ID + " TEXT, " +
            Contract.MovieEntry.COLUMN_IS_VIDEO + " BOOLEAN, " +
            Contract.MovieEntry.COLUMN_VOTE_AVERAGE + " DOUBLE, " +
            Contract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_POPULARITY + " DOUBLE, " +
            Contract.MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            Contract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            Contract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT, " +
            Contract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
            Contract.MovieEntry.COLUMN_IS_ADULT + " BOOLEAN, " +
            Contract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            Contract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + Contract.MovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_GENRE_TABLE = "CREATE TABLE " + Contract.GenreEntry.TABLE_NAME + " (" +
            Contract.GenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.GenreEntry.COLUMN_GENRE_ID + " TEXT, " +
            Contract.GenreEntry.COLUMN_GENRE_NAME + " TEXT, " +

            " UNIQUE (" + Contract.GenreEntry.COLUMN_GENRE_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + Contract.MovieGenreEntry.TABLE_NAME + " (" +
            Contract.MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.MovieGenreEntry.COLUMN_GENRE_ID + " TEXT, " +
            Contract.MovieGenreEntry.COLUMN_MOVIE_ID + " TEXT" +

            " );";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
        db.execSQL(SQL_CREATE_GENRE_TABLE);
        db.execSQL(SQL_CREATE_MOVIE_GENRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Contract.MovieGenreEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.GenreEntry.TABLE_NAME);

        onCreate(db);
    }
}
