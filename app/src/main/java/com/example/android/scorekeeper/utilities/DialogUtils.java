package com.example.android.scorekeeper.utilities;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.widget.Toast;

import com.example.android.scorekeeper.R;
import com.example.android.scorekeeper.data.DbContract;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.*;

public final class DialogUtils {

    public static void showInsertDialog(final String[] scoreTypes, final Context context,
                                        final Uri tableUri) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.action_create));
        builder.setPositiveButton(context.getString(R.string.action_create), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertTable(scoreTypes, tableUri, context);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private static void insertTable(String[] scoreTypes, Uri tableUri, Context context) {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SPORT_TEXT_A, scoreTypes[i]);
            values.put(COLUMN_SPORT_SCORE_A, 0);
            values.put(COLUMN_SPORT_SCORE_B, 0);
            values.put(COLUMN_SPORT_TEXT_B, scoreTypes[i]);
            context.getContentResolver().insert(tableUri, values);
        }
    }

    public static void resetScore(String[] scoreTypes, Uri tableUri, Context context) {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SPORT_SCORE_A, 0);
            values.put(COLUMN_SPORT_SCORE_B, 0);
            context.getContentResolver().update(tableUri, values, null, null);
        }
    }

    public static void showDeleteConfirmationDialog(final Context context, final Uri tableUri, final long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTable(context, tableUri, id);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private static void deleteTable(Context context, Uri tableUri, long id) {
        int rowsDeleted = 0;
        if (id == 0) {
            rowsDeleted = context.getContentResolver().delete(
                    tableUri,
                    null,
                    null);
        } else {
            int iterateCount;
            if (tableUri == DbContract.SportsEntry.BASKETBALL_CONTENT_URI) {
                iterateCount = 5;
            } else if (tableUri == DbContract.SportsEntry.FOOTBALL_CONTENT_URI) {
                iterateCount = 8;
            } else {
                iterateCount = 5;
            }
            for (int i = (int) id; i < id + iterateCount; i++) {
                Uri currentUri = ContentUris.withAppendedId(tableUri, i);
                context.getContentResolver().delete(
                        currentUri,
                        null,
                        null);
                rowsDeleted++;
            }
        }

        if (rowsDeleted == 0) {
            Toast.makeText(context, R.string.delete_table_failed, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.delete_table_successful, Toast.LENGTH_SHORT).show();
        }
    }
}
