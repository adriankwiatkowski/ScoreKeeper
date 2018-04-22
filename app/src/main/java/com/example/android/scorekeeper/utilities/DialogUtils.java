package com.example.android.scorekeeper.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.scorekeeper.R;
import com.example.android.scorekeeper.data.DbContract;

import static com.example.android.scorekeeper.data.DbContract.SportsEntry.*;

public final class DialogUtils {

    public static void showInsertDialog(final String[] scoreTypes, final Uri tableUri,
                                        final Context context, final SharedPreferences sharedPreferences,
                                        final String editorKey, final LinearLayout linearLayoutToShow) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String dialogTableMessage;
        if (tableUri == DbContract.SportsEntry.BASKETBALL_CONTENT_URI) {
            dialogTableMessage = context.getString(R.string.basketball);
        } else if (tableUri == DbContract.SportsEntry.RUGBY_CONTENT_URI) {
            dialogTableMessage = context.getString(R.string.rugby);
        } else {
            dialogTableMessage = context.getString(R.string.football);
        }
        builder.setMessage(context.getString(R.string.create) + " " + dialogTableMessage + " " + context.getString(R.string.table));
        builder.setPositiveButton(context.getString(R.string.action_create), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertTable(scoreTypes, tableUri, context, sharedPreferences, editorKey, linearLayoutToShow);
            }
        });
        builder.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                ((Activity)context).finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private static void insertTable(String[] scoreTypes, Uri tableUri, Context context,
                                    SharedPreferences preferences, String editorKey,
                                    LinearLayout linearLayoutToShow) {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SPORT_TEXT_A, scoreTypes[i]);
            values.put(COLUMN_SPORT_SCORE_A, 0);
            values.put(COLUMN_SPORT_SCORE_B, 0);
            values.put(COLUMN_SPORT_TEXT_B, scoreTypes[i]);
            context.getContentResolver().insert(tableUri, values);
        }

        if (linearLayoutToShow != null) {
            linearLayoutToShow.setVisibility(View.VISIBLE);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(editorKey, true);
        editor.apply();
        ((Activity)context).invalidateOptionsMenu();
    }

    public static void resetScore(String[] scoreTypes, Uri tableUri, Context context) {
        for (int i = 0; i < scoreTypes.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_SPORT_SCORE_A, 0);
            values.put(COLUMN_SPORT_SCORE_B, 0);
            context.getContentResolver().update(tableUri, values, null, null);
        }
    }

    public static void showDeleteConfirmationDialog(final Uri tableUri, final Context context,
                                                    final SharedPreferences preferences,
                                                    final String editorKey,
                                                    final LinearLayout linearLayoutToHide,
                                                    final boolean finishActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(context.getString(R.string.delete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteTable(tableUri, context, preferences, editorKey, linearLayoutToHide);
                if (finishActivity) {
                    ((Activity)context).finish();
                }
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

    private static void deleteTable(Uri tableUri, Context context, SharedPreferences preferences,
                             String editorKey, LinearLayout linearLayoutToHide) {
        int rowsDeleted = context.getContentResolver().delete(
                tableUri,
                null,
                null);

        if (rowsDeleted == 0) {
            Toast.makeText(context, R.string.delete_table_failed, Toast.LENGTH_SHORT).show();
        } else {
            Log.i("Tag", "Number rows deleted: " + rowsDeleted);
            if (linearLayoutToHide != null) {
                linearLayoutToHide.setVisibility(View.GONE);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(editorKey, false);
            editor.apply();;
            Toast.makeText(context, R.string.delete_table_successful, Toast.LENGTH_SHORT).show();
            ((Activity)context).invalidateOptionsMenu();
        }
    }
}
