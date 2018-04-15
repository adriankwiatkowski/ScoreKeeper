package com.example.android.scorekeeper.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {

    private DbContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.scorekeeper";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BASKETBALL = "basketball";
    public static final String PATH_FOOTBALL = "football";
    public static final String PATH_RUGBY = "rugby";

    public static final class SportsEntry implements BaseColumns {

        public static final Uri BASKETBALL_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BASKETBALL);
        public static final Uri FOOTBALL_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FOOTBALL);
        public static final Uri RUGBY_CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_RUGBY);

        //MIME type for list.
        public static final String RUGBY_CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RUGBY;
        //MIME type for a single item.
        public static final String RUGBY_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RUGBY;
        public static final String BASKETBALL_CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BASKETBALL;
        public static final String BASKETBALL_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BASKETBALL;
        public static final String FOOTBALL_CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOOTBALL;
        public static final String FOOTBALL_CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FOOTBALL;

        public static final String BASKETBALL_TABLE_NAME = "basketball";
        public static final String FOOTBALL_TABLE_NAME = "football";
        public static final String RUGBY_TABLE_NAME = "rugby";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SPORT_TEXT_A = "textA";
        public static final String COLUMN_SPORT_SCORE_A = "scoreA";
        public static final String COLUMN_SPORT_TEXT_B = "textB";
        public static final String COLUMN_SPORT_SCORE_B = "scoreB";
    }
}
