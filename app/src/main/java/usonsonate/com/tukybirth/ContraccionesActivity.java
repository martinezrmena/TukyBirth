package usonsonate.com.tukybirth;

import android.content.ClipData;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import usonsonate.com.tukybirth.Cronometro.AdapterCronometro;
import usonsonate.com.tukybirth.Cronometro.LstContracciones;
import usonsonate.com.tukybirth.SQLite.Contracciones;
import usonsonate.com.tukybirth.SQLite.DB_C;

public class ContraccionesActivity extends AppCompatActivity {

    private Chronometer crono,crono2;
    private Button btnContraccion;
    private long pauseOffset,intervalo;
    private boolean running=false,reset=false,bandera=false;
    private AdapterCronometro adaptadorcronometro;
    private ArrayList<Contracciones> lstcontraciones;
    private String prueba;
    private int hora, minutos, segundos,contador=0;
    private String[] inte = new String[25];
    private DB_C db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracciones);

        db = new DB_C(ContraccionesActivity.this);

        btnContraccion = findViewById(R.id.btnContraccion);
        crono = findViewById(R.id.Cronometro);
        crono2 = findViewById(R.id.cronometro2);

        lstcontraciones = new ArrayList<>();
        adaptadorcronometro = new AdapterCronometro(ContraccionesActivity.this,lstcontraciones);
        ListView viewLista = findViewById(R.id.lstContracciones);
        viewLista.setAdapter(adaptadorcronometro);

        prueba = String.valueOf(db.getArrayContracciones(db.getCursorContacciones()));

       if(!prueba.equals("null")){
           FillListContraccioones();
       }


        btnContraccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesoCronometro();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_eliminar_contraccion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnBorrarCon:
                db.borrarContracciones();
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void FillListContraccioones(){
        for (final Contracciones c:db.getArrayContracciones(db.getCursorContacciones())){
            lstcontraciones.add(c);
            adaptadorcronometro.notifyDataSetChanged();
        }
    }

    public void procesoCronometro(){
        if(reset){
            /*resetro*/
            crono2.stop();
            intervalo = SystemClock.elapsedRealtime() - crono2.getBase();
            inte[contador] = String.valueOf(((int)intervalo)/1000);
            contador++;
            crono2.setBase(SystemClock.elapsedRealtime());
            crono.setBase(SystemClock.elapsedRealtime());
            pauseOffset =0;
            intervalo =0;
            reset= false;
        }
        if(!running){
            /*inicio*/
            btnContraccion.setText("fin de la contraccion");
            crono2.setBase(SystemClock.elapsedRealtime() - intervalo);
            crono.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            crono.start();
            crono2.start();
            running = true;
        }else{
            /*pause*/
            btnContraccion.setText("inicio de la contraccion");
            crono.stop();
            pauseOffset = SystemClock.elapsedRealtime() - crono.getBase();
            running = false;
            reset = true;
            Calendar calendario = new GregorianCalendar();
            hora =calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            if(!bandera){
                Contracciones lista = new Contracciones("",String.valueOf(((int)pauseOffset)/1000)+"s","---",hora+":"+minutos+" - "+hora+":"+minutos);
                db.guardar_O_ActualizarNotas(lista);
                lstcontraciones.add(lista);
                adaptadorcronometro.notifyDataSetChanged();
                bandera = true;
            }else{
                Contracciones lista = new Contracciones("",String.valueOf(((int)pauseOffset)/1000)+"s",String.valueOf(inte[contador-1])+"s",hora+":"+minutos+" - "+hora+":"+minutos);
                db.guardar_O_ActualizarNotas(lista);
                lstcontraciones.add(lista);
                adaptadorcronometro.notifyDataSetChanged();

            }



        }

    }


}
