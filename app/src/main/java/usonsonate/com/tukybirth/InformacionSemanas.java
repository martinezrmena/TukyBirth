package usonsonate.com.tukybirth;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import usonsonate.com.tukybirth.Semanas.*;

public class InformacionSemanas extends AppCompatActivity {

    RecyclerView recyclerView;
    String [] datos;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        //para cerrar utilizamos finishAfterTransition();

        setContentView(R.layout.activity_informacion_semanas);

        setTitle("INFORMACIÓN POR SEMANA");
        //Para activar y asignar que necesitaremos un botón para regresar a la activity anterior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializar variables
        datos = getResources().getStringArray(R.array.semanas_array);
        recyclerView = findViewById(R.id.recycleView);
        adapter = new CustomAdapter(this, Data.getData(datos));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Vertical Orientation By Default

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.linearViewHorizontal:
                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLinearLayoutManagerHorizontal);
                break;

            case R.id.linearViewVertical:
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3); // (Context context, int spanCount)
                recyclerView.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager mStaggeredHorizontalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
                recyclerView.setLayoutManager(mStaggeredHorizontalLayoutManager);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
                recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
