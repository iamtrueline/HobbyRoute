package com.example.hobbyroute_ver_1_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Post> post;

    public BoardAdapter(Context context, ArrayList<Post> data) {
        mContext = context;
        post= data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return post.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Post getItem(int position) {
        return post.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.post, null);

        TextView name = (TextView)view.findViewById(R.id.name);
        TextView title = (TextView)view.findViewById(R.id.title);
        TextView contents = (TextView)view.findViewById(R.id.contents);

        name.setText(post.get(position).getName());
        title.setText(post.get(position).getTitle());
        contents.setText(post.get(position).getContents());
        return view;
    }
}
