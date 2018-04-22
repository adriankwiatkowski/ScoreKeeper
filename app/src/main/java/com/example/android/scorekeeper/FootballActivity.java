package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.FootballLoader;

import static com.example.android.scorekeeper.data.DbContract.*;

public class FootballActivity extends AppCompatActivity {

    public static String[] scoreTypes = new String[] {"score", "goal", "foul", "corner kick", "offside",
            "yellow card", "red card", "goalkeeper save"};

    private static final int CURSOR_LOADER = 1;
    private FootballCursorAdapter mCursorAdapter;
    private ListView mListView;

    private SharedPreferences mSharedPreferences;
    public static String SHARED_PREFERENCES_KEY = "footballKey";
    public static String IS_TABLE_EXISTS_KEY = "footballBoolean";
    private boolean isTableNotEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        mListView = (ListView) findViewById(R.id.football_list);
        mCursorAdapter = new FootballCursorAdapter(this, null);
        mListView.setAdapter(mCursorAdapter);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.resetScore(scoreTypes,
                        SportsEntry.FOOTBALL_CONTENT_URI,
                        FootballActivity.this);
            }
        });

        insertTablesIfNotExists();

        FootballLoader footballLoader = new FootballLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, footballLoader);
    }

    private void insertTablesIfNotExists() {
        mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        isTableNotEmpty = mSharedPreferences.getBoolean(IS_TABLE_EXISTS_KEY, false);

        if (!isTableNotEmpty) {
            DialogUtils.showInsertDialog(scoreTypes,
                    SportsEntry.FOOTBALL_CONTENT_URI,
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
            DialogUtils.showDeleteConfirmationDialog(SportsEntry.FOOTBALL_CONTENT_URI,
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
