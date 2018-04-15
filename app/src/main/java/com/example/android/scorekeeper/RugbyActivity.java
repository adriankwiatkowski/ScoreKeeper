package com.example.android.scorekeeper;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.RUGBY_CONTENT_URI;

public class RugbyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private String[] scoreTypes = new String[] {"score", "conversion", "penalty", "drop goal", "try"};

    private static final int CURSOR_LOADER = 2;
    private RugbyCursorAdapter mCursorAdapter;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String PREFERENCES_KEY = "boolean1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rugby);

        ListView listView = (ListView) findViewById(R.id.rugby_list);
        mCursorAdapter = new RugbyCursorAdapter(this, null);
        listView.setAdapter(mCursorAdapter);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScore();
            }
        });

        mSharedPreferences = getPreferences(MODE_PRIVATE);
        boolean isTableEmpty = mSharedPreferences.getBoolean(PREFERENCES_KEY, false);

        if (!isTableEmpty) {
            insertItem(scoreTypes);
        }

        getLoaderManager().initLoader(CURSOR_LOADER, null, this);
    }

    private void insertItem(String[] scoreTypes) {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(SportsEntry.COLUMN_SPORT_TEXT_A, scoreTypes[i]);
            values.put(SportsEntry.COLUMN_SPORT_SCORE_A, 0);
            values.put(SportsEntry.COLUMN_SPORT_SCORE_B, 0);
            values.put(SportsEntry.COLUMN_SPORT_TEXT_B, scoreTypes[i]);
            getContentResolver().insert(RUGBY_CONTENT_URI, values);
        }
    }

    private void resetScore() {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(SportsEntry.COLUMN_SPORT_SCORE_A, 0);
            values.put(SportsEntry.COLUMN_SPORT_SCORE_B, 0);
            getContentResolver().update(RUGBY_CONTENT_URI, values, null, null);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                SportsEntry._ID,
                SportsEntry.COLUMN_SPORT_TEXT_A,
                SportsEntry.COLUMN_SPORT_SCORE_A,
                SportsEntry.COLUMN_SPORT_SCORE_B,
                SportsEntry.COLUMN_SPORT_TEXT_B};

        return new CursorLoader(this,
                RUGBY_CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(PREFERENCES_KEY, true);
        mEditor.apply();
    }
}
