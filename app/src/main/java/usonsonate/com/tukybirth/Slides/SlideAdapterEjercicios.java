package usonsonate.com.tukybirth.Slides;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import usonsonate.com.tukybirth.Ejercicios.AdapterListExercise;
import usonsonate.com.tukybirth.Ejercicios.DataExercise;
import usonsonate.com.tukybirth.InformationExercises;
import usonsonate.com.tukybirth.R;

public class SlideAdapterEjercicios extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Dialog myDialog;

    // list of images
    public int[] lst_images = {

            R.drawable.trim1circular,
            R.drawable.trim2circular,
            R.drawable.trim3circular,

    };

    /**************************************************************************/
    public int[] lst_imagesBack = {

            R.drawable.embarazo11,
            R.drawable.embarazo22,
            R.drawable.embarazo33,

    };
    /**************************************************************************/
    // list of titles
    public String[] lst_title = {
            "Ejercicios para el primer trimestre.",
            "Ejercicios para el segundo trimestre.",
            "Ejercicios para el tercer trimestre.",

    }   ;
    // list of descriptions
    public String[] lst_description = {
            "Durante los tres primeros meses es importante ir acondicionado el cuerpo a los cambios. Por lo tanto, ¿qué debemos hacer?.",
            "Durante el segundo trimestre también es importante realizar actividad física para mantenernos en forma",
            "En los últimos tres meses, recomendamos realizar unos ejercicios específicos para prepararnos para el parto"
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.parseColor("#F28131"),
            Color.parseColor("#3F39FC"),
            Color.parseColor("#FB585D")
    };


    public SlideAdapterEjercicios(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return this.lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_exercises,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayoutExercises);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimgExer);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitleExer);
        TextView description = (TextView) view.findViewById(R.id.txtdescriptionExer);
        /**************************************************************************/
        layoutslide.setBackgroundResource(lst_imagesBack[position]);
        /**************************************************************************/
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        myDialog = new Dialog(context);

        imgslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context, "valor de la posicion: " + position, Toast.LENGTH_SHORT).show();
                Intent detail = new Intent(context.getApplicationContext(), InformationExercises.class);
                new DataExercise(String.valueOf(position));
                new AdapterListExercise(position);
                context.startActivity(detail);
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
