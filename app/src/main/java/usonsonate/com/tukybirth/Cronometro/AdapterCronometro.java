package usonsonate.com.tukybirth.Cronometro;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import usonsonate.com.tukybirth.R;
import usonsonate.com.tukybirth.SQLite.Contracciones;

public class AdapterCronometro extends ArrayAdapter<Contracciones> {
    public AdapterCronometro(@NonNull Context context, @NonNull List<Contracciones> objects) {
        super(context, 0,objects );
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Contracciones lstcontracciones = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lstcontracciones,parent,false);
        }
        int valor;
        if(position>0){
            valor = position%2;
        }else {
         valor = 0;
        }

        TextView duracion = convertView.findViewById(R.id.txtduracion);
        TextView intervalo =  convertView.findViewById(R.id.txtintervalo);
        TextView iniciofin = convertView.findViewById(R.id.txtiniciofin);
        LinearLayout fondoitem = convertView.findViewById(R.id.fondoitem);


        duracion.setText(lstcontracciones.getDuracion());
        intervalo.setText(lstcontracciones.getIntervalo());
        iniciofin.setText(lstcontracciones.getIniciofin());
       // Log.d("valor: " ,String.valueOf(valor));
     if(valor==0){
         fondoitem.setBackgroundColor(Color.parseColor("#B3BBF5")); // celeste
     }else{
         fondoitem.setBackgroundColor(Color.parseColor("#FDFDFE")); //blanco
     }



        return convertView;
    }

}
