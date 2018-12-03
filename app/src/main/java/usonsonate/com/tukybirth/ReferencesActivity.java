package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import usonsonate.com.tukybirth.Adaptadores.AdaptadorReferencias;
import usonsonate.com.tukybirth.Referencias.References;

public class ReferencesActivity extends AppCompatActivity {

    AdaptadorReferencias adaptadorReferencias;
    ListView lstReferencias;
    List<References> Referencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        lstReferencias = findViewById(R.id.lstReferencias);
        Referencias = new ArrayList<>();
        LlenarReferencias();
        adaptadorReferencias = new AdaptadorReferencias(this, Referencias);
        lstReferencias.setAdapter(adaptadorReferencias);

    }


    private void LlenarReferencias(){

        Referencias.add(new References("Libro","Laurence Pernoud","Espero un hijo", R.color.red));

    }
}
