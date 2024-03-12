package com.example.zingmp3.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.zingmp3.fragment.HomeFragment;
import com.example.zingmp3.fragment.LibraryFragment;
import com.example.zingmp3.fragment.MusicSearchFragment;
import com.example.zingmp3.fragment.PMQFragment;
import com.example.zingmp3.fragment.PlayMusicFragment;
import com.example.zingmp3.fragment.ProfileFragment;
import com.example.zingmp3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private boolean isHomeSelected = true;
    Fragment fragment;
    TextView txtKP;
    ImageView imgS, imgPMQ;
    ScrollView scrview;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Sửa layout thành layout của HomeActivity
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("TRANG CHỦ")
        txtKP = findViewById(R.id.txtKP);
        imgS = findViewById(R.id.imgS);
        scrview = findViewById(R.id.scrview);
        imgPMQ = findViewById(R.id.imgPMQ);
        imgS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để hiển thị Fragment MusicSearchFragment
                showMusicSearchFragment();
                // Ẩn BottomNavigationView
                hideBottomNavigationView();

            }
        });
        imgPMQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPMQFragment();
            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menuHome) {
                    //toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    if (!isTextViewAndImageViewVisible) {
                        showTextViewAndImageView();
                    }
                    return true;
                } else if (id == R.id.menuLibary) {
                   // toolbar.setTitle("Thư viện");
                    fragment = new LibraryFragment();
                    loadFragment(fragment);
                    hideTextViewandImageView();
                    return true;
                } else if (id == R.id.menuProfile) {
                    //toolbar.setTitle("Cá nhân");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    hideTextViewandImageView();
                    return true;
                }
                return false;
            }
        });
    }

    private void showMusicSearchFragment() {
        MusicSearchFragment musicSearchFragment = new MusicSearchFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, musicSearchFragment);
        transaction.addToBackStack(null); // Để thêm Fragment vào ngăn xếp
        transaction.commit();
    }


    private void openPMQFragment() {
        // Kiểm tra nếu PMQFragment đã được thêm vào FragmentManager thì loại bỏ nó
        PMQFragment pmqFragment = (PMQFragment) getSupportFragmentManager().findFragmentByTag("PMQ_FRAGMENT");
        if (pmqFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(pmqFragment).commit();
        }

        PMQFragment newFragment = new PMQFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_fragment, newFragment, "PMQ_FRAGMENT"); // Sử dụng replace thay vì add
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void showTextViewAndImageView() {
        txtKP.setVisibility(View.VISIBLE);
        imgS.setVisibility(View.VISIBLE);
        isTextViewAndImageViewVisible = true;
    }

    private boolean isTextViewAndImageViewVisible = true;
    private void hideTextViewandImageView() {
        txtKP.setVisibility(View.GONE);
        imgS.setVisibility(View.GONE);
        isTextViewAndImageViewVisible = false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void hideBottomNavigationView() {
        // Ẩn BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.GONE);
    }

    private void showBottomNavigationView() {
        // Hiển thị lại BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    }



