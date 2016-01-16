package com.kwygonjin.usersdb.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwygonjin.usersdb.R;
import com.kwygonjin.usersdb.items.UserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KwygonJin on 16.01.2016.
 */
public class UsersRVAdapter extends RecyclerView.Adapter<UsersRVAdapter.UserViewHolder> {
    private Context context;
    private List<UserItem> users;

    public UsersRVAdapter(Context context, List<UserItem> users) {
        this.context = context;
        this.users = users;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView userImg;
        public TextView userName;

        UserViewHolder(View itemView) {
            super(itemView);
            userImg = (ImageView)itemView.findViewById(R.id.user_image);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            //userImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public UsersRVAdapter.UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, null);
        UserViewHolder uvh = new UserViewHolder(v);
        return uvh;
    }

    @Override
    public void onBindViewHolder(UsersRVAdapter.UserViewHolder userViewHolder, int position) {
        Picasso.with(context).load(users.get(position).userImgURL).placeholder(R.drawable.user_image_placeholder).into(userViewHolder.userImg);
        userViewHolder.userName.setText(users.get(position).userFullName);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
