package com.example.zingmp3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zingmp3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        // Thêm sự kiện click vào rootView để kiểm tra và ngăn chặn sự kiện click lan ra bên ngoài
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ngăn chặn sự kiện click từ lan ra bên ngoài fragment
                v.setEnabled(false);

                // Thêm log để kiểm tra xem sự kiện click này có được gọi không
                Log.d("ProfileFragment", "Layout Clicked");
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
        ImageView imgSetting = rootView.findViewById(R.id.imgSetting);
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi phương thức để hiển thị Fragment SettingFragment
                showSettingFragment();
            }
        });
        return rootView;
    }

    private void showSettingFragment() {
        SettingFragment settingFragment = new SettingFragment();

        // Sử dụng FragmentManager để thay thế fragment hiện tại bằng SettingFragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment, settingFragment)
                .addToBackStack(null)
                .commit();
    }
}