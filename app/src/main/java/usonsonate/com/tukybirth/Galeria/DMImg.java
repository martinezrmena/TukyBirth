package usonsonate.com.tukybirth.Galeria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import usonsonate.com.tukybirth.SQLite.DBHelperContarcciones;

public class DMImg {
    private DBHelperContarcciones dbhelper;
    public  DMImg(Context context) {
        //definimos el nombre de la BD
        dbhelper = new DBHelperContarcciones(context, "BD_nombressIMG", null, 1);
    }


    public Cursor getCursorNombreIMG(){
        return  dbhelper.getReadableDatabase().rawQuery("select * from nombresimg",null);
    }



    public ArrayList<IMG> getArrayNombreIMG(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<IMG> lstNombreIMG = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstNombreIMG.add(new IMG(
                        cursor.getString(0),//id_contraccion
                        cursor.getString(1)//duracion
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstNombreIMG;
        }
        return null;//si no entro en el if
    }


    public boolean guardar_O_ActualizarNotas(IMG img) {
        ContentValues initialValues = new ContentValues();
        if(!img.getId().isEmpty())
            initialValues.put("id", Integer.parseInt(img.getId()));
        initialValues.put("nombreImg",img.getNombreImg());

        int id = (int) dbhelper.getWritableDatabase().insertWithOnConflict(
                "nombresimg",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }




    public void  borrarContracciones(){
        dbhelper.getWritableDatabase().execSQL(String.format("delete from nombresimg"));
    }
}
