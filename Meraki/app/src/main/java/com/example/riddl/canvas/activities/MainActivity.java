package com.example.riddl.canvas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.riddl.canvas.R;
import com.example.riddl.canvas.fragments.HomeFragment;
import com.example.riddl.canvas.fragments.NotificationsFragment;
import com.example.riddl.canvas.fragments.ProfileFragment;
import com.example.riddl.canvas.fragments.SearchFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationViewEx bnve;

    private ImageView upload_pic;

    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = FirebaseStorage.getInstance();


        upload_pic = findViewById(R.id.btnUpload);

        upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, UploadActivity.class));
            }
        });

        bnve = findViewById(R.id.bnve);
        bnve.setOnNavigationItemSelectedListener(navListener);

        bnve.enableAnimation(false);
        bnve.enableShiftingMode(false);
        bnve.enableItemShiftingMode(false);
        bnve.setTextVisibility(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationViewEx.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selected = new HomeFragment();
                    break;
                case R.id.notification:
                    selected = new NotificationsFragment();
                    break;
                case R.id.profile:
                    selected = new ProfileFragment();
                    break;
                case R.id.search:
                    selected = new SearchFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();

//            changeFragment(selected);


            return true;
        }
    };

    public void changeFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(0, R.anim.fade_out, 0, R.anim.fade_out);
            ft.replace(R.id.container, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        } else{
            super.onBackPressed();
        }
    }

}


