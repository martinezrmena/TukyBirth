package usonsonate.com.tukybirth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import usonsonate.com.tukybirth.Adaptadores.AdaptadorDetalleCiclo;
import usonsonate.com.tukybirth.Calendar.DetalleDia;

public class ListDetalleActivity extends AppCompatActivity {

    private ListView lstDetalles;
    private ArrayList<DetalleDia> listaDetalleDias;
    private AdaptadorDetalleCiclo adaptadorDetalleCiclo;

    //region Arrays
    private String[] titulos={
            "DOLORES",
            "PERDIDAS",
            "LIGERO",
            "MEDIO",
            "FUERTE",
            "EL CICLO TERMINO"
    };

    private String[] detalles={
            "Puede experimentar dolores palpitantes o cólicos en la parte baja del abdomen, sensibilidad e hinchazón de los pechos, dolor de ovarios y calambres abdominales, dolor en los riñones, dolor de cabeza, aparición de acné, cambios repentinos de humor, cansancio y debilidad, alteraciones gastrointestinales como náuseas.",
            "Casi imperceptibles, únicamente visibles con revisión meticulosa, es normal visualizar una tonalidad rosa, para tratar de combatir estas molestias es aconsejable seguir una dieta equilibrada y saludable, beber dos litros de agua al día, evitar la sal, disminuir el nivel de estrés, ansiedad y, en el caso de ser necesario, tomar algún analgésico.",
            "Son sangrados leves, lo importante es entender que cada cuerpo es diferente, por ende, los periodos menstruales también son diferentes. Lo que es 'normal' para una persona puede no serlo para otra, y puede cambiar con el tiempo. Sólo de 10% a 15% de las mujeres cumplen la duración promedio de 28 días.",
            "Son sangrados normales donde el periodo dura entre tres y siete días, la toalla se puede cambiar cada 4 horas aproximadamente y sus síntomas son leves o sin presencia, es común que ocurran cambios en el sangrado menstrual, dependerá de los niveles de las hormonas estrógeno y progesterona.",
            "Su sangrado empapa totalmente la toalla sanitaria o tampón en menos de dos horas, su ropa se mancha frecuentemente con sangre que la sobrepasa, su sangrado interfiere con que usted realice sus actividades diarias, presenta coágulos grandes (de más de una pulgada), es recomendable visitar al médico.",
            "El ciclo finaliza justo antes del sangrado menstrual, a menos que haya fecundación (fase inicial del embarazo), es decir, que el inicio del periodo marca un nuevo ciclo y por consiguiente el día anterior se convierte automáticamente en el último día del ciclo anterior."
    };

    private int[] imgIcon={
            R.drawable.sick_girl,
            R.drawable.perdidas,
            R.drawable.ligero,
            R.drawable.medio,
            R.drawable.desangramiento,
            R.drawable.finish_period

    };

    private int[] imgBackground={
            R.drawable.sick_girl_background,
            R.drawable.sos_background,
            R.drawable.fight_background,
            R.drawable.fire,
            R.drawable.desangrarse_background,
            R.drawable.happiness_background

    };
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detalle);

        lstDetalles = findViewById(R.id.lstDetallesCalendar);
        listaDetalleDias = new ArrayList<>();
        FillListDetalles();
        adaptadorDetalleCiclo = new AdaptadorDetalleCiclo(this, listaDetalleDias);

        lstDetalles.setAdapter(adaptadorDetalleCiclo);
        adaptadorDetalleCiclo.notifyDataSetChanged();

    }

    private void FillListDetalles(){
        int contador = 5;

        for (int i = 0; i <= 5; i++){
            listaDetalleDias.add(new DetalleDia(
                    imgBackground[i],
                    imgIcon[i],
                    titulos[i],
                    detalles[i]
            ));
        }

    }

}
