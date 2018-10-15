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
                R.drawable.ani_deer_four,

                R.drawable.semana_17,
                R.drawable.semana_18,
                R.drawable.semana_19,
                R.drawable.bird_parrot_four,
                R.drawable.bird_parrot_five,
                R.drawable.bird_parrot_six,
                R.drawable.bird_parrot_seven,
                R.drawable.bird_parrot_eight
        };

        String[] Semanas = datos;

        for (int i = 0; i < images.length; i++) {

            Information current = new Information();
            current.title = Semanas[i];
            current.imageId = images[i];
            if (i <= 11){
                current.color = new ColorDrawable(Color.parseColor("#00171F"));
            }else if(i > 11 && i < 25){
                current.color = new ColorDrawable(Color.parseColor("#204E4A"));
            }else{
                current.color = new ColorDrawable(Color.parseColor("#A00E21"));
            }

            current.Details = detils[i];

            data.add(current);
        }

        return data;
    }

}
