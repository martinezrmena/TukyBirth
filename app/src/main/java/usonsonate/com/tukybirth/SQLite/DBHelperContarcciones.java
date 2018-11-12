package usonsonate.com.tukybirth.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperContarcciones extends SQLiteOpenHelper {

    private String crearContracciones="create table contracciones(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "duracion VARCHAR(25),"+
            "intervalo VARCHAR(25),"+
            "iniciofin varchar(25)"+
            ")";

    public DBHelperContarcciones(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(crearContracciones);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contracciones");
    }
}
