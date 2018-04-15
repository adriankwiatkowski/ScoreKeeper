package com.example.android.scorekeeper;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract;
import com.example.android.scorekeeper.data.DbContract.SportsEntry;

public class StatisticsActivity extends AppCompatActivity {

    private static final int BASKETBALL_CURSOR_LOADER = 0;
    private static final int FOOTBALL_CURSOR_LOADER = 1;
    private static final int RUGBY_CURSOR_LOADER = 2;

    private BasketballCursorAdapter mBasketballAdapter;
    private RugbyCursorAdapter mRugbyAdapter;
    private FootballCursorAdapter mFootballAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ListView basketballListView = findViewById(R.id.basketball_list);
        mBasketballAdapter = new BasketballCursorAdapter(this, null);
        basketballListView.setAdapter(mBasketballAdapter);

        ListView footballListView = findViewById(R.id.football_list);
        mFootballAdapter = new FootballCursorAdapter(this, null);
        footballListView.setAdapter(mFootballAdapter);

        ListView rugbyListView = findViewById(R.id.rugby_list);
        mRugbyAdapter = new RugbyCursorAdapter(this, null);
        rugbyListView.setAdapter(mRugbyAdapter);

        getLoaderManager().initLoader(BASKETBALL_CURSOR_LOADER, null, basketballLoaderListener);
        getLoaderManager().initLoader(FOOTBALL_CURSOR_LOADER, null, footballLoaderListener);
        getLoaderManager().initLoader(RUGBY_CURSOR_LOADER, null, rugbyLoaderListener);
    }

    private LoaderManager.LoaderCallbacks<Cursor> basketballLoaderListener
            = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String[] projection = {
                    SportsEntry._ID,
                    SportsEntry.COLUMN_SPORT_TEXT_A,
                    SportsEntry.COLUMN_SPORT_SCORE_A,
                    SportsEntry.COLUMN_SPORT_SCORE_B,
                    SportsEntry.COLUMN_SPORT_TEXT_B};

            return new CursorLoader(getApplicationContext(),
                    SportsEntry.BASKETBALL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mBasketballAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mBasketballAdapter.swapCursor(null);
        }
    };

    private LoaderManager.LoaderCallbacks<Cursor> footballLoaderListener
            = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String[] projection = {
                    SportsEntry._ID,
                    SportsEntry.COLUMN_SPORT_TEXT_A,
                    SportsEntry.COLUMN_SPORT_SCORE_A,
                    SportsEntry.COLUMN_SPORT_SCORE_B,
                    SportsEntry.COLUMN_SPORT_TEXT_B};

            return new CursorLoader(getApplicationContext(),
                    SportsEntry.FOOTBALL_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mFootballAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mFootballAdapter.swapCursor(null);
        }
    };

    private LoaderManager.LoaderCallbacks<Cursor> rugbyLoaderListener
            = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String[] projection = {
                    SportsEntry._ID,
                    SportsEntry.COLUMN_SPORT_TEXT_A,
                    SportsEntry.COLUMN_SPORT_SCORE_A,
                    SportsEntry.COLUMN_SPORT_SCORE_B,
                    SportsEntry.COLUMN_SPORT_TEXT_B};

            return new CursorLoader(getApplicationContext(),
                    SportsEntry.RUGBY_CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mRugbyAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mRugbyAdapter.swapCursor(null);
        }
    };
}