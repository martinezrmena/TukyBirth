package usonsonate.com.tukybirth.Fragmentos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import usonsonate.com.tukybirth.MainNotes;
import usonsonate.com.tukybirth.R;

public class Fragmento02 extends Fragment {

    Button btnNotes;
    Context context;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragmento02, container, false);
        context = view.getContext();

        btnNotes = view.findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), MainNotes.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
