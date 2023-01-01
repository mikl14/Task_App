package com.example.test_ui.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test_ui.Hub;
import com.example.test_ui.databinding.FragmentGalleryBinding;
import com.example.test_ui.databinding.FragmentHomeBinding;
import com.example.test_ui.ui.home.HomeViewModel;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
public static String str;
    public static int raz ;
    public static int center ;

    public static String[] Name = new String[20];    //20 задач максимум
    public static String[] Data= new String[20];
    public static String[] Time= new String[20];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final LinearLayout Layout = binding.Homelay;
        Layout.removeAllViews();
        if(str != "error") {
            for (int i = 0; i < 6; i++) {

                Hub ss1 = new Hub(Name[i], Data[i], Time[i], getContext(), Layout, center);
            }
        }
        else
        {
            Hub ss1 = new Hub("Неудачно", "Проверьте соединение с интернетом", "", getContext(), Layout, center);
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}