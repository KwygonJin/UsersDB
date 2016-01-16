package com.kwygonjin.usersdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.kwygonjin.usersdb.db.UserDBManager;
import com.kwygonjin.usersdb.items.UserItem;
import com.kwygonjin.usersdb.network.UserHTTPRequest;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private UserItem userItem;

    public void update() {
        ImageView iv = (ImageView) findViewById(R.id.profile_user_image);
        EditText tvUserFullName = (EditText) findViewById(R.id.profile_user_full_name);
        EditText tvUserAddres = (EditText) findViewById(R.id.profile_address);
        EditText tvUserEmail = (EditText) findViewById(R.id.profile_email);
        EditText tvUserUsername = (EditText) findViewById(R.id.profile_username);
        EditText tvUserNationality  = (EditText) findViewById(R.id.profile_nationality);

        if (!userItem.userImgURL.isEmpty())
            Picasso.with(this).load(userItem.userImgURL).into(iv);
        if (!userItem.userFullName.isEmpty())
            tvUserFullName.setText(userItem.userFullName);
        if (!userItem.userAddress.isEmpty())
            tvUserAddres.setText(userItem.userAddress);
        if (!userItem.userEmail.isEmpty())
            tvUserEmail.setText(userItem.userEmail);
        if (!userItem.userUserName.isEmpty())
            tvUserUsername.setText(userItem.userUserName);
        if (!userItem.userNationality.isEmpty())
            tvUserNationality.setText(userItem.userNationality);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userItem = new UserItem();
        UserHTTPRequest.doRequest(getApplicationContext(), userItem, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addUserToDB(View view) {

        EditText tvUserFullName = (EditText) findViewById(R.id.profile_user_full_name);
        EditText tvUserAddres = (EditText) findViewById(R.id.profile_address);
        EditText tvUserEmail = (EditText) findViewById(R.id.profile_email);
        EditText tvUserUsername = (EditText) findViewById(R.id.profile_username);
        EditText tvUserNationality  = (EditText) findViewById(R.id.profile_nationality);

        userItem.userFullName = tvUserFullName.getText().toString();
        userItem.userAddress = tvUserAddres.getText().toString();
        userItem.userEmail = tvUserEmail.getText().toString();
        userItem.userUserName = tvUserUsername.getText().toString();
        userItem.userNationality = tvUserNationality.getText().toString();

        UserDBManager dbManager = new UserDBManager(getApplicationContext());
        dbManager.saveItem(userItem);

    }
}
