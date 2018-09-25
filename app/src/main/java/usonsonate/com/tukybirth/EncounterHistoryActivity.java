package usonsonate.com.tukybirth;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.animation.DecelerateInterpolator;

import usonsonate.com.tukybirth.Slides.SlideAdapter;

public class EncounterHistoryActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter_history);

        setTitle("Historia de un encuentro");
        //Para activar y asignar que necesitaremos un botón para regresar a la activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CREAMOS LA TRANSICION DE ENTRADA
        Fade fadeIn = new Fade(Fade.IN);
        fadeIn.setDuration(MainActivity.DURATION_TRANSITION);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        //CREAMOS LA TRANSICION DE SALIDA
        Fade fadeOut = new Fade(Fade.OUT);
        fadeIn.setDuration(MainActivity.DURATION_TRANSITION);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        //OBTENEMOS LA VENTANA ANTERIOR Y ESTABLECEMOS LA TRASICION EN SU LLEGADA
        getWindow().setExitTransition(fadeOut);
        getWindow().setEnterTransition(fadeIn);
        getWindow().setAllowEnterTransitionOverlap(false);

        viewPager = findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);
    }
}
