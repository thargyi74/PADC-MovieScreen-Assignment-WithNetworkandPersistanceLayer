package com.yeminnaing.padc_moviescreenassignment.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yeminnaing on 12/14/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";


    private static final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + Contract.MovieEntry.TABLE_NAME + "(" +
            Contract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.MovieEntry.COLUMN_VOTE_COUNT + " TEXT, " +
            Contract.MovieEntry.COLUMN_ID + " TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_VIDEO + "TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT, " +
            Contract.MovieEntry.COLUMN_POPULARITY + " TEXT, " +
            Contract.MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            Contract.MovieEntry.COLUMN_ORIGINL_LANGUAGE + " TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
            Contract.MovieEntry.COLUMN_ADULT + " TEXT, " +
            Contract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            Contract.MovieEntry.COLUMN_RELEASE_DATE + "TEXT NOT NULL, " +

            "UNIQUE (" + Contract.MovieEntry._ID + "," + Contract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE " + " );";


    private static final String SQL_CREATE_GENRE_IDS_TABLE = " CREATE TABLE " + Contract.GenreIdsEntry.TABLE_NAME + "(" +
            Contract.GenreIdsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Contract.GenreIdsEntry.COLUMN_MOVIE_ID + "TEXT NOT NULL, " +
            Contract.GenreIdsEntry.COLUMN_GENRE_IDS + " TEXT NOT NULL, " +
            Contract.GenreIdsEntry.COLUMN_GENRE_NAME + " TEXT NOT NULL, " +

            "UNIQUE (" + Contract.GenreIdsEntry._ID + ") ON CONFLICT IGNORE " + ");";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_GENRE_IDS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contract.MovieEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contract.GenreIdsEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);

    }
}
