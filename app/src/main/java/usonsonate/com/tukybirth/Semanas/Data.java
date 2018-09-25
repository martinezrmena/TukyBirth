package usonsonate.com.tukybirth.Semanas;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

import usonsonate.com.tukybirth.R;

public class Data {

    public static ArrayList<Information> getData(String [] datos) {

        ArrayList<Information> data = new ArrayList<>();

        int[] images = {
                R.drawable.semana_1,
                R.drawable.ani_cat_two,
                R.drawable.ani_cat_three,
                R.drawable.ani_cat_four,
                R.drawable.ani_cat_five,
                R.drawable.ani_cat_six,
                R.drawable.ani_cat_seven,

                R.drawable.ani_dog_one,
                R.drawable.ani_dog_two,
                R.drawable.ani_dog_three,
                R.drawable.ani_dog_four,
                R.drawable.ani_dog_five,

                R.drawable.ani_deer_one,
                R.drawable.ani_deer_two,
                R.drawable.ani_deer_three,
                R.drawable.ani_deer_four,

                R.drawable.bird_parrot_one,
                R.drawable.bird_parrot_two,
                R.drawable.bird_parrot_three,
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
                current.color = new ColorDrawable(Color.parseColor("#767099"));
            }else if(i > 11 && i < 25){
                current.color = new ColorDrawable(Color.parseColor("#009688"));
            }else{
                current.color = new ColorDrawable(Color.parseColor("#1b5e20"));
            }

            data.add(current);
        }

        return data;
    }

}
