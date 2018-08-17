package com.example.riddl.canvas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.riddl.canvas.R;
import com.example.riddl.canvas.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView userImg, postImg;
        public TextView username, likesNumber, timestamp;

        public MyViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.username);
            userImg = view.findViewById(R.id.user_img);
            postImg = view.findViewById(R.id.post_img);
            likesNumber = view.findViewById(R.id.likesNumber);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }


    public PostAdapter(List<Post> postsList) {
        this.postList = postsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);

        context = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.username.setText(post.getUsername());
        holder.likesNumber.setText(String.valueOf(post.getLikesNumber()));
        holder.timestamp.setText(post.getTimeStamp());

        Glide.with(context).load(post.getUserImg()).into(holder.userImg);
        Glide.with(context).load(post.getPostImg()).into(holder.postImg);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}