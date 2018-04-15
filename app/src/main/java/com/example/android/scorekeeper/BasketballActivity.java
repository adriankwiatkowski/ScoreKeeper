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

import static com.example.android.scorekeeper.data.DbContract.*;

public class BasketballActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private String[] scoreTypes = new String[] {"score", "free throw", "two points", "three points", "foul"};

    private static final int CURSOR_LOADER = 0;
    private BasketballCursorAdapter mCursorAdapter;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    String PREFERENCES_KEY = "boolean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        ListView listView = (ListView) findViewById(R.id.basketball_list);
        mCursorAdapter = new BasketballCursorAdapter(this, null);
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
            getContentResolver().insert(SportsEntry.BASKETBALL_CONTENT_URI, values);
        }
    }

    private void resetScore() {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(SportsEntry.COLUMN_SPORT_SCORE_A, 0);
            values.put(SportsEntry.COLUMN_SPORT_SCORE_B, 0);
            getContentResolver().update(SportsEntry.BASKETBALL_CONTENT_URI, values, null, null);
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
                SportsEntry.BASKETBALL_CONTENT_URI,
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
