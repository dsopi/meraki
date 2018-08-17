package com.example.riddl.canvas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.riddl.canvas.R;
import com.example.riddl.canvas.models.Post;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder {

    public ImageView postImg;

    public ThumbnailViewHolder(View itemView) {
        super(itemView);
        postImg = itemView.findViewById(R.id.art_thumbnail);
    }

    public void bind(Post post, Context context) {

        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .centerInside()
                .placeholder(R.drawable.ic_home)
                .transform(new CenterCrop());

        Glide.with(context).load(post.getPostImg()).apply(options).into(postImg);
    }
}