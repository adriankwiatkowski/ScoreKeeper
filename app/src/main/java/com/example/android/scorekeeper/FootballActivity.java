package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.FootballCursorAdapter;
import com.example.android.scorekeeper.utilities.FootballLoader;

import static com.example.android.scorekeeper.data.DbContract.*;

public class FootballActivity extends AppCompatActivity {

    public static String[] scoreTypes;

    private static final int CURSOR_LOADER = 1;
    private FootballCursorAdapter mCursorAdapter;
    private ListView mListView;

    private FootballLoader footballLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football);

        scoreTypes = getResources().getStringArray(R.array.football_array);

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

        footballLoader = new FootballLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, footballLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sport, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                DialogUtils.showInsertDialog(scoreTypes,
                        this,
                        SportsEntry.FOOTBALL_CONTENT_URI);
                return true;
            case R.id.action_delete:
                DialogUtils.showDeleteConfirmationDialog(this,
                        SportsEntry.FOOTBALL_CONTENT_URI, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
