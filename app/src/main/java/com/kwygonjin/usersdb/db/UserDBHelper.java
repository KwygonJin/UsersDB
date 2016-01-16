package com.kwygonjin.usersdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kwygonjin.usersdb.items.UserItem;

/**
 * Created by KwygonJin on 16.01.2016.
 */
public class UserDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_USERS = "users";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_NAME_USERS +" ( " +
            UserItem.USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            UserItem.USER_FULL_NAME + " TEXT NOT NULL, "+
            UserItem.USER_IMG_URL + " TEXT, "+
            UserItem.USER_ADDRESS + " TEXT, "+
            UserItem.USER_EMAIL + " TEXT, "+
            UserItem.USER_USERNAME + " TEXT, " +
            UserItem.USER_NATIONALITY + " TEXT, " +
            UserItem.USER_IMG_URL_MEDIUM + " TEXT) ";

    private Context context;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + TABLE_NAME_USERS);
        onCreate(db);
    }
}
