package com.example.android.scorekeeper.utilities;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scorekeeper.R;
import com.example.android.scorekeeper.data.DbContract;

public class StatisticsCursorAdapter extends CursorAdapter {

    public StatisticsCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_statistics, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView headerTv = (TextView) view.findViewById(R.id.tv_header);
        TextView textTvA = (TextView) view.findViewById(R.id.tv_name_team_a);
        TextView textTvB = (TextView) view.findViewById(R.id.tv_name_team_b);
        TextView countTvA = (TextView) view.findViewById(R.id.tv_score_team_a);
        TextView countTvB = (TextView) view.findViewById(R.id.tv_score_team_b);

        int textAColumnIndex = cursor.getColumnIndex(DbContract.SportsEntry.COLUMN_SPORT_TEXT_A);
        int countAColumnIndex = cursor.getColumnIndex(DbContract.SportsEntry.COLUMN_SPORT_SCORE_A);
        int countBColumnIndex = cursor.getColumnIndex(DbContract.SportsEntry.COLUMN_SPORT_SCORE_B);
        int textBColumnIndex = cursor.getColumnIndex(DbContract.SportsEntry.COLUMN_SPORT_TEXT_B);

        final String textA = cursor.getString(textAColumnIndex);
        String countA = Integer.toString(cursor.getInt(countAColumnIndex));
        String countB = Integer.toString(cursor.getInt(countBColumnIndex));
        String textB = cursor.getString(textBColumnIndex);

        textTvA.setText(textA);
        countTvA.setText(countA);
        countTvB.setText(countB);
        textTvB.setText(textB);

        int gameCounter = cursor.getPosition() + 1;
        String gameString = context.getResources().getString(R.string.game);
        headerTv.setText(String.format("%s %s", gameString, String.valueOf(gameCounter)));
    }
}