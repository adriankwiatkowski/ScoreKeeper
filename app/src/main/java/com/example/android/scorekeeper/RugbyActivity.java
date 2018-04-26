package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.data.DbContract.SportsEntry;
import com.example.android.scorekeeper.utilities.DialogUtils;
import com.example.android.scorekeeper.utilities.RugbyCursorAdapter;
import com.example.android.scorekeeper.utilities.RugbyLoader;

public class RugbyActivity extends AppCompatActivity {

    public static String[] scoreTypes;

    private static final int CURSOR_LOADER = 2;
    private RugbyCursorAdapter mCursorAdapter;
    private ListView mListView;

    private RugbyLoader rugbyLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rugby);

        scoreTypes = getResources().getStringArray(R.array.rugby_array);

        mListView = (ListView) findViewById(R.id.rugby_list);
        mCursorAdapter = new RugbyCursorAdapter(this, null);
        mListView.setAdapter(mCursorAdapter);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.resetScore(
                        scoreTypes,
                        SportsEntry.RUGBY_CONTENT_URI,
                        RugbyActivity.this);
            }
        });

        rugbyLoader = new RugbyLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, rugbyLoader);
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
                        SportsEntry.RUGBY_CONTENT_URI);
                return true;
            case R.id.action_delete:
                DialogUtils.showDeleteConfirmationDialog(this,
                        SportsEntry.RUGBY_CONTENT_URI, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
