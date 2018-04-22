package com.example.android.scorekeeper.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;

public class DbProvider extends ContentProvider {

    private static final int RUGBY = 100;
    private static final int RUGBY_ID = 101;

    private static final int BASKETBALL = 200;
    private static final int BASKETBALL_ID = 201;

    private static final int FOOTBALL = 300;
    private static final int FOOTBALL_ID = 301;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_RUGBY, RUGBY);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_RUGBY + "/#", RUGBY_ID);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_BASKETBALL, BASKETBALL);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_BASKETBALL + "/#", BASKETBALL_ID);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_FOOTBALL, FOOTBALL);
        sUriMatcher.addURI(DbContract.CONTENT_AUTHORITY, DbContract.PATH_FOOTBALL + "/#", FOOTBALL_ID);
    }

    private static final String LOG_TAG = DbProvider.class.getSimpleName();

    private DbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RUGBY:
                cursor = database.query(SportsEntry.RUGBY_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case RUGBY_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(SportsEntry.RUGBY_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case BASKETBALL:
                cursor = database.query(SportsEntry.BASKETBALL_TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case BASKETBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(SportsEntry.BASKETBALL_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case FOOTBALL:
                cursor = database.query(SportsEntry.FOOTBALL_TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case FOOTBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(SportsEntry.FOOTBALL_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RUGBY:
                return insertItem(uri, values, SportsEntry.RUGBY_TABLE_NAME);
            case BASKETBALL:
                return insertItem(uri, values, SportsEntry.BASKETBALL_TABLE_NAME);
            case FOOTBALL:
                return insertItem(uri, values, SportsEntry.FOOTBALL_TABLE_NAME);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values, String tableName) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(tableName, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RUGBY:
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.RUGBY_TABLE_NAME);
            case RUGBY_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.RUGBY_TABLE_NAME);
            case BASKETBALL:
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.BASKETBALL_TABLE_NAME);
            case BASKETBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.BASKETBALL_TABLE_NAME);
            case FOOTBALL:
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.FOOTBALL_TABLE_NAME);
            case FOOTBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs, SportsEntry.FOOTBALL_TABLE_NAME);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs,
                           String tableName) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(tableName, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match =sUriMatcher.match(uri);
        switch (match) {
            case RUGBY:
                rowsDeleted = database.delete(SportsEntry.RUGBY_TABLE_NAME, selection, selectionArgs);
                break;
            case RUGBY_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SportsEntry.RUGBY_TABLE_NAME, selection, selectionArgs);
                break;
            case BASKETBALL:
                rowsDeleted = database.delete(SportsEntry.BASKETBALL_TABLE_NAME, selection, selectionArgs);
                break;
            case BASKETBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SportsEntry.BASKETBALL_TABLE_NAME, selection, selectionArgs);
                break;
            case FOOTBALL:
                rowsDeleted = database.delete(SportsEntry.FOOTBALL_TABLE_NAME, selection, selectionArgs);
                break;
            case FOOTBALL_ID:
                selection = SportsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SportsEntry.FOOTBALL_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case RUGBY:
                return SportsEntry.RUGBY_CONTENT_LIST_TYPE;
            case RUGBY_ID:
                return SportsEntry.RUGBY_CONTENT_ITEM_TYPE;
            case BASKETBALL:
                return SportsEntry.BASKETBALL_CONTENT_LIST_TYPE;
            case BASKETBALL_ID:
                return SportsEntry.BASKETBALL_CONTENT_ITEM_TYPE;
            case FOOTBALL:
                return SportsEntry.FOOTBALL_CONTENT_LIST_TYPE;
            case FOOTBALL_ID:
                return SportsEntry.FOOTBALL_CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
