package com.example.test_ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.test_ui.ui.gallery.GalleryFragment;
import com.example.test_ui.ui.home.HomeFragment;
import com.example.test_ui.ui.slideshow.SlideshowFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_ui.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static ActivityMainBinding binding;
    public static LinearLayout HomeLayer;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";
    EditText ss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ss = (EditText) findViewById(R.id.editTextTextPersonName);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HomeLayer = (LinearLayout) findViewById(R.id.Homelay);

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.lay.setVisibility(View.INVISIBLE);
        binding.appBarMain.buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarMain.lay.setVisibility(View.INVISIBLE);
                MainActivity.HomeLayer.setVisibility(View.VISIBLE);
                binding.appBarMain.lay.removeViewAt(2);
                binding.appBarMain.lay.removeViewAt(2);
                binding.appBarMain.lay.removeViewAt(2);
               // ImageView ss = (ImageView) findViewById(R.id.ava);
                //ss.setImageResource(R.drawable.kot);

                saveText();
                loadText();
            }

        });

     /*    findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveText();
            }
        }); */

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
       // navController.


        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int center = (int)(display.getWidth()/2);
        sql_connect ss45 = new sql_connect();
        ss45.execute();
        String s = "error";
        String Name = null,Data = null,Time = null;
        int pos = 0;

        try {
            s = ss45.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            s = "error";
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }


        HomeFragment.str = s;
        GalleryFragment.str =s;
        HomeFragment.raz = 1;
        GalleryFragment.raz = 1;
        HomeFragment.center = center;
        GalleryFragment.center = center;//передаем все данные в домашнию страницу

         if(s != "error") {    //заполняем при входе в приложение первуб страницу
            String erase;
            for (int i = 0; i < 1; i++) {
                Name = s.substring(6, s.indexOf("/"));
                GalleryFragment.Name[i] = s.substring(6, s.indexOf("/"));

                Data = s.substring(s.indexOf("/") + 1, s.indexOf("|"));
                GalleryFragment.Data[i] = s.substring(s.indexOf("/") + 1, s.indexOf("|"));
                s.replace('|', ' ');

                Time = s.substring(s.indexOf("|") + 1, s.indexOf(".."));
                GalleryFragment.Time[i] = s.substring(s.indexOf("|") + 1, s.indexOf(".."));
                erase = s.substring(s.indexOf("..") + 2, s.length());
                s = erase;

                Hub ss1 = new Hub(Name, Data, Time, this, HomeLayer, center);

            }
        }
         else
         {
             Hub ss1 = new Hub("Неудачно", "Проверьте соединение с интернетом", "", this, HomeLayer, center);

         }
        //  Hub ss2 = new Hub("Курсач афони","sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss","12:00",this,HomeLayer,center );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        loadText();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void full_screen(Context context, String Name, String Data, String Time)
    {
        TextView Name_view = new TextView(context);
        Name_view.setText(Name);
        binding.appBarMain.lay.addView(Name_view);
        Name_view.setTypeface(ResourcesCompat.getFont(context,R.font.bold_unix));
        Name_view.setTextSize(30);
        Name_view.setTextColor(Color.argb(200,171,94,232));

        TextView Data_view = new TextView(context);
        Data_view.setText(Data);
        binding.appBarMain.lay.addView(Data_view);
        Data_view.setTextSize(20);
        Data_view.setTextColor(Color.argb(200,230,198,255)); //фиолетовый

        Button I_DO = new Button(context);
        I_DO.setText("Я сделал");
        I_DO.setWidth(200);
        I_DO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.appBarMain.lay.setVisibility(View.INVISIBLE);
                binding.appBarMain.lay.removeViewAt(2);
                binding.appBarMain.lay.removeViewAt(2);
                binding.appBarMain.lay.removeViewAt(2);
                MainActivity.HomeLayer.setVisibility(View.VISIBLE);
            }
        });
        binding.appBarMain.lay.addView(I_DO);
        MainActivity.HomeLayer.setVisibility(View.INVISIBLE);

        binding.appBarMain.lay.setVisibility(View.VISIBLE);

    }

    void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, "Богданьчик 123");
        ed.commit();

    }

    void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        ((TextView) findViewById(R.id.name)).setText(savedText);
       // Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
    }
}