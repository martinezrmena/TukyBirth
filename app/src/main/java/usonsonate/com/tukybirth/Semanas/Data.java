package usonsonate.com.tukybirth.Semanas;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

import usonsonate.com.tukybirth.R;

public class Data {

    public static ArrayList<Information> getData(String [] datos, String detils []) {

        ArrayList<Information> data = new ArrayList<>();

        int[] images = {
                R.drawable.semana_1,
                R.drawable.semana_2,
                R.drawable.semana_3,
                R.drawable.semana_4,
                R.drawable.semana_5,
                R.drawable.semana_6,
                R.drawable.semana_7,
                R.drawable.semana_8,
                R.drawable.semana_9,
                R.drawable.semana_10,
                R.drawable.semana_11,
                R.drawable.semana_12,
                R.drawable.semana_13,
                R.drawable.semana_14,
                R.drawable.semana_15,
                R.drawable.wekk_16,
                R.drawable.week_17,
                R.drawable.week_18,
                R.drawable.week_19,
                R.drawable.week_20,
                R.drawable.week_21,
                R.drawable.week_22,
                R.drawable.week_23,
                R.drawable.week_24,
                R.drawable.week_25,
                R.drawable.week_26,
                R.drawable.week_27,
                R.drawable.week_28,
                R.drawable.week_29,
                R.drawable.week_30
        };

        String[] Semanas = datos;

        for (int i = 0; i < images.length; i++) {

            Information current = new Information();
            current.title = Semanas[i];
            current.imageId = images[i];
            if (i <= 11){
                current.color = new ColorDrawable(Color.parseColor("#00171F"));
            }else if(i > 11 && i < 25){
                current.color = new ColorDrawable(Color.parseColor("#1b5e20"));
            }else{
                current.color = new ColorDrawable(Color.parseColor("#A00E21"));
            }

            current.Details = detils[i];

            data.add(current);
        }

        return data;
    }

}
