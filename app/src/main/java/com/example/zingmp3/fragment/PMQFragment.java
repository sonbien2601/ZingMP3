package com.example.zingmp3.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zingmp3.R;
import com.example.zingmp3.activity.PlayMusicActivity;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PMQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PMQFragment extends Fragment {
Button btnPlayPMQ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PMQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PMQList.
     */
    // TODO: Rename and change types and number of parameters
    public static PMQFragment newInstance(String param1, String param2) {
        PMQFragment fragment = new PMQFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_p_m_q_list, container, false);
        btnPlayPMQ= rootView.findViewById(R.id.btnPlaypmq);
        // Thêm sự kiện click vào rootView để kiểm tra và ngăn chặn sự kiện click lan ra bên ngoài
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ngăn chặn sự kiện click từ lan ra bên ngoài fragment
                v.setEnabled(false);

                // Thêm log để kiểm tra xem sự kiện click này có được gọi không
                Log.d("PMQFragment", "Layout Clicked");
            }
        });

        // Loại bỏ sự kiện touch khi lan ra bên ngoài fragment
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Ngăn chặn sự kiện touch từ lan ra bên ngoài fragment
                return true;
            }
        });
        btnPlayPMQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlayMusicFragment();
            }
        });

        // Sử dụng findViewById để tìm Button và thiết lập sự kiện click cho nó
        Button btnBack = rootView.findViewById(R.id.btnbackpmq);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Loại bỏ fragment PMQ khỏi ngăn xếp fragment
                getFragmentManager().popBackStack();
            }
        });

        // Trả về rootView sau khi thêm sự kiện click, touch và thiết lập sự kiện cho Button
        return rootView;
    }
    private void showPlayMusicFragment() {
        PlayMusicFragment playMusicFragment = new PlayMusicFragment();

        // Sử dụng FragmentManager để thay thế PMQFragment bằng PlayMusicFragment
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_fragment, playMusicFragment)
                .addToBackStack(null)
                .commit();
    }

}