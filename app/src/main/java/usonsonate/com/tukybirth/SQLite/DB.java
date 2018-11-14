package usonsonate.com.tukybirth.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //region Ciclos
    public Cursor getCursorCiclo(String date){

        Log.d("CONSULTA", "select * from ciclos where strftime('%m', fecha_inicio) = '"+date+"'");
        return dbHelper.getReadableDatabase().rawQuery(
                "select * from ciclos where strftime('%m-%Y', fecha_inicio)",null);
    }

    public ArrayList<Ciclo> getArrayCiclos(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<Ciclo> lstCiclo = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstCiclo.add(new Ciclo(
                        cursor.getString(0),//id_ciclo
                        cursor.getString(1),//duracion_ciclo
                        cursor.getString(2),//duracion_periodo
                        cursor.getString(3),//fecha_inicio
                        cursor.getString(4),//fecha_fin
                        cursor.getString(5)//estado
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstCiclo;
        }
        return null;//si no entro en el if
    }

    public Cursor getLastCiclo(){

        return dbHelper.getReadableDatabase().rawQuery(
                "select * from ciclos where id_ciclo = (select max(id_ciclo) from ciclos); ",null);
    }

    public Ciclo getArrayLastCiclo(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        Ciclo ciclo = new Ciclo();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                ciclo = new Ciclo(
                        cursor.getString(0),//id_ciclo
                        cursor.getString(1),//duracion_ciclo
                        cursor.getString(2),//duracion_periodo
                        cursor.getString(3),//fecha_inicio
                        cursor.getString(4),//fecha_fin
                        cursor.getString(5)//estado
                );//se agregan a la lista
            }while (cursor.moveToNext());
            return ciclo;
        }
        return null;//si no entro en el if
    }

    public Cursor getPromediosCiclo(){

        return dbHelper.getReadableDatabase().rawQuery(
                "select round(avg(duracion_periodo), 0) as duracion_periodo, round(avg(duracion_ciclo),0) as duracion_ciclo, count(*) from ciclos",null);
    }

    public List <PromedioCiclos> getArrayPromediosCiclos(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        List <PromedioCiclos> promedioCiclos = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                promedioCiclos.add(new PromedioCiclos(
                        cursor.getString(0),//DURACION_PERIODO
                        cursor.getString(1),//DURACION_CICLO
                        cursor.getString(2))//CANTIDAD_CICLOS
                );//se agregan a la lista
            }while (cursor.moveToNext());
            return promedioCiclos;
        }
        return null;//si no entro en el if
    }

    public boolean guardar_O_ActualizarCiclos(Ciclo ciclo) {
        ContentValues initialValues = new ContentValues();
        Log.d("Ciclos","id_ciclo "+ciclo.getId_ciclo());
        Log.d("Ciclos","duracion_ciclo "+ciclo.getDuracion_ciclo());
        Log.d("Ciclos","duracion_periodo "+ciclo.getDuracion_periodo());
        Log.d("Ciclos","fecha_inicio "+String.format(ciclo.getFecha_inicio(),"{0:yyyy'-'MM'-'dd}"));
        Log.d("Ciclos","fecha_fin "+ciclo.getFecha_fin());
        Log.d("Ciclos","estado "+ciclo.getEstado());

        if(!ciclo.getId_ciclo().isEmpty())
            initialValues.put("id_ciclo", Integer.parseInt(ciclo.getId_ciclo()));
            initialValues.put("duracion_ciclo", Integer.parseInt(ciclo.getDuracion_ciclo()));
            initialValues.put("duracion_periodo",Integer.parseInt(ciclo.getDuracion_periodo()));
            initialValues.put("fecha_inicio", String.format(ciclo.getFecha_inicio(),"{0:yyyy'-'MM'-'dd}"));
            initialValues.put("fecha_fin",ciclo.getFecha_fin());
            initialValues.put("estado",ciclo.getEstado());


        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "ciclos",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }

    public void  borrarCiclo(String id){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from ciclos where id_ciclo='%s'",id));
    }
    //endregion

    //region Detalle ciclos
    public Cursor getCursorDetalleCiclo(String date){

        Log.d("CONSULTA", "select * from ciclos where strftime('%m-%Y', fecha_introduccion) = '"+date+"'");
        return dbHelper.getReadableDatabase().rawQuery(
                "select * from detalleciclos where strftime('%m-%Y', fecha_introduccion) = '"+date+"'",null);
    }

    public ArrayList<DetalleCiclo> getArrayDetalleCiclos(Cursor cursor){
        cursor.moveToFirst();//moverse al principio
        ArrayList<DetalleCiclo> lstDetalleCiclo = new ArrayList<>();
        if(cursor != null && cursor.getCount() > 0){//si hay datos
            do{
                lstDetalleCiclo.add(new DetalleCiclo(
                        cursor.getString(0),//id_detalle
                        cursor.getString(1),//id_ciclo
                        cursor.getString(2),//fecha_introduccion
                        cursor.getString(3),//severidad
                        cursor.getString(4)//detalle
                ));//se agregan a la lista
            }while (cursor.moveToNext());
            return lstDetalleCiclo;
        }
        return null;//si no entro en el if
    }

    public boolean guardar_O_ActualizarDetalleCiclos(DetalleCiclo detalleciclo) {
        ContentValues initialValues = new ContentValues();
        Log.d("Ciclos","id_detalle "+detalleciclo.getId_detalle());
        Log.d("Ciclos","id_ciclo "+detalleciclo.getId_ciclo());
        Log.d("Ciclos","fecha_introduccion "+detalleciclo.getFecha_introduccion());
        Log.d("Ciclos","severidad "+detalleciclo.getSeveridad());
        Log.d("Ciclos","detalle "+detalleciclo.getDetalle());

        if(!detalleciclo.getId_ciclo().isEmpty())
            initialValues.put("id_detalle", Integer.parseInt(detalleciclo.getId_detalle()));
            initialValues.put("id_ciclo", Integer.parseInt(detalleciclo.getId_ciclo()));
            initialValues.put("fecha_introduccion",detalleciclo.getFecha_introduccion());
            initialValues.put("severidad",detalleciclo.getSeveridad());
            initialValues.put("detalle",detalleciclo.getDetalle());


        int id = (int) dbHelper.getWritableDatabase().insertWithOnConflict(
                "detalleciclos",
                null,
                initialValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        return id>0;
    }

    public void  borrarDetalleCiclo(String id){
        dbHelper.getWritableDatabase().execSQL(String.format("delete from detalleciclos where id_ciclo='%s'",id));
    }
    //endregion


}
