package net.allacalle.android.prototipourgencyassist1;


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


public class Detalles extends ActionBarActivity {

    private TextView txtDetalles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //Localizar los controles
        txtDetalles = (TextView)findViewById(R.id.TxtDetalles);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //Construimos el mensaje a mostrar
        //txtDetalles.setText("Elegiste la ocpcion " + bundle.getString("NOMBRE"));
        String valor = bundle.getString("NOMBRE") ;

        //creamos el layout dinamico como pros!
        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedorDetalles);

        // create the layout params that will be used to define how your
        // button will be displayed
        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        //LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        // Create LinearLayout
        //LinearLayout l1 = new LinearLayout(this);
        //l1.setOrientation(LinearLayout.HORIZONTAL);

        String cadenaCompleta ="";


        //Abro la base de datos.
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DBPrueba", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT parametrosFormula FROM Formulas  WHERE abreviatura = '"+valor+"'  ", null);
        c.moveToFirst();
        cadenaCompleta = c.getString(0);

        TextView caja = new TextView(this);
        caja.setText(cadenaCompleta);
        lm.addView(caja);

        int numeroParametros = Util.ContarParametros(cadenaCompleta);


        /*
        for(int i=0;i< numeroParametros; i++)
        {

        }
        */

        db.close();
        c.close();



        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalles, menu);
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
