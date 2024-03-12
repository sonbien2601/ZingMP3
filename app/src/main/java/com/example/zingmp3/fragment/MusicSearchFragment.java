package com.example.zingmp3.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.zingmp3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicSearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MusicSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicSearchFragment newInstance(String param1, String param2) {
        MusicSearchFragment fragment = new MusicSearchFragment();
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
        //return inflater.inflate(R.layout.fragment_music_search, container, false);

        View rootView = inflater.inflate(R.layout.fragment_music_search, container, false);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ngăn chặn sự kiện click từ lan ra bên ngoài fragment
                v.setEnabled(false);
                // Thêm log để kiểm tra xem sự kiện click này có được gọi không
                Log.d("MusicSearchFragment", "Layout Clicked");
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
        EditText edtSearch = rootView.findViewById(R.id.edtSearch);
        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Ẩn BottomNavigationView khi nhấn vào EditText
                hideBottomNavigationView();
                return false; // Trả về false để cho phép sự kiện touch tiếp tục
            }
        });
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Hiển thị lại BottomNavigationView khi EditText không còn focus
                    showBottomNavigationView();
                }
            }
        });
        Button btnBackSearch = rootView.findViewById(R.id.btnbackSearch);
        btnBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị lại BottomNavigationView khi thoát khỏi fragment MusicSearchFragment
                showBottomNavigationView();
                // Loại bỏ fragment MusicSearchFragment khỏi ngăn xếp fragment
                getFragmentManager().popBackStack();
            }
        });

        return rootView;

    }

    private void showBottomNavigationView() {
        BottomNavigationView navigation = getActivity().findViewById(R.id.navigation);
        if (navigation != null) {
            navigation.setVisibility(View.VISIBLE);
        }
    }

    private void hideBottomNavigationView() {
        BottomNavigationView navigation = getActivity().findViewById(R.id.navigation);
        if (navigation != null) {
            navigation.setVisibility(View.GONE);
        }
    }


}