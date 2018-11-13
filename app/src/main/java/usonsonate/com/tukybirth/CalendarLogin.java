package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import usonsonate.com.tukybirth.Calendar.MyEventDay;
import usonsonate.com.tukybirth.SQLite.DB;
import usonsonate.com.tukybirth.SQLite.Notas;
import usonsonate.com.tukybirth.SQLite.Personas;

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
    private List<EventDay> mEventDays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_login);

        persona = (Personas) getIntent().getExtras().getSerializable("PERSONA");
        CalendarPeriodo = findViewById(R.id.clnPeriodo);
        btnAgregar = findViewById(R.id.fabtnPeriodo);
        customDateParse = new CustomDateParse();

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

        FinCiclo =  calculateFinCiclo("2018-11-01", persona.getCiclo());
        InicioPeriodo = calculateInicioPeriodo();

        Calendar fechatemporal = Calendar.getInstance();
        final CalendarView datePicker = CalendarPeriodo;

        fechatemporal.setTime(customDateParse.convertirStringToDate(InicioPeriodo));

        try {
            datePicker.setDate(fechatemporal);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < Integer.parseInt(persona.getPeriodo()); i++){

            try {
                addNoteinCalendar( new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.note, "nota del dia"));
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }

            fechatemporal.setTime(customDateParse.cambiar_dia(
                    customDateParse.convertirStringToDate(InicioPeriodo), (i + 1)));

            try {
                datePicker.setDate(fechatemporal);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
        }


    }

    private String calculateInicioPeriodo(){

        int periododuracion = Integer.parseInt(persona.getPeriodo()) * - 1;

        return customDateParse.convertirDateToString(customDateParse.cambiar_dia(customDateParse.convertirStringToDate(
                FinCiclo), periododuracion));
    }

    private String calculateFinCiclo(String UltimoPeriodo, String Cambio){

        return customDateParse.convertirDateToString(customDateParse.cambiar_dia(
                customDateParse.convertirStringToDate(UltimoPeriodo),Integer.parseInt(Cambio)));
    }

    private void addNoteinCalendar(MyEventDay myEventDay ) throws OutOfDateRangeException {
        //Formato que debe poseer la nota al enviarse para agregar al calendario
        //MyEventDay(Calendar day, int imageResource, String note)

        //mCalendarView.setDate(myEventDay.getCalendar());
        mEventDays.add(myEventDay);
        CalendarPeriodo.setEvents(mEventDays);
    }

}
