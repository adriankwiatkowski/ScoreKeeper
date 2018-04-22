package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbHelper;
import com.example.android.scorekeeper.utilities.BasketballLoader;
import com.example.android.scorekeeper.utilities.DialogUtils;

import static com.example.android.scorekeeper.data.DbContract.*;

public class BasketballActivity extends AppCompatActivity {

    public static String[] scoreTypes = new String[] {"score", "free throw", "two points", "three points", "foul"};

    private static final int CURSOR_LOADER = 0;
    private BasketballCursorAdapter mCursorAdapter;
    private ListView mListView;

    private SharedPreferences mSharedPreferences;
    public static String SHARED_PREFERENCES_KEY = "basketballKey";
    public static String IS_TABLE_EXISTS_KEY = "basketballBoolean";
    private boolean isTableNotEmpty;
    private BasketballLoader basketballLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        mListView = (ListView) findViewById(R.id.basketball_list);
        mCursorAdapter = new BasketballCursorAdapter(this, null);
        mListView.setAdapter(mCursorAdapter);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.resetScore(scoreTypes,
                        SportsEntry.BASKETBALL_CONTENT_URI,
                        BasketballActivity.this);
            }
        });

        insertTablesIfNotExists();

        basketballLoader = new BasketballLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, basketballLoader);
    }

    private void insertTablesIfNotExists() {
        mSharedPreferences = getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        isTableNotEmpty = mSharedPreferences.getBoolean(IS_TABLE_EXISTS_KEY, false);
        if (!isTableNotEmpty) {
            DialogUtils.showInsertDialog(scoreTypes,
                    SportsEntry.BASKETBALL_CONTENT_URI,
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
            DialogUtils.showDeleteConfirmationDialog(SportsEntry.BASKETBALL_CONTENT_URI,
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
