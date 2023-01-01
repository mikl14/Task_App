package com.example.test_ui.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test_ui.Hub;
import com.example.test_ui.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    public static String str;
    public static int raz ;
    public static int center ;

        String[] Name = new String[20];    //20 задач максимум
         String[] Data= new String[20];
         String[] Time= new String[20];

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final LinearLayout Layout = binding.Homelay;
        Layout.removeAllViews();
      if(raz ==1) {                                     //заполняем все по запросу на первом проходе

          if (str != "error") {
              String erase;
              for (int i = 0; i < 6; i++) {
                Name[i] = str.substring(2, str.indexOf("/"));
                  Data[i] = str.substring(str.indexOf("/") + 1, str.indexOf("|"));
                  str.replace('|', ' ');

                  Time[i] = str.substring(str.indexOf("|") + 1, str.indexOf(".."));
                  erase = str.substring(str.indexOf("..") + 2, str.length());
                  str = erase;
                  //   Hub ss = new Hub("Курсач афони",s,"12:00",this,HomeLayer,center );

                  Hub ss1 = new Hub(Name[i], Data[i], Time[i], getContext(), Layout, center);

              }
          }
          else
          {
              Hub ss1 = new Hub("Неудачно", "Проверьте соединение с интернетом", "", getContext(), Layout, center);
          }

      }

      if(raz == 2)                      //на втором проходе только рисуем
      {
          if(str != "error") {
              for (int i = 0; i < 6; i++) {
                  Hub ss1 = new Hub(Name[i], Data[i], Time[i], getContext(), Layout, center);
              }
          }
         else
          {
              Hub ss1 = new Hub("Неудачно", "Проверьте соединение с интернетом", "", getContext(), Layout, center);
          }
      }
        raz = 2;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}