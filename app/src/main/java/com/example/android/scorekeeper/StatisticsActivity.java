package com.example.android.scorekeeper;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;
import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.StatisticsCursorAdapter;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.BASKETBALL_CONTENT_URI;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.COLUMN_SPORT_SCORE_A;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.COLUMN_SPORT_SCORE_B;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.COLUMN_SPORT_TEXT_A;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.COLUMN_SPORT_TEXT_B;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.FOOTBALL_CONTENT_URI;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.RUGBY_CONTENT_URI;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry._ID;

public class StatisticsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] PROJECTION = {
            _ID,
            COLUMN_SPORT_TEXT_A,
            COLUMN_SPORT_SCORE_A,
            COLUMN_SPORT_SCORE_B,
            COLUMN_SPORT_TEXT_B
    };
    private String SELECTION;

    private static final int BASKETBALL_CURSOR_LOADER = 0;
    private static final int FOOTBALL_CURSOR_LOADER = 1;
    private static final int RUGBY_CURSOR_LOADER = 2;

    private ListView mBasketballListView;
    private ListView mRugbyListView;
    private ListView mFootballListView;

    private StatisticsCursorAdapter mBasketballAdapter;
    private StatisticsCursorAdapter mRugbyAdapter;
    private StatisticsCursorAdapter mFootballAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        SELECTION = COLUMN_SPORT_TEXT_A + " = '" + getString(R.string.score) + "'";

        mBasketballAdapter = new StatisticsCursorAdapter(this, null);
        mBasketballListView = findViewById(R.id.basketball_list);
        mBasketballListView.setAdapter(mBasketballAdapter);
        mBasketballListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUtils.showDeleteConfirmationDialog(view.getContext(), SportsEntry.BASKETBALL_CONTENT_URI, id);
            }
        });

        mFootballAdapter = new StatisticsCursorAdapter(this, null);
        mFootballListView = findViewById(R.id.football_list);
        mFootballListView.setAdapter(mFootballAdapter);
        mFootballListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUtils.showDeleteConfirmationDialog(view.getContext(), SportsEntry.FOOTBALL_CONTENT_URI, id);
            }
        });

        mRugbyAdapter = new StatisticsCursorAdapter(this, null);
        mRugbyListView = findViewById(R.id.rugby_list);
        mRugbyListView.setAdapter(mRugbyAdapter);
        mRugbyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUtils.showDeleteConfirmationDialog(view.getContext(), SportsEntry.RUGBY_CONTENT_URI, id);
            }
        });

        getSupportLoaderManager().initLoader(BASKETBALL_CURSOR_LOADER, null, this);
        getSupportLoaderManager().initLoader(FOOTBALL_CURSOR_LOADER, null, this);
        getSupportLoaderManager().initLoader(RUGBY_CURSOR_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case BASKETBALL_CURSOR_LOADER:
                return new CursorLoader(
                        this,
                        SportsEntry.BASKETBALL_CONTENT_URI,
                        PROJECTION,
                        SELECTION,
                        null,
                        null
                );
            case FOOTBALL_CURSOR_LOADER:
                return new CursorLoader(
                        this,
                        SportsEntry.FOOTBALL_CONTENT_URI,
                        PROJECTION,
                        SELECTION,
                        null,
                        null);
            case RUGBY_CURSOR_LOADER:
                return new CursorLoader(
                        this,
                        SportsEntry.RUGBY_CONTENT_URI,
                        PROJECTION,
                        SELECTION,
                        null,
                        null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case BASKETBALL_CURSOR_LOADER:
                mBasketballAdapter.swapCursor(data);
                break;
            case FOOTBALL_CURSOR_LOADER:
                mFootballAdapter.swapCursor(data);
                break;
            case RUGBY_CURSOR_LOADER:
                mRugbyAdapter.swapCursor(data);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()) {
            case BASKETBALL_CURSOR_LOADER:
                mBasketballAdapter.swapCursor(null);
                break;
            case FOOTBALL_CURSOR_LOADER:
                mFootballAdapter.swapCursor(null);
                break;
            case RUGBY_CURSOR_LOADER:
                mRugbyAdapter.swapCursor(null);
                break;
        }
    }
}