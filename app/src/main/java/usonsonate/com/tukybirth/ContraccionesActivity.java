package usonsonate.com.tukybirth;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
    private int cont=2;
    private LottieAnimationView animationViewlotiee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracciones);

        db = new DB_C(ContraccionesActivity.this);

        btnContraccion = findViewById(R.id.btnContraccion);
        crono = findViewById(R.id.Cronometro);
        crono2 = findViewById(R.id.cronometro2);

        animationViewlotiee  = findViewById(R.id.animation_view);

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

    public void cuadro_dialogio(){
        AlertDialog.Builder builder = new   AlertDialog.Builder(this);
        builder.setTitle("Alerta!");
        builder.setMessage("Desea borrar el registro de contracciones?");
        builder.setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.borrarContracciones();
                Toast.makeText(ContraccionesActivity.this, "Registro de contracciones eliminado!!", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }
        });
        builder.setNegativeButton("cancelar",null);
        Dialog dialog = builder.create();
        dialog.show();
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
            case R.id.btnBorrarCon:{
                cuadro_dialogio();
                return  true;
            }
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void FillListContraccioones(){
        //QuickSort qr=new QuickSort();
        //qr.sort(db.getArrayContracciones(db.getCursorContacciones()),0,db.getArrayContracciones(db.getCursorContacciones()).size()-1);
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
            //animacion
            animationViewlotiee.loop(true);
            animationViewlotiee.playAnimation();
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

            //animacion
            animationViewlotiee.loop(false);

            if(!bandera){
                Contracciones lista = new Contracciones("1",String.valueOf(((int)pauseOffset)/1000)+"s","---",hora+":"+minutos+" - "+hora+":"+minutos);
                db.guardar_O_ActualizarNotas(lista);
                lstcontraciones.add(lista);
                adaptadorcronometro.notifyDataSetChanged();
                bandera = true;
            }else{

                Contracciones lista = new Contracciones(String.valueOf(cont),String.valueOf(((int)pauseOffset)/1000)+"s",String.valueOf(inte[contador-1])+"s",hora+":"+minutos+" - "+hora+":"+minutos);
                cont++;
                db.guardar_O_ActualizarNotas(lista);
                lstcontraciones.add(lista);
                adaptadorcronometro.notifyDataSetChanged();

            }

        }

    }


    /******************************************************************************************************************/
    /*CLASE ALGORITMO QUITKSORT*/

    class QuickSort
    {
        int partition(ArrayList<Contracciones> array, int menor, int mayor)
        {
            int pivot =Integer.parseInt(array.get(mayor).duracion.toString());
            int i = (menor-1);
            for (int j=menor; j<mayor; j++)
            {
                if (Integer.parseInt(array.get(j).duracion.toString()) >= pivot)  //<-- aqui podemos cambiar si queremos de mayor a menor(>=) o si queremos de menor a mayor(<=)
                {
                    i++;
                    int temp = Integer.parseInt(array.get(i).duracion.toString());
                    //array[i] = array[j];
                    array.get(i).setDuracion(array.get(j).duracion.toString());
                    //array[j] = temp;
                    array.get(j).setDuracion(String.valueOf(temp));


                }
            }
            //int temp = array[i+1];
            int temp = Integer.parseInt(array.get(i+1).duracion.toString());
            //array[i+1] = array[mayor];
            array.get(i+1).setDuracion(array.get(mayor).duracion.toString());
            //array[mayor] = temp;
            array.get(mayor).setDuracion(String.valueOf(temp));

            return i+1;
        }


        void sort(ArrayList<Contracciones> array, int menor, int mayor)
        {
            if (menor < mayor)
            {
                int pi = partition(array, menor, mayor);
                sort(array, menor, pi-1);
                sort(array, pi+1, mayor);
            }
        }
    }


/******************************************************************************************************************/


}
