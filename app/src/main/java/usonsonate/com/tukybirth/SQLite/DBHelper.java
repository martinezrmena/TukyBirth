package usonsonate.com.tukybirth.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private String crearNotas="create table notas"+
            "("+
            "id_nota INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "fecha DATE,"+
            "nota TEXT"+
            ")";

    private String crearPersonas = "create table personas"+
            "("+
            "id_persona INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre VARCHAR(150),"+
            "password VARCHAR(150),"+
            "periodo INTEGER,"+
            "pms INTEGER,"+
            "ciclo INTEGER,"+
            "ultimo_periodo DATE,"+
            "cumpleaños DATE,"+
            "fecha_ingreso DATE"+
            ")";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearNotas);
        db.execSQL(crearPersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS notas");
        db.execSQL("DROP TABLE IF EXISTS personas");
    }
}
