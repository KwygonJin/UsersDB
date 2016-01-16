package com.kwygonjin.usersdb.items;

import android.database.Cursor;

/**
 * Created by KwygonJin on 16.01.2016.
 */
public class UserItem {
    public static final String USER_FULL_NAME = "user_full_name";
    public static final String USER_ADDRESS = "user_address";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_USERNAME = "user_username";
    public static final String USER_IMG_URL = "user_img_url";
    public static final String USER_IMG_URL_MEDIUM = "user_img_url_med";
    public static final String USER_NATIONALITY = "user_nationality";
    public static final String USER_ID = "user_id";

    public String userFullName;
    public String userAddress;
    public String userImgURL;
    public String userImgURLMedium;
    public String userEmail;
    public String userUserName;
    public String userNationality;
    public Long userId;

    public UserItem(){

    }

    public UserItem(String userFullName, String userAddress, String userImgURL, String userEmail, String userUserName, String userNationality, String userImgURLMedium) {
        this.userFullName = userFullName;
        this.userAddress = userAddress;
        this.userImgURL = userImgURL;
        this.userEmail = userEmail;
        this.userUserName = userUserName;
        this.userNationality = userNationality;
        this.userImgURLMedium = userImgURLMedium;
    }

    public static UserItem createUserFromCursor(Cursor cursor) {
       UserItem userItem = new UserItem(cursor.getString(cursor.getColumnIndex(USER_FULL_NAME)),
               cursor.getString(cursor.getColumnIndex(USER_ADDRESS)),
               cursor.getString(cursor.getColumnIndex(USER_IMG_URL)),
               cursor.getString(cursor.getColumnIndex(USER_EMAIL)),
               cursor.getString(cursor.getColumnIndex(USER_USERNAME)),
               cursor.getString(cursor.getColumnIndex(USER_NATIONALITY)),
               cursor.getString(cursor.getColumnIndex(USER_IMG_URL_MEDIUM)));

        userItem.userId = cursor.getLong(cursor.getColumnIndex(USER_ID));

        return userItem;
    }
}
