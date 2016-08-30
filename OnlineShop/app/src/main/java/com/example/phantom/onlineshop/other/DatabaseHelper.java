package com.example.phantom.onlineshop.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DB_NAME = "POSTS";
    private static final int DB_VERSION = 2;
    public static final String NAME_COLUMN = "name";
    public static final String WEIGHT_COLUMN = "weight";
    public static final String PRICE_COLUMN = "price";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String CATEGORY_ID_COLUMN = "category_id";
    public static final String IMAGE_URL_COLUMN = "image_url";
    private static final String DB_SQRIPT = "CREATE TABLE " + DB_NAME + "("
            + BaseColumns._ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_COLUMN + " TEXT, "
            + WEIGHT_COLUMN + " TEXT, "
            + PRICE_COLUMN+ " TEXT, "
            + DESCRIPTION_COLUMN + " TEXT, "
            + IMAGE_URL_COLUMN + " TEXT, "
            + CATEGORY_ID_COLUMN + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDB(db, 0, DB_VERSION);
    }


    private void updateMyDB(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(DB_SQRIPT);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDB(db, oldVersion, newVersion);
    }
}
