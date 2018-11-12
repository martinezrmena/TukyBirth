package usonsonate.com.tukybirth.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DB_C {

    private DBHelperContarcciones dbhelper;
    public  DB_C(Context context) {
        //definimos el nombre de la BD
        dbhelper = new DBHelperContarcciones(context, "BD_contraccioones", null, 1);
    }


public Cursor getCursorContacciones(){

   return  dbhelper.getReadableDatabase().rawQuery("select * from contracciones",null);
 }



    public ArrayList<Contracciones> getArrayContracciones(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<Contracciones> lstContracciones = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstContracciones.add(new Contracciones(
                        cursor.getString(0),//id_contraccion
                        cursor.getString(1),//duracion
                        cursor.getString(2),//intervalo
                        cursor.getString(3)//iniciofin
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstContracciones;
        }
        return null;//si no entro en el if
    }


    public boolean guardar_O_ActualizarNotas(Contracciones contra) {
        ContentValues initialValues = new ContentValues();
      //  Log.d("Notas","id_nota "+notas.getId());
      //  Log.d("Notas","fecha "+notas.getFechanota());
      //  Log.d("Notas","nota "+notas.getNota());

        if(!contra.getId().isEmpty())
              initialValues.put("id", Integer.parseInt(contra.getId()));
        initialValues.put("duracion",contra.getDuracion());
        initialValues.put("intervalo",contra.getIntervalo());
        initialValues.put("iniciofin",contra.getIniciofin());

        int id = (int) dbhelper.getWritableDatabase().insertWithOnConflict(
                "contracciones",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }




    public void  borrarContracciones(){
        dbhelper.getWritableDatabase().execSQL(String.format("delete from contracciones"));
    }


}
