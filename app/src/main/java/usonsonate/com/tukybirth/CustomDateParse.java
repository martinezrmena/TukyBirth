package usonsonate.com.tukybirth;

import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomDateParse {

    public CustomDateParse() {
    }


    public Date convertirStringToDate(String fecha){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public Date cambiar_dia(Date fecha, int cambio){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, cambio);  // numero de meses a añadir, o restar en caso de días<0
        fecha = calendar.getTime();
        return fecha;

    }

    public String convertirDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(date);

        return fechaComoCadena;
    }

    public String convertirDateToStringMonth(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String fechaComoCadena = sdf.format(date);

        return fechaComoCadena;
    }


    public String convertirDateToStringMonth_Year(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        String fechaComoCadena = sdf.format(date);

        return fechaComoCadena;
    }

    public Date cambiar_mes(Date fecha, int cambio){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, cambio);  // numero de meses a añadir, o restar en caso de días<0
        fecha = calendar.getTime();
        return fecha;

    }

    public Calendar convertirACalendar(String fecha){
        String[] fechArray = fecha.split("-");


        int anio = Integer.parseInt(fechArray[0]);
        int mes =  Integer.parseInt(fechArray[1]) - 1;
        int dia =  Integer.parseInt(fechArray[2]);

        /*
         *
         * Al mes lo resto 1 (-1) ya que el formato de Calendar el mes empieza en 0
         * Enero=0, Febrero=1, Marzo=2, ... , Diciembre=11
         * De lo contrario Diciembre (12) no funcionaria
         *
         * */

        Calendar c1 = new GregorianCalendar(anio, mes, dia);

        return c1;
    }

    public String FormatSQLite(String date){
        Calendar cInicio = convertirACalendar(date);
        String FechaInicio = convertirDateToString(cInicio.getTime());

        return FechaInicio;
    }

    public String FormatSQLiteCambiarDia(String date, int cambio){

        String newDate =  FormatSQLite(convertirDateToString(cambiar_dia(convertirStringToDate(date), cambio)));

        return newDate;

    }

    public String Diferencia_Dias_Fechas(String fecha_inicio, String fecha_fin) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaInicial= dateFormat.parse(fecha_inicio);
        Date fechaFinal= dateFormat.parse(fecha_fin);

        int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);

        return String.valueOf(dias);

    }

    public boolean ValidarUnaFechaEntreDos(String FechaInicio, String FechaFin, String FechaValidar){
        Date FechaStart = convertirStringToDate(FechaInicio);
        Date FechaEnd = convertirStringToDate(FechaFin);
        Date FechaValidate = convertirStringToDate(FechaValidar);
        boolean EntreFechas;

        if (FechaValidate.after(FechaStart) && FechaValidate.before(FechaEnd)){

            EntreFechas = true;

        }else {

            EntreFechas = false;

        }

        return EntreFechas;
    }


}
