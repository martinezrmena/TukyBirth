package usonsonate.com.tukybirth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HerramientasActivity extends AppCompatActivity {

    private CardView btncronometro,btnnnotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas);

        btnnnotas = findViewById(R.id.btnnotas);
        btncronometro = findViewById(R.id.btncronometro);

        btnnnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainNotes.class);
                startActivity(intent);
            }
        });

        btncronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContraccionesActivity.class);
                startActivity(intent);
            }
        });


    }
}
