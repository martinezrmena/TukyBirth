package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import usonsonate.com.tukybirth.SQLite.DB;

public class DetalleDiaPeriodo extends AppCompatActivity {


    private EditText txbAgregarNota;
    private TextView txbFechaPeriodo;
    private Spinner spSeverdidad;
    private String[] SEVERIDAD = {"Perdidas","Ligero", "Medio", "Fuerte"};
    private ArrayAdapter<String> adaptador;
    private CustomDateParse customDateParse;
    private RadioButton rdbtnSi;
    private RadioButton rdbtnNo;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dia_periodo);

        setTitle("Detalle del día");

        spSeverdidad = findViewById(R.id.spSeveridad);
        txbFechaPeriodo = findViewById(R.id.txbFechaPeriodo);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SEVERIDAD);
        customDateParse = new CustomDateParse();
        spSeverdidad.setAdapter(adaptador);

        txbFechaPeriodo.setText(customDateParse.convertirDateToString(new Date()));



    }
}
