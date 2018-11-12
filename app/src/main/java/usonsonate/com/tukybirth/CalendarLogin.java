package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import usonsonate.com.tukybirth.SQLite.Notas;
import usonsonate.com.tukybirth.SQLite.Personas;

public class CalendarLogin extends AppCompatActivity {

    Personas persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_login);

        persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");

        Toast.makeText(CalendarLogin.this, "Duracion periodo: " + persona.getPeriodo() +
                " Duracion PMS: " + persona.getPMS() + " Duracion Ciclo: " + persona.getCiclo()+
                " Ultimo periodo: "+ persona.getUltimo_periodo() + " Cumpleaños: " + persona.getCumpleaños() +
                " Usuario: " + persona.getNombre(), Toast.LENGTH_SHORT).show();

    }
}
