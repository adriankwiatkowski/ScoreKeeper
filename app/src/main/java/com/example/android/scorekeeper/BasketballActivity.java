package com.example.android.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.scorekeeper.utilities.BasketballCursorAdapter;
import com.example.android.scorekeeper.utilities.BasketballLoader;
import com.example.android.scorekeeper.utilities.DialogUtils;

import static com.example.android.scorekeeper.data.DbContract.*;

public class BasketballActivity extends AppCompatActivity {

    public static String[] scoreTypes;

    private static final int CURSOR_LOADER = 0;
    private BasketballCursorAdapter mCursorAdapter;
    private ListView mListView;

    private BasketballLoader basketballLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        scoreTypes = getResources().getStringArray(R.array.basketball_array);

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

        basketballLoader = new BasketballLoader(mCursorAdapter, this);
        getSupportLoaderManager().initLoader(CURSOR_LOADER, null, basketballLoader);
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
                        SportsEntry.BASKETBALL_CONTENT_URI);
                return true;
            case R.id.action_delete:
                DialogUtils.showDeleteConfirmationDialog(this,
                        SportsEntry.BASKETBALL_CONTENT_URI, 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
