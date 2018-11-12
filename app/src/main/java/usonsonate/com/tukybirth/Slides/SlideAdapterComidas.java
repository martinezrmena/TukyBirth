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

import usonsonate.com.tukybirth.Ejercicios.DataExercise;
import usonsonate.com.tukybirth.InformacionComidas;
import usonsonate.com.tukybirth.InformationExercises;
import usonsonate.com.tukybirth.R;

public class SlideAdapterComidas extends PagerAdapter {

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

            R.drawable.comida1,
            R.drawable.comida2,
            R.drawable.comida3,

    };
    /**************************************************************************/
    // list of titles
    public String[] lst_title = {
            "Comidas para el primer trimestre.",
            "Comidas para el segundo trimestre.",
            "Comidas para el tercer trimestre."

    }   ;
    // list of descriptions
    public String[] lst_description = {
            "Nuestros menús del primer trimestre incluyen comidas ricas en ácido fólico, que es vital para el desarrollo del sistema nervioso del bebé",
            "En el segundo trimestre, los menús sugieren recetas repletas de calcio y vitamina D, esenciales para el crecimiento de los huesos y para que tu bebé tenga dientes fuertes.",
            "En los menús del tercer trimestre hemos seleccionado alimentos que proporcionan una buena dosis de energía para lidiar con el cansancio de los últimos meses"

    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.parseColor("#2D3142"),
            Color.parseColor("#335C67"),
            Color.parseColor("#540B0E")
    };


    public SlideAdapterComidas(Context context) {
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
                Intent detail = new Intent(context.getApplicationContext(), InformacionComidas.class);
                DataExercise valor = new DataExercise(String.valueOf(position));
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
