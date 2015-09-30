package net.allacalle.android.prototipourgencyassist1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {
    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DBPrueba", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();


        //Si hemos abierto correctamente la base de datos


        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            /*
            for(int i=1; i<=5; i++)
            {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario" + i;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Prueba (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre +"')");
            }

            //Cerramos la base de datos
            */


            //Asi le ponemos el nombre a un campo
            //txtNombre = (TextView)findViewById(R.id.TxtNombre);
            //txtNombre.setText("Hola caracola ");


            Cursor c = db.rawQuery(" SELECT  COUNT(*) FROM Formulas  ", null);
            c.moveToFirst();
            String nCampos = c.getString(0);
            //Vamos a ponerle a un nombre el resultado de un select
            //txtNombre = (TextView)findViewById(R.id.TxtNombre);
            //txtNombre.setText(nCampos);

            //Comprobamos si la tabla esta vacia. Insertamos solo el listado de formulas
            // en una tabla vacia.

            //Esta vacio pues insertamos
            if( nCampos.equals("0") )
            {
                db.execSQL("INSERT INTO Formulas (parametrosFormula, tipoFormula, nombreCompleto, abreviatura) VALUES('hola','hola','hola','hola') ");
                db.execSQL("INSERT INTO Formulas (parametrosFormula, tipoFormula, nombreCompleto, abreviatura) VALUES('hola','hola','hola','hola') ");
                db.execSQL("INSERT INTO Formulas (parametrosFormula, tipoFormula, nombreCompleto, abreviatura) VALUES('hola','hola','hola','hola') ");


                txtNombre = (TextView)findViewById(R.id.TxtNombre);
                txtNombre.setText("He insertado 3 campos en la base de datos");

            }

            else
            {
                txtNombre = (TextView)findViewById(R.id.TxtNombre);
                txtNombre.setText("No he insertado nada porque no hacia falta");
            }

            db.close();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}