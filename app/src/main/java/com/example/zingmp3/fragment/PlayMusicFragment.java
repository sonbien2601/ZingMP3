package com.example.zingmp3.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import com.example.zingmp3.R;
import java.io.IOException;

public class PlayMusicFragment extends Fragment {
    Button btnBackSong;
    ImageButton btnplaySong, btnstopSong;
    SeekBar skkSong;
    MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean isPlaying = false;
    private int currentPosition = 0; // Lưu vị trí hiện tại của bài hát

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_play_music, container, false);
        btnBackSong = rootView.findViewById(R.id.btnBackSong);
        btnplaySong = rootView.findViewById(R.id.btnplaySong);
        btnstopSong = rootView.findViewById(R.id.btnstopSong);
        skkSong = rootView.findViewById(R.id.skkSong);

        skkSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnstopSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_Song();
            }

            private void stop_Song() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    currentPosition = mediaPlayer.getCurrentPosition(); // Lưu vị trí hiện tại của bài hát
                    isPlaying = false;
                }
            }
        });

        btnBackSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomeFragment();
            }

            private void showHomeFragment() {
                HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_fragment, homeFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnplaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_Song();
            }

            private void play_Song() {
                if (!isPlaying) {
                    try {
                        mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/doanzing-1d30d.appspot.com/o/SauLoiTuKhuoc.mp3?alt=media&token=8dad5097-c37f-4540-9e1c-479e4d17f7a1");
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.seekTo(currentPosition); // Đặt vị trí của bài hát
                                mp.start();
                                skkSong.setMax(mp.getDuration());

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while (mediaPlayer != null && mediaPlayer.isPlaying()) {
                                            try {
                                                final int currentPosition = mediaPlayer.getCurrentPosition();
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        skkSong.setProgress(currentPosition);
                                                    }
                                                });
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }).start();
                            }
                        });
                        mediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isPlaying = true;
                } else {
                    mediaPlayer.start();
                }
            }
        });
        return rootView;
    }
}
