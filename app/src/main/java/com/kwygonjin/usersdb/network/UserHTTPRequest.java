package com.kwygonjin.usersdb.network;

import android.content.Context;
import android.net.Uri;

import com.kwygonjin.usersdb.ProfileActivity;
import com.kwygonjin.usersdb.items.UserItem;

/**
 * Created by KwygonJin on 16.01.2016.
 */
public class UserHTTPRequest {
    private static final String HTTP_SCHEME = "https";
    private static final String URL_AUTHORITY = "randomuser.me";
    private static final String ADDITIONAL_PATH_API = "api";

    public static void doRequest(Context context, UserItem userItem, ProfileActivity profileActivity) {
        UserFetcherAsync task = new UserFetcherAsync(context, userItem, profileActivity);
        task.execute(new Uri.Builder()
                .scheme(HTTP_SCHEME)
                .authority(URL_AUTHORITY)
                .appendPath(ADDITIONAL_PATH_API)
                .build().toString());
    }
}
