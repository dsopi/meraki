package com.example.riddl.canvas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.riddl.canvas.R;
import com.example.riddl.canvas.models.Post;


public class PostViewHolder extends RecyclerView.ViewHolder {

    public ImageView userImg, postImg;
    public TextView username, likesNumber, description, timestamp;

    public PostViewHolder(View itemView) {
        super(itemView);
        postImg = itemView.findViewById(R.id.art_thumbnail);

        username = itemView.findViewById(R.id.username);
        userImg = itemView.findViewById(R.id.user_img);
        postImg = itemView.findViewById(R.id.post_img);
        likesNumber = itemView.findViewById(R.id.likesNumber);
        description = itemView.findViewById(R.id.post_description);
        timestamp = itemView.findViewById(R.id.timestamp);
    }

    public void bind(Post post, Context context) {

        username.setText(post.getUsername());
        likesNumber.setText(String.valueOf(post.getLikesNumber()));
        description.setText(post.getDescription());
        timestamp.setText(post.getTimeStamp());

        Glide.with(context).load(post.getUserImg()).into(userImg);
        Glide.with(context).load(post.getPostImg()).into(postImg);
    }
}