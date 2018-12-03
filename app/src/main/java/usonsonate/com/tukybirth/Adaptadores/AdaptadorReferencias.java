package usonsonate.com.tukybirth.Adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import usonsonate.com.tukybirth.R;
import usonsonate.com.tukybirth.Referencias.References;
import usonsonate.com.tukybirth.SQLite.Notas;

public class AdaptadorReferencias extends ArrayAdapter<References> {

    public AdaptadorReferencias(Context context, List<References> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obteniendo el dato
        References reference = getItem(position);
        //TODO inicializando el layout correspondiente al item (Contacto)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_referencia, parent, false);
        }

        TextView lblTipo = (TextView) convertView.findViewById(R.id.txbTipoReferencia);
        TextView lblAutor = (TextView) convertView.findViewById(R.id.txbAutor);
        TextView lblReferencia = (TextView) convertView.findViewById(R.id.txbReferencia);
        CardView container = (CardView) convertView.findViewById(R.id.MainContainer);

        // mostrar los datos
        lblTipo.setText(reference.getTipo());
        lblAutor.setText(reference.getAutor());
        lblReferencia.setText(reference.getReferencia());
        container.setBackground(new ColorDrawable(reference.getColor()));

        // Return la convertView ya con los datos
        return convertView;
    }
}
