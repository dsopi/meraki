package com.example.riddl.canvas.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.riddl.canvas.R;
import com.example.riddl.canvas.adapters.ThumbnailViewHolder;
import com.example.riddl.canvas.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProfileFragment extends Fragment {
    FirebaseRecyclerAdapter adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    DatabaseReference myRef = database.getReference("users");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = view.findViewById(R.id.home_recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("users").child(mAuth.getUid()).child("posts")
                .limitToLast(10);

        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(query, Post.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Post, ThumbnailViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ThumbnailViewHolder holder, int position, @NonNull Post model) {

//                Log.e("Username", model.getUsername());
                holder.bind(model, getContext());
            }

            @Override
            public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.profile_recyclerview, parent, false);
                return new ThumbnailViewHolder(view);
            }
        };


        GridLayoutManager manager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
