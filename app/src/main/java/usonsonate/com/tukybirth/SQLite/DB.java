package usonsonate.com.tukybirth.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class DB {
    private DBHelper dbHelper;
    public DB(Context context) {
        //definimos el nombre de la BD
        dbHelper = new DBHelper(context, "BD_Tuky", null, 1);
    }

    //region Notas
    public Cursor getCursorNota(String mes){

        Log.d("CONSULTA", "select * from notas where strftime('%m', fecha) = '"+mes+"'");
        return dbHelper.getReadableDatabase().rawQuery(
                "select * from notas where strftime('%m', fecha) = '"+mes+"'",null);
    }

    public ArrayList<Notas> getArrayNotas(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<Notas> lstNotas = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstNotas.add(new Notas(
                        cursor.getString(0),//id_nota
                        cursor.getString(1),//fechanota
                        cursor.getString(2)//nota
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstNotas;
        }
        return null;//si no entro en el if
    }

    public boolean guardar_O_ActualizarNotas(Notas notas) {
        ContentValues initialValues = new ContentValues();
        Log.d("Notas","id_nota "+notas.getId());
        Log.d("Notas","fecha "+notas.getFechanota());
        Log.d("Notas","nota "+notas.getNota());

        if(!notas.getId().isEmpty())
            initialValues.put("id_nota", Integer.parseInt(notas.getId()));
        initialValues.put("fecha",notas.getFechanota());
        initialValues.put("nota",notas.getNota());

        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "notas",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }

    public void  borrarNota(String id){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from notas where id_nota='%s'",id));
    }
    //endregion

    //region Personas
    public Cursor getCursorPersona(){

        return dbHelper.getReadableDatabase().rawQuery(
                "select * from personas",null);
    }

    public ArrayList<Personas> getArrayPersonas(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<Personas> lstPersonas = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstPersonas.add(new Personas(
                        cursor.getString(0),//id_persona
                        cursor.getString(1),//nombre
                        cursor.getString(2),//password
                        cursor.getString(3),//periodo
                        cursor.getString(4),//PMS
                        cursor.getString(5),//Ciclo
                        cursor.getString(6),//Ultimo_periodo
                        cursor.getString(7),//Cumpleaños
                        cursor.getString(8)//FechaIngreso
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstPersonas;
        }
        return null;//si no entro en el if
    }

    public boolean guardar_O_ActualizarPersonas(Personas personas) {
        ContentValues initialValues = new ContentValues();
        Log.d("Personas","id_persona "+personas.getId());
        Log.d("Personas","nombre "+personas.getNombre());
        Log.d("Personas","password "+personas.getPassword());
        Log.d("Personas","periodo "+personas.getPeriodo());
        Log.d("Personas","pms "+personas.getPMS());
        Log.d("Personas","ciclo "+personas.getCiclo());
        Log.d("Personas","ultimo_periodo "+personas.getUltimo_periodo());
        Log.d("Personas","cumpleaños "+personas.getCumpleaños());
        Log.d("Personas","fecha_ingreso "+personas.getFechaIngreso());

        if(!personas.getId().isEmpty())
            initialValues.put("id_persona", Integer.parseInt(personas.getId()));
        initialValues.put("nombre",personas.getNombre());
        initialValues.put("password",personas.getPassword());
        initialValues.put("periodo",personas.getPeriodo());
        initialValues.put("pms",personas.getPMS());
        initialValues.put("ciclo",personas.getCiclo());
        initialValues.put("ultimo_periodo",personas.getUltimo_periodo());
        initialValues.put("cumpleaños",personas.getCumpleaños());
        initialValues.put("fecha_ingreso",personas.getFechaIngreso());

        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "personas",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }

    public void  borrarPersona(String id){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from personas where id_persona='%s'",id));
    }
    //endregion

}
