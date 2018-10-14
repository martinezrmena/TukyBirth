package usonsonate.com.tukybirth.Threads;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;

import usonsonate.com.tukybirth.R;

public class CarouselBanner extends AsyncTask<Integer, Integer, String> {

    Context context;
    CollapsingToolbarLayout collapsingToolbarLayout;
    int[] images = {
            R.drawable.semana_1,
            R.drawable.semana_2,
            R.drawable.semana_3,
            R.drawable.semana_4,
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

            R.drawable.semana_17,
            R.drawable.semana_18,
            R.drawable.semana_19,
            R.drawable.bird_parrot_four,
            R.drawable.bird_parrot_five,
            R.drawable.bird_parrot_six,
            R.drawable.bird_parrot_seven,
            R.drawable.bird_parrot_eight
    };

    public CarouselBanner(Context context, CollapsingToolbarLayout collapsingToolbarLayout) {
        this.context = context;
        this.collapsingToolbarLayout = collapsingToolbarLayout;
    }

    @Override
    protected String doInBackground(Integer... params) {
        Integer contador = 0;

        while (contador != 24){
            try{
                Thread.sleep(1000);//milsegundos
                publishProgress(contador);
            }catch (InterruptedException e){
                e.printStackTrace();

            }
            contador++;
            if (contador == 24){
                contador = 0;
            }
        }
        return  "Encuesta procesada con éxito.";
    }

    @Override
    protected void onPreExecute() {
        //btnProgreso.setEnabled(false);
        //barra.setProgress(0);
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(context,"Encuesta procesada con éxito",Toast.LENGTH_SHORT).show();
        //barra.setProgress(0);
        //btnProgreso.setEnabled(true);
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        collapsingToolbarLayout.setBackgroundResource(images[values[0]]);
        super.onProgressUpdate(values);
    }
}
