package usonsonate.com.tukybirth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragmento01 extends Fragment {
    private Button Procesar;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragmento01, container, false);

        Procesar = view.findViewById(R.id.btnactivity);
        Procesar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Context context = view.getContext();
                Intent detail = new Intent(context.getApplicationContext(), InformacionSemanas.class);
                context.startActivity(detail);
                //Toast.makeText(context, "FUNCIONA SIN HACER NADA" , Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
