package usonsonate.com.tukybirth.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import usonsonate.com.tukybirth.Calendar.DetalleDia;
import usonsonate.com.tukybirth.R;

public class AdaptadorDetalleCiclo  extends ArrayAdapter<DetalleDia> {

    public AdaptadorDetalleCiclo(Context context, List<DetalleDia> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obteniendo el dato
        DetalleDia detalleDia = getItem(position);
        //TODO inicializando el layout correspondiente al item (Contacto)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_detalle_dia, parent, false);
        }

        TextView txbTitle = (TextView) convertView.findViewById(R.id.TitleDetalle);
        TextView txbDetalle = (TextView) convertView.findViewById(R.id.TextoDetalle);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.iconDetalle);
        ImageView imgBackground = (ImageView) convertView.findViewById(R.id.BackgroundMainDetalle);


        // mostrar los datos
        txbTitle.setText(detalleDia.getTitle());
        txbDetalle.setText(detalleDia.getDescription());
        imgIcon.setImageResource(detalleDia.getIconImage());
        imgBackground.setBackgroundResource(detalleDia.getBackGroundImagen());
        imgBackground.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Return la convertView ya con los datos
        return convertView;
    }
}