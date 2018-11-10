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

import java.util.Timer;
import java.util.TimerTask;

import usonsonate.com.tukybirth.Threads.ViewPagerCaurosel;

public class InformacionEmbarazo extends AppCompatActivity {

    private CardView Calendario, History;
    private Toolbar toolbar;
    private Transition transition;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private Timer timer;
    private ViewPagerCaurosel viewPagerCaurosel;
    private AppBarLayout appbar;

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



        //ESTABLECEMOS EL CAROUSEL PARA SU MOVIMIENTO AUTOMATICO
        viewPager = findViewById(R.id.viewPager);

        viewPagerCaurosel = new ViewPagerCaurosel(InformacionEmbarazo.this);

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

                    if (value == 39){
                        value = 0;
                    }else {
                        value++;
                    }

                    viewPager.setCurrentItem(value);

                }
            });
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        timer = new Timer();
        timer.scheduleAtFixedRate(new InformacionEmbarazo.MyTimerTask(), 2000,4000);
    }

}
