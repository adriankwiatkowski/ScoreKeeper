package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;
import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.RugbyLoader;

public class RugbyActivity extends AppCompatActivity {

    public static String[] scoreTypes = new String[] {"score", "conversion", "penalty", "drop goal", "try"};

    private static final int CURSOR_LOADER = 2;
    private RugbyCursorAdapter mCursorAdapter;
    private ListView mListView;

    private SharedPreferences mSharedPreferences;
    public static String SHARED_PREFERENCES_KEY = "rugbyKey";
    public static String IS_TABLE_EXISTS_KEY = "rugbyBoolean";
    private boolean isTableNotEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rugby);

        mListView = (ListView) findViewById(R.id.rugby_list);
        mCursorAdapter = new RugbyCursorAdapter(this, null);
        mListView.setAdapter(mCursorAdapter);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.resetScore(scoreTypes,
                        SportsEntry.RUGBY_CONTENT_URI,
                        RugbyActivity.this);
            }
        });

        insertTablesIfNotExists();

        RugbyLoader rugbyLoader = new RugbyLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, rugbyLoader);
    }

    private void insertTablesIfNotExists() {
        mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        isTableNotEmpty = mSharedPreferences.getBoolean(IS_TABLE_EXISTS_KEY, false);

        if (!isTableNotEmpty) {
            DialogUtils.showInsertDialog(scoreTypes,
                    SportsEntry.RUGBY_CONTENT_URI,
                    this,
                    mSharedPreferences,
                    IS_TABLE_EXISTS_KEY,
                    null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            DialogUtils.showDeleteConfirmationDialog(SportsEntry.RUGBY_CONTENT_URI,
                    this,
                    mSharedPreferences,
                    IS_TABLE_EXISTS_KEY,
                    null,
                    true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
