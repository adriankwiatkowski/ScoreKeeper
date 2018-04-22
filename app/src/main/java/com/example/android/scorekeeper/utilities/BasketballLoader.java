package com.example.android.scorekeeper.utilities;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.CursorAdapter;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.*;

public class BasketballLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] PROJECTION = {
            _ID,
            COLUMN_SPORT_TEXT_A,
            COLUMN_SPORT_SCORE_A,
            COLUMN_SPORT_SCORE_B,
            COLUMN_SPORT_TEXT_B
    };

    private Context mContext;
    private CursorAdapter mAdapter;

    public BasketballLoader(CursorAdapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(mContext,
                BASKETBALL_CONTENT_URI,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
