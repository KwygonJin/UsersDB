package com.kwygonjin.usersdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.kwygonjin.usersdb.adapters.UsersRVAdapter;
import com.kwygonjin.usersdb.db.UserDBManager;
import com.kwygonjin.usersdb.items.UserItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String LOG_TAG = "my_log";
    private List<UserItem> users = new ArrayList<UserItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        UsersRVAdapter usersRVAdapter = new UsersRVAdapter(getApplicationContext(), users);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(usersRVAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_add_user:
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    public void initData() {
        UserDBManager userDBManager = new UserDBManager(getApplicationContext());
        userDBManager.getAll(this.users);
    }
}
