package usonsonate.com.tukybirth;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import usonsonate.com.tukybirth.Threads.ViewPagerCaurosel;

public class HerramientasActivity extends AppCompatActivity {

    private CardView btncronometro,btnnnotas, btnReferencias;
    private Toolbar toolbar;
    private Transition transition;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private Timer timer;
    private ViewPagerCaurosel viewPagerCaurosel;
    private AppBarLayout appbar;
    private int[] images = {
            R.drawable.tool1,
            R.drawable.tool2,
            R.drawable.tool3,
            R.drawable.tool4,
            R.drawable.tool5,
            R.drawable.tool6
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas);

        btnnnotas = findViewById(R.id.btnnotas);
        btncronometro = findViewById(R.id.btncronometro);
        btnReferencias = findViewById(R.id.btnReferencias);

        setTitle("Utilidades");

        //Para activar y asignar que necesitaremos un botón para regresar a la activity anterior
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //transicion inversa a //
        //CREAMOS LA TRANSICION
        Fade fadeIn = new Fade(Fade.IN);
        fadeIn.setDuration(MainActivity.DURATION_TRANSITION);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        //ESTABLECEMOS LA TRANSICION DE REGRESO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setReenterTransition(fadeIn);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setAllowEnterTransitionOverlap(false);
        }

        //INICIALIZAMOS LAS VARIABLES
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        appbar = findViewById(R.id.appbar);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Se cumple que sea Landscape entonces llamo a mis controles
            appbar.setExpanded(false);
        }

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

        btnReferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReferencesActivity.class);
                startActivity(intent);
            }
        });

        //ESTABLECEMOS EL CAROUSEL PARA SU MOVIMIENTO AUTOMATICO
        viewPager = findViewById(R.id.viewPager);

        viewPagerCaurosel = new ViewPagerCaurosel(HerramientasActivity.this, images);

        viewPager.setAdapter(viewPagerCaurosel);

        timer = new Timer();
        timer.scheduleAtFixedRate(new HerramientasActivity.MyTimerTask(), 2000,4000);

    }


    public class MyTimerTask extends TimerTask {
        @Override
        public void run(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Integer value = viewPager.getCurrentItem();

                    if (value == (viewPagerCaurosel.getCount() - 1)){
                        value = 0;
                    }else {
                        value++;
                    }

                    if(value >= 0 && value<= (viewPagerCaurosel.getCount() - 1)){
                        viewPager.setCurrentItem(value);
                    }


                }
            });
        }
    }

}

