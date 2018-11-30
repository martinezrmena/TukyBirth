package usonsonate.com.tukybirth.Comidas;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;

import usonsonate.com.tukybirth.Ejercicios.InformationExercise;
import usonsonate.com.tukybirth.R;

public class DataComida {


    private static String posicion;

    public  DataComida(String posicion){
        this.posicion = posicion;
    }

    public static ArrayList<InformacionComidas> getData(String [] datos, String detils []) {

        ArrayList<InformacionComidas> data = new ArrayList<>();

        int[] images = {
                /*Trimestre 1*/
                R.drawable.trim1_plan1,
                R.drawable.trim1_plan2,

                /*Trimestre 2*/
                R.drawable.trim2_plan1,
                R.drawable.trim2_plan2,

                /*Trimestre 3*/
                R.drawable.trim3_plan1,
                R.drawable.trim3_plan2,

        };

        String[] Semanas = datos;

        /*********************************************************************************/
        if(posicion.equals("0")){

            for (int i = 0; i < 2; i++) {
                InformacionComidas current = new InformacionComidas();
                current.title = Semanas[i];
                current.imageId = images[i];
                current.color = new ColorDrawable(Color.parseColor("#F28131"));
                current.Details = detils[i];
                data.add(current);
            }


        }
        /*********************************************************************************/
        if(posicion.equals("1")){


            for (int i = 2; i < 4; i++) {

                InformacionComidas current = new InformacionComidas();
                current.title = Semanas[i];
                current.imageId = images[i];
                current.color = new ColorDrawable(Color.parseColor("#3F39FC"));
                current.Details = detils[i];
                data.add(current);
            }


        }
        /*********************************************************************************/
        if(posicion.equals("2")){


            for (int i = 4; i < 6; i++) {

                InformacionComidas current = new InformacionComidas();
                current.title = Semanas[i];
                current.imageId = images[i];
                current.color = new ColorDrawable(Color.parseColor("#FB585D"));
                current.Details = detils[i];
                data.add(current);
            }


        }
        /*********************************************************************************/


        return data;
    }
}
