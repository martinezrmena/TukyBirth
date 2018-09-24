package usonsonate.com.tukybirth.Semanas;

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

            data.add(current);
        }

        return data;
    }

}
