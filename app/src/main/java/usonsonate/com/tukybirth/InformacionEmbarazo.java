package usonsonate.com.tukybirth;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import usonsonate.com.tukybirth.Threads.ViewPagerCaurosel;

public class InformacionEmbarazo extends AppCompatActivity {

    private CardView Calendario, History,ejercicios,comidas, referencias;
    private Toolbar toolbar;
    private Transition transition;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private Timer timer;
    private ViewPagerCaurosel viewPagerCaurosel;
    private AppBarLayout appbar;
    private int[] images = {
            R.drawable.semana_1,
            R.drawable.semana_2,
            R.drawable.semana_3,
            R.drawable.semana_4,
            R.drawable.semana_5,
            R.drawable.semana_6,
            R.drawable.semana_7,
            R.drawable.semana_8,
            R.drawable.semana_9,
            R.drawable.semana_10,
            R.drawable.semana_11,
            R.drawable.semana_12,
            R.drawable.semana_13,
            R.drawable.semana_14,
            R.drawable.semana_15,
            R.drawable.wekk_16,
            R.drawable.week_17,
            R.drawable.week_18,
            R.drawable.week_19,
            R.drawable.week_20,
            R.drawable.week_21,
            R.drawable.week_22,
            R.drawable.week_23,
            R.drawable.week_24,
            R.drawable.week_25,
            R.drawable.week_26,
            R.drawable.week_27,
            R.drawable.week_28,
            R.drawable.week_29,
            R.drawable.week_30,
            R.drawable.week_31,
            R.drawable.week_32,
            R.drawable.week_33,
            R.drawable.week_34,
            R.drawable.week_35,
            R.drawable.week_36,
            R.drawable.week_37,
            R.drawable.week_38,
            R.drawable.week_39,
            R.drawable.week_39
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_embarazo);

        setTitle("INFORMACIÓN");
        //Para activar y asignar que necesitaremos un botón para regresar a la activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        Calendario = findViewById(R.id.btnactivitycalendar);
        History = findViewById(R.id.btnactivityHistory);
        ejercicios = findViewById(R.id.btnactivityEjercicio);
        comidas = findViewById(R.id.btnactivityComidas);
        referencias = findViewById(R.id.btnReferenciasButton);

        ejercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(transition);
                }
                Context context = InformacionEmbarazo.this;

                Intent detail = new Intent(context.getApplicationContext(), ExercisesActivity.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(InformacionEmbarazo.this).toBundle());
            }
        });

        comidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(transition);
                }
                Context context = InformacionEmbarazo.this;

                Intent detail = new Intent(InformacionEmbarazo.this, ComidasActivity.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(InformacionEmbarazo.this).toBundle());
            }
        });

        Calendario.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v)
            {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(transition);
                }
                Context context = InformacionEmbarazo.this;

                Intent detail = new Intent(InformacionEmbarazo.this, InformacionSemanas.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(InformacionEmbarazo.this).toBundle());
            }
        });

        History.setOnClickListener(new View.OnClickListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void onClick(View v)
            {
                transition = new Fade(Fade.OUT);
                transition.setDuration(MainActivity.DURATION_TRANSITION);
                transition.setInterpolator(new DecelerateInterpolator());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(transition);
                }
                Context context = InformacionEmbarazo.this;

                Intent detail = new Intent(context.getApplicationContext(), EncounterHistoryActivity.class);
                context.startActivity(detail,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(InformacionEmbarazo.this).toBundle());
            }
        });

        referencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReferencesActivity.class);
                startActivity(intent);
            }
        });



        //ESTABLECEMOS EL CAROUSEL PARA SU MOVIMIENTO AUTOMATICO
        viewPager = findViewById(R.id.viewPager);

        viewPagerCaurosel = new ViewPagerCaurosel(InformacionEmbarazo.this, images);

        viewPager.setAdapter(viewPagerCaurosel);

        timer = new Timer();
        timer.scheduleAtFixedRate(new InformacionEmbarazo.MyTimerTask(), 2000,4000);

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
