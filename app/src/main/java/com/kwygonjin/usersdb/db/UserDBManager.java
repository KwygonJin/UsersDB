package com.kwygonjin.usersdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kwygonjin.usersdb.items.UserItem;

import java.util.List;

/**
 * Created by KwygonJin on 16.01.2016.
 */
public class UserDBManager {
    private SQLiteDatabase db;
    private Context context;
    private UserDBHelper userDBHelper;

    public UserDBManager(Context context) {
        this.context = context;
        userDBHelper = new UserDBHelper(this.context);
        db = userDBHelper.getWritableDatabase();
    }

    public synchronized SQLiteDatabase openDatabase() {
        return db;
    }

    public synchronized void closeDatabase() {
        db.close();
    }

    public long saveItem(UserItem userItem){
        db = openDatabase();
        db.beginTransaction();
        ContentValues cv = new ContentValues();
        cv.put(UserItem.USER_USERNAME, userItem.userUserName);
        cv.put(UserItem.USER_ADDRESS, userItem.userAddress);
        cv.put(UserItem.USER_EMAIL, userItem.userEmail);
        cv.put(UserItem.USER_FULL_NAME, userItem.userFullName);
        cv.put(UserItem.USER_IMG_URL, userItem.userImgURL);
        cv.put(UserItem.USER_NATIONALITY, userItem.userNationality);

        long _id = db.insert(UserDBHelper.TABLE_NAME_USERS, null, cv);

        if (_id != -1) {
            db.setTransactionSuccessful();
            userItem.userId = _id;
        }

        db.endTransaction();
        closeDatabase();

        return _id;
    }

    public void getAll(List<UserItem> userItems) {
        db = openDatabase();

        Cursor cursor = db.query(UserDBHelper.TABLE_NAME_USERS, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            userItems.add(UserItem.createUserFromCursor(cursor));
        }

        cursor.close();
        closeDatabase();

    }
}
