package net.allacalle.android.prototipourgencyassist1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FormulasSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateFormula = "CREATE TABLE Formulas ( parametrosFormula TEXT, tipoFormula TEXT, nombreCompleto TEXT, abreviatura TEXT)";
    String sqlCreatePrueba = "CREATE TABLE Prueba (codigo INTEGER , nombre TEXT)";


    public FormulasSQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateFormula);
        db.execSQL(sqlCreatePrueba);
        //Este comentario deberia borrarse
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Formula");
        db.execSQL("DROP TABLE IF EXISTS Prueba");



        onCreate(db);

    }
}