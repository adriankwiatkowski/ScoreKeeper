package com.example.android.scorekeeper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.scorekeeper.data.DbContract.SportsEntry;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "score.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_BASKETBALL_TABLE = "CREATE TABLE "
            + SportsEntry.BASKETBALL_TABLE_NAME + " ("
            + SportsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SportsEntry.COLUMN_SPORT_SCORE_A + " INTEGER NOT NULL DEFAULT 0, "
            + SportsEntry.COLUMN_SPORT_TEXT_A + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_TEXT_B + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_SCORE_B + " INTEGER NOT NULL DEFAULT 0)";

    private static final String SQL_CREATE_FOOTBALL_TABLE = "CREATE TABLE "
            + SportsEntry.FOOTBALL_TABLE_NAME + " ("
            + SportsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SportsEntry.COLUMN_SPORT_SCORE_A + " INTEGER NOT NULL DEFAULT 0, "
            + SportsEntry.COLUMN_SPORT_TEXT_A + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_TEXT_B + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_SCORE_B + " INTEGER NOT NULL DEFAULT 0)";

    private static final String SQL_CREATE_RUGBY_TABLE = "CREATE TABLE "
            + SportsEntry.RUGBY_TABLE_NAME + " ("
            + SportsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SportsEntry.COLUMN_SPORT_SCORE_A + " INTEGER NOT NULL DEFAULT 0, "
            + SportsEntry.COLUMN_SPORT_TEXT_A + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_TEXT_B + " TEXT NOT NULL, "
            + SportsEntry.COLUMN_SPORT_SCORE_B + " INTEGER NOT NULL DEFAULT 0)";

    private static final String SQL_DELETE_BASKETBALL_TABLE =
            "DROP TABLE IF EXISTS " + SportsEntry.BASKETBALL_TABLE_NAME;

    private static final String SQL_DELETE_FOOTBALL_TABLE =
            "DROP TABLE IF EXISTS " + SportsEntry.FOOTBALL_TABLE_NAME;

    private static final String SQL_DELETE_RUGBY_TABLE =
            "DROP TABLE IF EXISTS " + SportsEntry.RUGBY_TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BASKETBALL_TABLE);
        db.execSQL(SQL_CREATE_FOOTBALL_TABLE);
        db.execSQL(SQL_CREATE_RUGBY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_BASKETBALL_TABLE);
        db.execSQL(SQL_DELETE_FOOTBALL_TABLE);
        db.execSQL(SQL_DELETE_RUGBY_TABLE);
        onCreate(db);
    }
}
