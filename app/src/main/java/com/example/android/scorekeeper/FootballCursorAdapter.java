package com.example.android.scorekeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.scorekeeper.data.DbContract;
import com.example.android.scorekeeper.data.DbContract.SportsEntry;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.FOOTBALL_CONTENT_URI;
import static com.example.android.scorekeeper.data.DbContract.SportsEntry.RUGBY_CONTENT_URI;

public class FootballCursorAdapter extends CursorAdapter {

    public FootballCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView textTvA = (TextView) view.findViewById(R.id.tv_name_team_a);
        TextView textTvB = (TextView) view.findViewById(R.id.tv_name_team_b);
        final TextView countTvA = (TextView) view.findViewById(R.id.tv_score_team_a);
        final TextView countTvB = (TextView) view.findViewById(R.id.tv_score_team_b);

        int textAColumnIndex = cursor.getColumnIndex(SportsEntry.COLUMN_SPORT_TEXT_A);
        int countAColumnIndex = cursor.getColumnIndex(SportsEntry.COLUMN_SPORT_SCORE_A);
        int countBColumnIndex = cursor.getColumnIndex(SportsEntry.COLUMN_SPORT_SCORE_B);
        int textBColumnIndex = cursor.getColumnIndex(SportsEntry.COLUMN_SPORT_TEXT_B);

        final String textA = cursor.getString(textAColumnIndex);
        String countA = Integer.toString(cursor.getInt(countAColumnIndex));
        String countB = Integer.toString(cursor.getInt(countBColumnIndex));
        String textB = cursor.getString(textBColumnIndex);

        textTvA.setText(textA);
        countTvA.setText(countA);
        countTvB.setText(countB);
        textTvB.setText(textB);

        final int position = cursor.getPosition();

        textTvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(position);
                int quantity = Integer.parseInt(countTvA.getText().toString().trim());
                updateDatabase(context, cursor, quantity, textA, SportsEntry.COLUMN_SPORT_SCORE_A);
            }
        });
        textTvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(position);
                int quantity = Integer.parseInt(countTvB.getText().toString().trim());
                updateDatabase(context, cursor, quantity, textA, SportsEntry.COLUMN_SPORT_SCORE_B);
            }
        });
    }

    private void updateDatabase(Context context, Cursor cursor, int currentScore, String textString,
                                String teamColumn) {
        int increaseBy = 0;
        switch (textString) {
            case "goal":
                increaseBy = 1;
                break;
            case "foul":
                increaseBy = 0;
                break;
            case "corner kick":
                increaseBy = 0;
                break;
            case "offside":
                increaseBy = 0;
                break;
            case "yellow card":
                increaseBy = 0;
                break;
            case "red card":
                increaseBy = 0;
                break;
            case "goalkeeper save":
                increaseBy = 0;
                break;
            case "score":
                return;
        }
        updateScore(context, cursor, currentScore, increaseBy, teamColumn);
    }

    private void updateScore(Context context, Cursor cursor, int clickCount, int increaseBy,
                             String teamColumn) {
        clickCount ++;
        int cursorPosition = cursor.getPosition();
        ContentValues values = new ContentValues();
        values.put(teamColumn, clickCount);
        int id = cursor.getInt(cursor.getColumnIndex(SportsEntry._ID));
        context.getContentResolver().update(FOOTBALL_CONTENT_URI, values, "_id=" + id, null);

        if (increaseBy != 0) {
            //Position 0 = score
            int scoreTeamPosition = 0;
            cursor.moveToPosition(scoreTeamPosition);
            int scoreColumnIndex = cursor.getColumnIndex(teamColumn);
            int currentScore = cursor.getInt(scoreColumnIndex);
            currentScore += increaseBy;
            ContentValues scoreValues = new ContentValues();
            scoreValues.put(teamColumn, currentScore);

            int scoreTeamId = id - cursorPosition;
            context.getContentResolver().update(FOOTBALL_CONTENT_URI, scoreValues, "_id=" + scoreTeamId, null);
        }
    }
}