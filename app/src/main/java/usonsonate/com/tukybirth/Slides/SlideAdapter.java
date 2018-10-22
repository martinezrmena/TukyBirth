package usonsonate.com.tukybirth.Slides;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import usonsonate.com.tukybirth.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Dialog myDialog;

    // list of images
    public int[] lst_images = {

            R.drawable.ovulo_rodeado_circle_black,
            R.drawable.aparato_genital_circle,
            R.drawable.ovulo_listo_circle,
            R.drawable.espermatozoide_circle,
            R.drawable.ovulo_liberacion_cicle,
            R.drawable.egg_travel_circle,
            R.drawable.ic_implantacion
    };
    // list of titles
    public String[] lst_title = {
            "EL ÓVULO RODEADO.",
            "APARATO GENITAL FEMENINO.",
            "EL ÓVULO LISTO.",
            "ESPERMATOZOIDE.",
            "LIBERACIÓN DEL ÓVULO.",
            "EL VIAJE DEL HUEVO.",
            "DEL HUEVO AL NIÑO."
    }   ;
    // list of descriptions
    public String[] lst_description = {
            "Un espermatozoide penetra el óvulo, pierde su cola, su núcleo localizado en la cabeza aumenta su volumen para luego fusionarse con el núcleo del óvulo.",
            "Los dos ovarios y las dos trompas desembocan en el útero. Abajo, la abertura del útero (el cuello) se encuentra en el fondo de la vagina.",
            "El óvulo listo para la fecundación. En el centro, el núcleo rodeado del citoplasma. Alrededor, la zona pelúcida circundada por algunas células del folículo.",
            "Un espermatozoide con su cabeza que contiene el núcleo y su cola que le permite desplazarse, mide de 10 a sesenta micras de longitud.",
            "Sobre el ovario, el folículo de Grasf listo para romperse. En el folículo, el óvulo, mientras que cerca del ovario las franjas del pabellón de la trompa preparadas para 'atrapar' al óvulo.",
            "Luego de que el óvulo es liberado y se encuentra rodeado por las celulas folinculares será fecundado por un espermatozoide. Comienza la división hasta llegar a 16 células. Por  último el huevo se implanta en la mucosa uterina o nidación.",
            "Esta nueva y única célula es el principio de un ser humano y para lograrlo ella o él se implantará en el revestimiento uterino creciendo y madurando por los próximos 9 meses hasta el nacimiento. "
    };
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.parseColor("#2D3142"),
            Color.parseColor("#335C67"),
            Color.parseColor("#540B0E"),
            Color.parseColor("#1D8A99"),
            Color.parseColor("#9EA57C"),
            Color.parseColor("#B21025"),
            Color.parseColor("#274690")
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
    public Object instantiateItem(ViewGroup container, final int position) {
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
        myDialog = new Dialog(context);

        imgslide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txtclose, txbnumero;
                PhotoView imagenPopUp;
                //inicializamos las variables
                myDialog.setContentView(R.layout.custom_image_popup);
                txtclose = myDialog.findViewById(R.id.txtclose);
                imagenPopUp = myDialog.findViewById(R.id.ImagePopup);

                //establecemos los valores
                imagenPopUp.setImageResource(lst_images[position]);
                txtclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
            }
        });

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}