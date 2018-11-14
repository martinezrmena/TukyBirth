package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import usonsonate.com.tukybirth.SQLite.Ciclo;
import usonsonate.com.tukybirth.SQLite.DB;
import usonsonate.com.tukybirth.SQLite.DetalleCiclo;
import usonsonate.com.tukybirth.SQLite.Notas;
import usonsonate.com.tukybirth.SQLite.Personas;

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
    private String DateCalendar;
    private DetalleCiclo detalleCiclo_seleccionado;
    private Ciclo ciclo_seleccionado;
    private String Inicializar;
    private Personas persona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dia_periodo);

        //Inicializamos variables
        setTitle("Detalle del día");
        spSeverdidad = findViewById(R.id.spSeveridad);
        txbFechaPeriodo = findViewById(R.id.txbFechaPeriodo);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SEVERIDAD);
        customDateParse = new CustomDateParse();
        spSeverdidad.setAdapter(adaptador);
        db = new DB(DetalleDiaPeriodo.this);

        //Obtenemos los parametros recibidos desde el anterior activity
        DateCalendar = getIntent().getExtras().getString("DATE_CALENDAR");
        Inicializar = getIntent().getExtras().getString("INICIALIZAR");
        if(Inicializar.equals("SI")){
            persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");
            detalleCiclo_seleccionado = null;
            ciclo_seleccionado = null;
            IniciarCiclos();


        }else{
            persona = null;
            detalleCiclo_seleccionado = (DetalleCiclo) getIntent().getExtras().getSerializable("DETALLE_CICLO");
            ciclo_seleccionado = (Ciclo) getIntent().getExtras().getSerializable("CICLO");
        }


        //Establecemos parametros de inicio
        txbFechaPeriodo.setText(DateCalendar);

    }


    private void GuardarDetalleCiclo(){

    }

    private void IniciarCiclos(){

        String DuracionCiclo = persona.getCiclo();
        String DuracionPeriodo = persona.getPeriodo();
        Calendar cInicio = customDateParse.convertirACalendar(persona.getUltimo_periodo());
        String FechaInicio = customDateParse.convertirDateToString(cInicio.getTime());
        Calendar cFin = customDateParse.convertirACalendar(customDateParse.convertirDateToString(customDateParse.cambiar_dia(customDateParse.convertirStringToDate(persona.getUltimo_periodo())
                , Integer.parseInt(persona.getCiclo()))));
        String FechaFin = customDateParse.convertirDateToString(cFin.getTime());

        String Estado = "EN PROCESO";

        Ciclo cicloinicio = new Ciclo("", DuracionCiclo, DuracionPeriodo, FechaInicio, FechaFin, Estado );

        db.guardar_O_ActualizarCiclos(cicloinicio);

    }


}
