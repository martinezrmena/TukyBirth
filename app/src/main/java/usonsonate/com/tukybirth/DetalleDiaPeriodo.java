package usonsonate.com.tukybirth;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
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
    private String[] SEVERIDAD = {"Dolores","Perdidas","Ligero", "Medio", "Fuerte"};
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
    private Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dia_periodo);

        //Inicializamos variables
        setTitle("Detalle del día");
        spSeverdidad = findViewById(R.id.spSeveridad);
        txbFechaPeriodo = findViewById(R.id.txbFechaPeriodo);
        rdbtnSi = findViewById(R.id.rdbtnSi);
        rdbtnNo = findViewById(R.id.rdbtnNo);
        txbAgregarNota = findViewById(R.id.txbDetalle);
        btnGuardar = findViewById(R.id.btnGuadarDetalleCiclo);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SEVERIDAD);
        customDateParse = new CustomDateParse();
        spSeverdidad.setAdapter(adaptador);
        db = new DB(DetalleDiaPeriodo.this);

        //region INICIALIZACION
        //Obtenemos los parametros recibidos desde el anterior activity
        DateCalendar = getIntent().getExtras().getString("DATE_CALENDAR");
        Inicializar = getIntent().getExtras().getString("INICIALIZAR");

        if(Inicializar.equals("SI")){
            //NECESITAMOS REGISTRAR DESDE CERO TODO, ES UNA INICIALIZACIÓN
            persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");
            detalleCiclo_seleccionado = null;
            ciclo_seleccionado = null;
            IniciarCiclos();

        }else if(Inicializar.equals("NO")){
            //NECESITAMOS REESTABLECER TODO, YA QUE ES UNA ACTUALIZACIÓN
            persona = null;
            detalleCiclo_seleccionado = (DetalleCiclo) getIntent().getExtras().getSerializable("DETALLE_CICLO");
            ciclo_seleccionado = (Ciclo) getIntent().getExtras().getSerializable("CICLO");
            ReasignarValores();

        }else if(Inicializar.equals("MEDIO")){
            //NECESITAMOS PARAMETROS BASICOS, TODO SERÁ UNA INSERCIÓN
            ciclo_seleccionado = (Ciclo) getIntent().getExtras().getSerializable("CICLO");
            persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");

        }

        //Establecemos parametros de inicio
        txbFechaPeriodo.setText(DateCalendar);
        //endregion
    }


    public void ReasignarValores(){

        if (ciclo_seleccionado.getEstado().equals("TERMINO")){
            rdbtnSi.setEnabled(false);
            rdbtnNo.setEnabled(false);
            rdbtnSi.setEnabled(true);
        }

        int seleccion = 0;

        switch (detalleCiclo_seleccionado.getSeveridad()){

            case "Dolores":
                seleccion = 0;
                break;

            case "Perdidas":
                seleccion = 1;
                break;

            case "Ligero":
                seleccion = 2;
                break;

            case "Medio":
                seleccion = 3;
                break;

            case "Fuerte":
                seleccion = 4;
                break;
        }


        spSeverdidad.setSelection(seleccion);

        txbAgregarNota.setText(detalleCiclo_seleccionado.getDetalle());

    }

    public void GuardarDetalleCiclo(View v) throws ParseException {
        String Message = "";

        if (rdbtnSi.isChecked() && !ciclo_seleccionado.getEstado().equals("TERMINO")){
            Message = "Si termina un ciclo el proceso es irreversible para garantizar la predicción de los datos ¿Desea Proceder?";
        }else{
            Message = "¿Desea procesar la información actual?";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(DetalleDiaPeriodo.this);
        builder.setIcon(R.drawable.period_blood).
                setTitle("Atención").setMessage(Message).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String estado = "";

                //region GUARDAR_DATOS
                //VALIDAMOS SI TERMINO EL CICLO ACTUAL Y SI EL CICLO ACTUAL NO FUE
                //TERMINADO PREVIAMENTE
                if (rdbtnSi.isChecked() && !ciclo_seleccionado.getEstado().equals("TERMINO")){
                    //Si termino el ciclo debemos cerrar el ciclo actual y abrir uno nuevo
                    //actualizamos los estados del último ciclo antes de insertar el nuevo

                    estado = "TERMINO";
                    Date current_Date = new Date();
                    ciclo_seleccionado.setFecha_fin(customDateParse.FormatSQLite(customDateParse.convertirDateToString(current_Date)));
                    ciclo_seleccionado.setDuracion_periodo(ConsultarPeriodoCicloActual());
                    try {
                        ciclo_seleccionado.setDuracion_ciclo(customDateParse.Diferencia_Dias_Fechas(
                                ciclo_seleccionado.getFecha_inicio(), ciclo_seleccionado.getFecha_fin()
                        ));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //Actualizamos el último ciclo
                    db.guardar_O_ActualizarCiclos(ciclo_seleccionado);

                    //creamos un nuevo ciclo que no haya sido procesado
                    String id_ciclo = "";
                    String duracion_ciclo = ciclo_seleccionado.getDuracion_ciclo();
                    String duracion_periodo = ciclo_seleccionado.getDuracion_periodo();
                    String fecha_inicio = ciclo_seleccionado.getFecha_fin();
                    String fecha_fin = customDateParse.convertirDateToString(customDateParse.cambiar_dia(
                            customDateParse.convertirStringToDate(ciclo_seleccionado.getFecha_inicio()),
                            Integer.parseInt(ciclo_seleccionado.getDuracion_ciclo())
                    ));
                    estado = "EN PROCESO";

                    Ciclo newCiclo = new Ciclo(id_ciclo,duracion_ciclo, duracion_periodo, fecha_inicio, fecha_fin,estado);

                    db.guardar_O_ActualizarCiclos(newCiclo);

                }

                //VALIDAMOS SI HAREMOS UNA INSERCIÓN O ACTUALIZACIÓN DEL DETALLE DEL CICLOS
                if(Inicializar.equals("MEDIO")){
                    // TODO SERÁ UNA INSERCIÓN
                    String iddetalleciclo = "";
                    String idciclo = ciclo_seleccionado.getId_ciclo();
                    String fecha_introducción = customDateParse.FormatSQLite(txbFechaPeriodo.getText().toString());
                    String severidad = spSeverdidad.getSelectedItem().toString();
                    String detalle = txbAgregarNota.getText().toString();
                    DetalleCiclo detalleCiclo = new DetalleCiclo(iddetalleciclo,idciclo,fecha_introducción,severidad,detalle);

                    //insertamos
                    db.guardar_O_ActualizarDetalleCiclos(detalleCiclo);


                    Intent resultIntent = new Intent();
                    setResult(CalendarLogin.INSERTAR, resultIntent);
                    finish();

                }else if(Inicializar.equals("NO")){
                    //TODO SERÁ UN ACTUALIZACIÓN
                    String iddetalleciclo = detalleCiclo_seleccionado.getId_detalle();
                    String idciclo = ciclo_seleccionado.getId_ciclo();
                    String fecha_introducción = customDateParse.FormatSQLite(txbFechaPeriodo.getText().toString());
                    String severidad = spSeverdidad.getSelectedItem().toString();
                    String detalle = txbAgregarNota.getText().toString();
                    DetalleCiclo detalleCiclo = new DetalleCiclo(iddetalleciclo,idciclo,fecha_introducción,severidad,detalle);

                    //actualizamos
                    db.guardar_O_ActualizarDetalleCiclos(detalleCiclo);

                    Intent resultIntent = new Intent();
                    setResult(CalendarLogin.MODIFICAR, resultIntent);
                    finish();
                }
                //endregion

            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DetalleDiaPeriodo.this, "Cancelaste la acción.", Toast.LENGTH_SHORT ).show();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();




    }


    private void IniciarCiclos(){

        String DuracionCiclo = persona.getCiclo();
        String DuracionPeriodo = persona.getPeriodo();
        String FechaInicio = customDateParse.FormatSQLite(persona.getUltimo_periodo());
        String FechaFin = customDateParse.FormatSQLiteCambiarDia(persona.getUltimo_periodo(), Integer.parseInt(persona.getCiclo()));

        String Estado = "EN PROCESO";

        Ciclo cicloinicio = new Ciclo("", DuracionCiclo, DuracionPeriodo, FechaInicio, FechaFin, Estado );

        db.guardar_O_ActualizarCiclos(cicloinicio);

    }

    private String ConsultarPeriodoCicloActual(){

        String valor_periodo = "";

        valor_periodo =  db.getArrayDetalleCiclosPromedio(
          db.getCursorDetalleCicloPromedio(ciclo_seleccionado.getId_ciclo())
        );

        return valor_periodo;

    }


}
