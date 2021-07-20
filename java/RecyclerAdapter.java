package com.example.hobbyroute_ver_1_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Hobby> hobbylist = new ArrayList<>();
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    private String id;

    public RecyclerAdapter (Context context,List<Hobby> hobbylist, String id){
        this.mContext = context;
        this.hobbylist = hobbylist;
        this.id = id;
    }
    public String getid(){
        return id;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name, keyword;
        private ImageView imageView;
        private RatingBar mRate;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            name = view.findViewById(R.id.hobby_name);
            imageView = view.findViewById(R.id.hobby_image);
            keyword = view.findViewById(R.id.hobby_keyword);
            mContainer = view.findViewById(R.id.hobby_container);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.hobby_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Hobby hobby = hobbylist.get(position);
        final String id = this.id;

        holder.name.setText(hobby.getName());
        Glide.with(mContext).load(hobby.getImage()).into(holder.imageView);

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DetailedActivity.class);

                intent.putExtra("id",id);
                intent.putExtra("name",hobby.getName());
                intent.putExtra("image",hobby.getImage());
                intent.putExtra("keyword",hobby.getKeyword());
                intent.putExtra("location",hobby.getLocation());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return hobbylist.size();
    }


}
