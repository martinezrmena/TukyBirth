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
        Referencias.add(new References("Web","conmishijos","https://www.conmishijos.com/embarazo/parto/como-calcular-la-fecha-probable-de-parto/", R.color.blue));
        Referencias.add(new References("Web","babycenter","https://espanol.babycenter.com/c15600472/tu-embarazo-semana-a-semana", R.color.materialgold));
        Referencias.add(new References("Web","belibe","https://www.belibe.es/", R.color.green));
        Referencias.add(new References("Web","HuggiesR","https://www.hueggies.com/", R.color.orange));
        Referencias.add(new References("Web","mi bb y yo","https://www.mibebeyyo.com/", R.color.materialdarkred));

    }
}
