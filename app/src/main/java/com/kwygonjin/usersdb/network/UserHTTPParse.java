package com.kwygonjin.usersdb.network;

import android.content.Context;

import com.kwygonjin.usersdb.ProfileActivity;
import com.kwygonjin.usersdb.items.UserItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by KwygonJin on 06.12.2015.
 */
public class UserHTTPParse {

    public static void parseJSON(String result, Context context, UserItem userItem, ProfileActivity profileActivity) {

        if (result == null || userItem == null) {
            return;
        }

        try {
            JSONObject jsonObject= new JSONObject(result);
            userItem.userNationality = jsonObject.getString("nationality");

            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectArr = jsonArray.getJSONObject(i);
                JSONObject jsonObjectUser = jsonObjectArr.optJSONObject("user");
                userItem.userEmail = jsonObjectUser.getString("email");
                userItem.userUserName = jsonObjectUser.getString("username");

                JSONObject jsonObjectLoc = jsonObjectUser.getJSONObject("location");
                StringBuilder sbLoc = new StringBuilder();
                sbLoc.append(jsonObjectLoc.getString("state"));
                sbLoc.append(", ");
                sbLoc.append(jsonObjectLoc.getString("city"));
                sbLoc.append(", ");
                sbLoc.append(jsonObjectLoc.getString("street"));
                sbLoc.append(", ");
                sbLoc.append(jsonObjectLoc.getString("zip"));
                userItem.userAddress = sbLoc.toString();

                JSONObject jsonObjectFullName = jsonObjectUser.getJSONObject("name");
                StringBuilder sbFullName= new StringBuilder();
                sbFullName.append(jsonObjectFullName.getString("title"));
                sbFullName.append(" ");
                sbFullName.append(jsonObjectFullName.getString("first"));
                sbFullName.append(" ");
                sbFullName.append(jsonObjectFullName.getString("last"));
                userItem.userFullName = sbFullName.toString();

                JSONObject jsonObjectImg = jsonObjectUser.getJSONObject("picture");
                userItem.userImgURL = jsonObjectImg.getString("thumbnail");
                userItem.userImgURLMedium = jsonObjectImg.getString("medium");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        profileActivity.update();
    }
}
