package com.example.android.scorekeeper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;
import com.example.android.scorekeeper.utilities.BasketballLoader;
import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.FootballLoader;
import com.example.android.scorekeeper.utilities.RugbyLoader;

public class StatisticsActivity extends AppCompatActivity {

    private static final int BASKETBALL_CURSOR_LOADER = 0;
    private static final int FOOTBALL_CURSOR_LOADER = 1;
    private static final int RUGBY_CURSOR_LOADER = 2;

    private LinearLayout mBasketballLayout;
    private LinearLayout mRugbyLayout;
    private LinearLayout mFootballLayout;

    private ListView mBasketballListView;
    private ListView mRugbyListView;
    private ListView mFootballListView;

    private BasketballCursorAdapter mBasketballAdapter;
    private RugbyCursorAdapter mRugbyAdapter;
    private FootballCursorAdapter mFootballAdapter;

    private SharedPreferences mBasketballSharedPreferences;
    private SharedPreferences mRugbySharedPreferences;
    private SharedPreferences mFootballSharedPreferences;

    private boolean isBasketTableExists;
    private boolean isRugbyTableExists;
    private boolean isFootTableExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        mBasketballLayout = findViewById(R.id.basketball_layout);
        mRugbyLayout = findViewById(R.id.rugby_layout);
        mFootballLayout = findViewById(R.id.football_layout);

        mBasketballListView = findViewById(R.id.basketball_list);
        mRugbyListView = findViewById(R.id.rugby_list);
        mFootballListView = findViewById(R.id.football_list);

        mBasketballAdapter = new BasketballCursorAdapter(this, null);
        mBasketballListView.setAdapter(mBasketballAdapter);

        mRugbyAdapter = new RugbyCursorAdapter(this, null);
        mRugbyListView.setAdapter(mRugbyAdapter);

        mFootballAdapter = new FootballCursorAdapter(this, null);
        mFootballListView.setAdapter(mFootballAdapter);

        insertTablesIfNotExists();

        BasketballLoader basketballLoader = new BasketballLoader(mBasketballAdapter, this);
        getSupportLoaderManager().initLoader(BASKETBALL_CURSOR_LOADER, null, basketballLoader);
        FootballLoader footballLoader = new FootballLoader(mFootballAdapter, this);
        getSupportLoaderManager().initLoader(FOOTBALL_CURSOR_LOADER, null, footballLoader);
        RugbyLoader rugbyLoader = new RugbyLoader(mRugbyAdapter, this);
        getSupportLoaderManager().initLoader(RUGBY_CURSOR_LOADER, null, rugbyLoader);
    }

    private void insertTablesIfNotExists() {
        mBasketballSharedPreferences = getSharedPreferences(BasketballActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        mRugbySharedPreferences = getSharedPreferences(RugbyActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        mFootballSharedPreferences = getSharedPreferences(FootballActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        isBasketTableExists = mBasketballSharedPreferences.getBoolean(BasketballActivity.IS_TABLE_EXISTS_KEY, false);
        isRugbyTableExists = mRugbySharedPreferences.getBoolean(RugbyActivity.IS_TABLE_EXISTS_KEY, false);
        isFootTableExists = mFootballSharedPreferences.getBoolean(FootballActivity.IS_TABLE_EXISTS_KEY, false);
        if (!isRugbyTableExists) {
            mRugbyLayout.setVisibility(View.GONE);
            DialogUtils.showInsertDialog(RugbyActivity.scoreTypes,
                    SportsEntry.RUGBY_CONTENT_URI,
                    this,
                    mRugbySharedPreferences,
                    RugbyActivity.IS_TABLE_EXISTS_KEY,
                    mRugbyLayout);
        }
        if (!isFootTableExists) {
            mFootballLayout.setVisibility(View.GONE);
            DialogUtils.showInsertDialog(FootballActivity.scoreTypes,
                    SportsEntry.FOOTBALL_CONTENT_URI,
                    this,
                    mFootballSharedPreferences,
                    FootballActivity.IS_TABLE_EXISTS_KEY,
                    mFootballLayout);
        }
        if (!isBasketTableExists) {
            mBasketballLayout.setVisibility(View.GONE);
            DialogUtils.showInsertDialog(BasketballActivity.scoreTypes,
                    SportsEntry.BASKETBALL_CONTENT_URI,
                    this,
                    mBasketballSharedPreferences,
                    BasketballActivity.IS_TABLE_EXISTS_KEY,
                    mBasketballLayout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        isBasketTableExists = mBasketballSharedPreferences.getBoolean(BasketballActivity.IS_TABLE_EXISTS_KEY, false);
        isRugbyTableExists = mRugbySharedPreferences.getBoolean(RugbyActivity.IS_TABLE_EXISTS_KEY, false);
        isFootTableExists = mFootballSharedPreferences.getBoolean(FootballActivity.IS_TABLE_EXISTS_KEY, false);
        if (!isBasketTableExists) {
            menu.findItem(R.id.action_delete_basketball).setVisible(false);
        }
        if (!isRugbyTableExists) {
            menu.findItem(R.id.action_delete_rugby).setVisible(false);
        }
        if (!isFootTableExists) {
            menu.findItem(R.id.action_delete_football).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_basketball:
                DialogUtils.showDeleteConfirmationDialog(SportsEntry.BASKETBALL_CONTENT_URI,
                        this,
                        mBasketballSharedPreferences,
                        BasketballActivity.IS_TABLE_EXISTS_KEY,
                        mBasketballLayout,
                        false);
                return true;
            case R.id.action_delete_rugby:
                DialogUtils.showDeleteConfirmationDialog(SportsEntry.RUGBY_CONTENT_URI,
                        this,
                        mRugbySharedPreferences,
                        RugbyActivity.IS_TABLE_EXISTS_KEY,
                        mRugbyLayout,
                        false);
                return true;
            case R.id.action_delete_football:
                DialogUtils.showDeleteConfirmationDialog(SportsEntry.FOOTBALL_CONTENT_URI,
                        this,
                        mFootballSharedPreferences,
                        FootballActivity.IS_TABLE_EXISTS_KEY,
                        mFootballLayout,
                        false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}