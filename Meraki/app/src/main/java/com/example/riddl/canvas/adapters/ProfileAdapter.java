package com.example.riddl.canvas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.riddl.canvas.R;
import com.example.riddl.canvas.models.Post;
import com.example.riddl.canvas.models.Thumbnail;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {

    private final List<Thumbnail> thumbnailList;
    private Context context;
    private View view;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView postImg;

        public MyViewHolder(View view) {
            super(view);
            postImg = view.findViewById(R.id.art_thumbnail);
        }

        public void bind(Post post){
            Glide.with(context).load(post.getPostImg()).into(postImg);
        }
    }

    public ProfileAdapter(List<Thumbnail> thumbnailList) {

        this.thumbnailList = thumbnailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_recyclerview, parent, false);
        context = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Thumbnail thumbnail = thumbnailList.get(position);
        Glide.with(context).load(thumbnail.getUrl()).into(holder.postImg);
    }

    @Override
    public int getItemCount() {
        return thumbnailList.size();
    }
}