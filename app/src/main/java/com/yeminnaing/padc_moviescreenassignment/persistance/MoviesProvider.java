package com.yeminnaing.padc_moviescreenassignment.persistance;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Created by yeminnaing on 12/20/17.
 */

public class MoviesProvider extends ContentProvider {

    public static final int MOVIES = 100;
    public static final int GENER = 200;
    public static final int MOVIE_GENER = 300;



    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private DBHelper mDBHelper;


    private static final SQLiteQueryBuilder sMovieWithGenere_IJ;

    static {
        sMovieWithGenere_IJ = new SQLiteQueryBuilder();
        sMovieWithGenere_IJ.setTables(
                Contract.MovieEntry.TABLE_NAME + " INNER JOIN " +
                        Contract.GenreEntry.TABLE_NAME + " ON " +
                        Contract.MovieEntry.TABLE_NAME + "." + Contract.MovieEntry.COLUMN_MOVIE_ID + " = " +
                        Contract.MovieGenreEntry.TABLE_NAME + "." + Contract.MovieGenreEntry.COLUMN_MOVIE_ID +
                        " INNER JOIN " + Contract.GenreEntry.TABLE_NAME + " ON " +
                        Contract.GenreEntry.TABLE_NAME + "." + Contract.GenreEntry.COLUMN_GENRE_ID + " = " +
                        Contract.MovieGenreEntry.TABLE_NAME + "." + Contract.MovieGenreEntry.COLUMN_GENRE_ID
        );
    }



    private static UriMatcher buildUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_MOVIES, GENER);
        uriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_MOVIES, MOVIE_GENER);

        return uriMatcher;
    }

    private String getTableName(Uri uri){
        switch(sUriMatcher.match(uri)){
            case MOVIES:
                return Contract.MovieEntry.TABLE_NAME;
            case GENER:
                return Contract.GenreEntry.TABLE_NAME;
            case MOVIE_GENER:
                return Contract.MovieGenreEntry.TABLE_NAME;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    private Uri getContentUri(Uri uri){
        switch (sUriMatcher.match(uri)){
            case MOVIES:
                return Contract.MovieEntry.CONTENT_URI;
            case GENER:
                return Contract.GenreEntry.CONTENT_URI;
            case MOVIE_GENER:
                return Contract.MovieGenreEntry.CONTENT_URI;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }


    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor queryCursor;
        switch (sUriMatcher.match(uri)) {
            case MOVIES:
                queryCursor = sMovieWithGenere_IJ.query(mDBHelper.getReadableDatabase(),
                        projection,
                        selection,
                        selectionArgs,
                        Contract.MovieEntry.COLUMN_MOVIE_ID,
                        null,
                        sortOrder);
                break;
            default:
                queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri), projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
        }


        if(getContext() != null){
            queryCursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri){
            case MOVIES:
                return Contract.MovieEntry.DIR_TYPE;
            case GENER:
                return Contract.GenreEntry.DIR_TYPE;
            case MOVIE_GENER:
                return Contract.MovieGenreEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {

                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);

        }

        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }
}
