package usonsonate.com.tukybirth.Slides;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import usonsonate.com.tukybirth.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    // list of images
    public int[] lst_images = {

            R.drawable.ovulo_rodeado_circle,
            R.drawable.aparato_genital_circle,
            R.drawable.ovulo_listo_circle,
            R.drawable.espermatozoide_circle,
            R.drawable.image_4
    };
    // list of titles
    public String[] lst_title = {
            "EL ÓVULO RODEADO.",
            "APARATO GENITAL FEMENINO.",
            "EL ÓVULO LISTO.",
            "ESPERMATOZOIDE.",
            "ROCKET"
    }   ;
    // list of descriptions
    public String[] lst_description = {
            "Un espermatozoide penetra el óvulo, pierde su cola, su núcleo localizado en la cabeza aumenta su volumen para luego fusionarse con el núcleo del óvulo.",
            "Los dos ovarios y las dos trompas desembocan en el útero. Abajo, la abertura del útero (el cuello) se encuentra en el fondo de la vagina.",
            "El óvulo listo para la fecundación. En el centro, el núcleo rodeado del citoplasma. Alrededor, la zona pelúcida circundada por algunas células del folículo.",
            "Un espermatozoide con su cabeza que contiene el núcleo y su cola que le permite desplazarse, mide de 10 a sesenta micras de longitud.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,"
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.parseColor("#011627"),
            Color.parseColor("#335C67"),
            Color.parseColor("#540B0E"),
            Color.parseColor("#1D8A99"),
            Color.rgb(1,188,212)
    };


    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}