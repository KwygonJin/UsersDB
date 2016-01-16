package com.kwygonjin.usersdb.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.kwygonjin.usersdb.MainActivity;
import com.kwygonjin.usersdb.ProfileActivity;
import com.kwygonjin.usersdb.items.UserItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by KwygonJin on 01.12.2015.
 */
public class UserFetcherAsync extends AsyncTask<String, Integer, String> {

    private Context context;
    private UserItem userItem;
    private ProfileActivity profileActivity;

    public UserFetcherAsync(Context context, UserItem userItem, ProfileActivity profileActivity) {
        this.context = context;
        this.userItem = userItem;
        this.profileActivity = profileActivity;
    }

    @Override
    protected String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null)
                return null;
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0)
                return null;
            json = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(MainActivity.LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return json;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);

        if (result == null) {
            return;
        }

        if (!result.isEmpty()) {
            UserHTTPParse.parseJSON(result, context, userItem, profileActivity);
            return;
        }

    }

}