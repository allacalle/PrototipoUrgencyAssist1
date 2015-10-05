package net.allacalle.android.prototipourgencyassist1;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity {
    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creamos el layout dinamico como pros!
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);

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
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES (' Alb[< 2.8:3, intervalo:2,>3.5:1];Bili[< 2:1, intervalo:2,>3:3]; TP[< 4:1, intervalo:2,>6:3];  INR[< 1.7:1, intervalo:2,>2.3:3]', 'score','Child- Pugh (con birumina total no colestatica)','Child- Pugh ');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES (' FC; edad; PS', 'ecuacion','TIMI con indice de riesgo','TIMI', '(FC*(edad/10)^2)/PS ' );");

                //db.execSQL("INSERT INTO Formulas (parametrosFormula, tipoFormula, nombreCompleto, abreviatura) VALUES('hola','hola','hola','hola') ");


                txtNombre = (TextView)findViewById(R.id.TxtNombre);
                txtNombre.setText("He insertado 3 campos en la base de datos");

            }

            else
            {
                txtNombre = (TextView)findViewById(R.id.TxtNombre);
                txtNombre.setText("No he insertado nada porque no hacia falta");
            }




            //Leemos las formulas insertadas en la base de datos y cogemos las abreviaturas.
            Cursor abreviatura = db.rawQuery(" SELECT  abreviatura FROM Formulas  ", null);
            Button botonazo = new Button(this);
            abreviatura.moveToFirst();
            final String Nombreabreviatura = abreviatura.getString(0);
            botonazo.setText(Nombreabreviatura);
            lm.addView(botonazo);


            botonazo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creamos el Intent
                    Intent intent =
                            new Intent(MainActivity.this, Detalles.class);

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", Nombreabreviatura);

                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);
                }
            });




            abreviatura.close();
            c.close();
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