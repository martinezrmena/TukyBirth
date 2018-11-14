package usonsonate.com.tukybirth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import usonsonate.com.tukybirth.Calendar.MyEventDay;
import usonsonate.com.tukybirth.SQLite.Ciclo;
import usonsonate.com.tukybirth.SQLite.DB;
import usonsonate.com.tukybirth.SQLite.DetalleCiclo;
import usonsonate.com.tukybirth.SQLite.Notas;
import usonsonate.com.tukybirth.SQLite.Personas;
import usonsonate.com.tukybirth.SQLite.PromedioCiclos;

public class CalendarLogin extends AppCompatActivity {

    private Personas persona;
    private DB db;
    private Calendar c;
    private Date currentDate;
    private CalendarView CalendarPeriodo;
    private FloatingActionButton btnAgregar;
    private String FinCiclo;
    private String InicioPeriodo = "";
    private CustomDateParse customDateParse;
    private List<EventDay> mPredictDaysCiclo = new ArrayList<>();
    private List<EventDay> mRegisteredDaysCiclo = new ArrayList<>();
    private List<Ciclo> lstCiclos;
    private List<DetalleCiclo> lstDetalleCiclo;
    private PromedioCiclos promedioCiclos;
    private Ciclo UltimoCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_login);

        persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");
        CalendarPeriodo = findViewById(R.id.clnPeriodo);
        btnAgregar = findViewById(R.id.fabtnPeriodo);
        customDateParse = new CustomDateParse();
        db = new DB(CalendarLogin.this);
        setTitle("Datos del ciclo");

        //region Inicializar_Calendario
        //Inicializamos el calendario del sistema para poder asignar una fecha
        c = Calendar.getInstance();
        currentDate = new Date();
        c.setTime(currentDate);

        //Establecemos la fecha de inicio del calendario principal (widget)
        try {
            CalendarPeriodo.setDate(c);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
        //endregion

        //region Calcular Ciclo con Datos Iniciales

        //Obtenemos los promedios para conocer si calculamos desde ellos o desde parametros iniciales
        CalculatePromedios();
        if (Integer.parseInt(promedioCiclos.getCOUNT()) >0){

            //Consultamos el ultimo periodo almacenado
            ConsultarUltimoPeriodo();

            //Si poseemos registros almacenados consultamos los de este mes
            if(ConsultarCiclos()){

                //Si tiene ciclos calculamos los detalles de esos ciclos en este mes
                ConsultarDetalleCiclos();

                //Mostramos todos los detalles de los ciclos almacenados
                for (DetalleCiclo d:lstDetalleCiclo) {
                    DetallesCicloAlmacenados(d);
                }

            }

            //region PrediccionProximoCiclo
            if (customDateParse.convertirDateToStringMonth_Year(customDateParse.convertirStringToDate(UltimoCiclo.getFecha_fin()))
                    .equals(customDateParse.convertirDateToStringMonth_Year(CalendarPeriodo.getCurrentPageDate().getTime()))){

                //si la fecha del ultimo ciclo que finalizo es igual a la de la pagina actual del calendario
                // haremos prediccion del próximo ciclo, de lo contrario no vale la pena calcular la prediccion
                // si no se vera en la actual página del calendario
                FinCiclo = calculateFinCiclo(
                        UltimoCiclo.getFecha_fin(), String.valueOf(promedioCiclos.getDURACION_CICLO()));

                InicioPeriodo = calculateInicioPeriodo(Integer.parseInt(promedioCiclos.getDURACION_PERIODO()));
                PredecirProximoCiclo(InicioPeriodo);
            }
            //endregion

        }else{
            //Si no poseemos registros calculamos desde los parametros ingresados
            // desde el login
            FinCiclo =  calculateFinCiclo(persona.getUltimo_periodo(), persona.getCiclo());
            InicioPeriodo = calculateInicioPeriodo(Integer.parseInt(persona.getPeriodo()));
            PredecirProximoCiclo(InicioPeriodo);
        }


        //endregion

        //region Evento Dia Actual

        CalendarPeriodo.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Date date = customDateParse.convertirStringToDate(customDateParse.convertirDateToString(new Date()));
                Date pressDate = customDateParse.convertirStringToDate(customDateParse.convertirDateToString(eventDay.getCalendar().getTime()));

                if (date.after(eventDay.getCalendar().getTime()) || date.equals(pressDate)){
                    Intent intent = new Intent(getApplicationContext(), DetalleDiaPeriodo.class);

                    startActivity(intent);
                }else{

                    Toast.makeText(CalendarLogin.this, "No se pueden agregar detalles a dias posteriores de la fecha actual.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //endregion

    }

    private void PredecirProximoCiclo(String InicioPeriodo){

        String fecha_temporal = InicioPeriodo;

        for (int i = 0; i < Integer.parseInt(persona.getPeriodo()); i++){

            MyEventDay myEventDay = new MyEventDay(customDateParse.convertirACalendar(fecha_temporal),
                    R.drawable.period_blood,"nota dia");


            //Si la fecha_temporal corresponde al mes en el que se encuentra actualmente
            //el calendario que agrege las notas
            String fechatemp = customDateParse.convertirDateToStringMonth(customDateParse.convertirStringToDate(fecha_temporal));
            String fechacalendar = customDateParse.convertirDateToStringMonth(customDateParse.convertirStringToDate(
                    customDateParse.convertirDateToString(CalendarPeriodo.getCurrentPageDate().getTime())));
            if( fechatemp.equals(fechacalendar)){

                try {
                    addPredictDaysInCalendar(myEventDay);
                } catch (OutOfDateRangeException e) {
                    e.printStackTrace();
                }

            }


            String d = fecha_temporal;

            fecha_temporal = customDateParse.convertirDateToString(customDateParse.cambiar_dia(customDateParse.convertirStringToDate(d), 1));

        }
    }

    private void DetallesCicloAlmacenados(DetalleCiclo d){

        String fecha_temporal = d.getFecha_introduccion();
        int icon = 0;

        switch (d.getSeveridad()){
            case "Perdidas":
                icon = R.drawable.perdidas;
                break;

            case "Ligero":
                icon = R.drawable.ligero;
                break;

            case "Medio":
                icon = R.drawable.medio;
                break;

            case "Fuerte":
                icon = R.drawable.desangramiento;
                break;

                default:
                    icon = R.drawable.period_blood;
                    break;

        }

        MyEventDay myEventDay = new MyEventDay(customDateParse.convertirACalendar(fecha_temporal),
                icon,d.getDetalle());

        try {
            addDaysInCalendar(myEventDay);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }


    private String calculateInicioPeriodo(int periodo){

        int periododuracion = periodo * - 1;

        return customDateParse.convertirDateToString(customDateParse.cambiar_dia(customDateParse.convertirStringToDate(
                FinCiclo), periododuracion));
    }

    private String calculateFinCiclo(String UltimoPeriodo, String Cambio){

        return customDateParse.convertirDateToString(customDateParse.cambiar_dia(
                customDateParse.convertirStringToDate(UltimoPeriodo),Integer.parseInt(Cambio)));
    }

    private void addPredictDaysInCalendar(MyEventDay myEventDay ) throws OutOfDateRangeException {
        //Formato que debe poseer la nota al enviarse para agregar al calendario
        //MyEventDay(Calendar day, int imageResource, String note)

        //CalendarPeriodo.setDate(myEventDay.getCalendar());
        mPredictDaysCiclo.add(myEventDay);
        CalendarPeriodo.setEvents(mPredictDaysCiclo);
    }

    private void addDaysInCalendar(MyEventDay myEventDay ) throws OutOfDateRangeException {
        //Formato que debe poseer la nota al enviarse para agregar al calendario
        //MyEventDay(Calendar day, int imageResource, String note)

        //CalendarPeriodo.setDate(myEventDay.getCalendar());
        mRegisteredDaysCiclo.add(myEventDay);
        CalendarPeriodo.setEvents(mRegisteredDaysCiclo);
    }

    private boolean ConsultarCiclos() {

        boolean insertado = false;

        lstCiclos = null;

        lstCiclos = db.getArrayCiclos(
                db.getCursorCiclo(customDateParse.convertirDateToStringMonth_Year(
                        CalendarPeriodo.getCurrentPageDate().getTime()
                ))
        );

        if (lstCiclos == null) {

            lstCiclos = new ArrayList<>();//si no hay datos

        }else{
            insertado = true;
        }

        return insertado;
    }

    private boolean ConsultarDetalleCiclos() {

        boolean insertado = false;

        lstDetalleCiclo = null;

        lstDetalleCiclo = db.getArrayDetalleCiclos(
                db.getCursorDetalleCiclo(customDateParse.convertirDateToStringMonth_Year(
                        CalendarPeriodo.getCurrentPageDate().getTime()
                ))
        );

        if (lstDetalleCiclo == null) {

            lstDetalleCiclo = new ArrayList<>();//si no hay datos

        }else{

            insertado = true;
        }

        return insertado;
    }

    private void CalculatePromedios(){

        promedioCiclos = null;

        promedioCiclos = db.getArrayPromediosCiclos(
                db.getPromediosCiclo()
        ).get(0);

        if (promedioCiclos == null) {

            promedioCiclos = new PromedioCiclos();//si no hay datos

        }

    }

    private void ConsultarUltimoPeriodo(){

        UltimoCiclo = null;

        UltimoCiclo = db.getArrayLastCiclo(
                db.getLastCiclo()
        );

        if (UltimoCiclo == null) {

            UltimoCiclo = new Ciclo();//si no hay datos

        }

    }

}
