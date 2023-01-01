package com.example.test_ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class Hub
{

    private Button Knopka;
    private CheckBox check;
    private TextView Name,Data,Time;
    String Data_cut;
    //private ImageView Background;

    public Hub(String Name1, String Data1, String Time1, Context context, LinearLayout MainLayer, int center)
    {
        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(center*2, 125);
        layout.setOrientation(LinearLayout.VERTICAL);
        //Background = new ImageView(context);

        Name = new TextView(context);
        Data = new TextView(context);

        Time = new TextView(context);

        //Background.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.back));
        //layout.setWeightSum(10);
        layout.setBackground(ContextCompat.getDrawable(context,R.drawable.back));
        Name.setText(Name1);
        Name.setTypeface(ResourcesCompat.getFont(context,R.font.bold_unix));
        Name.setTextSize(30);
        Name.setTextColor(Color.argb(200,171,94,232)); // фиолетовый
        //Name.setBackground(ContextCompat.getDrawable(context,R.drawable.back1));



        if(Data1.length() > 50)
        {
            Data_cut = Data1.substring(0,50);
            Data_cut += "...";
        }
        else
        {
            Data_cut = Data1;
        }
        Data.setText(Data_cut);
        Data.setTextSize(20);
        Data.setTextColor(Color.argb(200,230,198,255)); //фиолетовый
        //Data.setBackground(ContextCompat.getDrawable(context,R.drawable.back2));
        Data.setPadding(100,-5,0,0);

        //Time.setBackground(ContextCompat.getDrawable(context,R.drawable.back3));
        Time.setText(Time1);
        Time.setTextSize(35);
        Time.setTypeface(ResourcesCompat.getFont(context,R.font.bold_unix));
        Time.setTextColor(Color.argb(200,171,94,232)); //фиолетовый
       // Time.setPadding(center - 130,0,0,0);

       // MainLayer.addView(Background,lParams);
        layout.addView(Name,950,150);
        layout.addView(Data,950,200);
        layout.addView(Time,950,100);
        Name.setPadding(lParams.width/2 - Name.length()*20,30,0,0);
        Time.setPadding(lParams.width/2 - Time.length()*25,-20,0,0);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.full_screen(context,Name1,Data1,Time1);

            }
        });
        MainLayer.addView(layout, MainLayer.getWidth()-1,500);
        //MainLayer.addView(Name, MainLayer.getWidth() - 20,50);

        //MainLayer.addView(Data,lParams);
        //MainLayer.addView(Time,lParams);

    }
}
