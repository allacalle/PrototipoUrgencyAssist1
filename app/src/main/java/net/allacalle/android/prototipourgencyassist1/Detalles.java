package net.allacalle.android.prototipourgencyassist1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        //String tipoDeFormula = "";

        // Para obtener el tipo de cada formula deberiamos movernos al cursor de cada formula y obtener el getString(1);


        //Abro la base de datos.
        FormulasSQLiteHelper usdbh =
                new FormulasSQLiteHelper(this, "DBPrueba", null, 1);

        SQLiteDatabase db = usdbh.getReadableDatabase();

        //creamos un cursos, en el string(0) tenemos el parametro, en el string(1) tenemos el tipo de formula
        Cursor c = db.rawQuery("SELECT parametrosFormula ,tipoFormula FROM Formulas  WHERE abreviatura = '"+valor+"'  ", null);
        c.moveToFirst();
        //Cogemos la lista de parametros de esa formula
        cadenaCompleta = c.getString(0);
        //Primero cogemos el tipo de formula para saber si es Ecuación o Score
        String tipoDeFormula = c.getString(1);


        //Comprobacion de que coje la lista de parametros y el tipo de formula
        //TextView caja = new TextView(this);
        //caja.setText(" Esta es la cadena  "+ cadenaCompleta+ " Y su formula es de tipo"  + tipoDeFormula+" "  );
        //lm.addView(caja);





        /*

        Averiguar si es tipo ecuacion o tipo score la Formula.
        Si es tipo Ecuacion
        Mostrar el nombre de todos los parametros y crear una caja de texto para introducir un valor
        crear un boton abajo que al pulsarlo ejecute la formula con los valores introducidos
        Solo se ejecuta la ecuación si estan todos los parametros

        Si es Score
        Se debe evaluar cada para independientemente.
        Si el paramatro es de tipo A
            Se crea un boton radial con cada una de las opciones (Se debe almacenar en alguna variable
            los distintos valores de puntuación para cada opcion elegible.

       Si el parametro es de tipo B
            Si el parametro es de tipo B incompleto
                Se crea una caja de texto para el parametro (se debe almacenar la cadena con la condicion y la puntuacion de esta)
            Si el parametro es de tipo B completo
                Se cambia el formato por defecto parametro[< num1:puntuacion1, intervalo:puntuacion2;>num2:puntuacion3]
                 para que quede de la foma.
                    x < num 1 -> Puntuacion1
                    x <= num2 && num1 <= x -> Puntuacion2
                    x > num2 --> Puntuacion3
                Se crea una caja de texto para el parametro (se debe almacenar en alguna variable )

          Si la formula es tipo Ecuacion.
          Se crea un boton cuyo nombre es calcular formula.
          Al pulsar el boton.

          Se deben agregar los valores introducidos en los campos de texto a la expression de la formula. Para ello se utiliza sobre la
          expression las funciones with() y and(). Una vez introducidos todos se utiliza la funciona eval() y esta devuelve un resultado.


          Si la formula es de tipo Score
            Si el parametro es de tipo A
                Se obtiene la puntuacion asignada a la condicion seleccionada.

            Si el parametro es de tipo B
                Si es B incompleto
                    Se obtiene la puntuacion evaluando si el numero introducido cumple la condicion o no; si la cumple suma el
                    valor de puntuacion, sino no suma nada.

                Si es B completo
                    Se obtiene la puntuacion evaluando cada una de las condiciones, dependiendo de cual cumpla obtendra una puntuacion.







        */

        //Evaluamos si el tipo de la formula es Score.
        if (tipoDeFormula.equals("score"))
        {
            int numeroParametros = Util.ContarParametros(cadenaCompleta);
            String [] parametrosOriginal  = Util.ListaParametros(cadenaCompleta);
            String [] parametrosFiltrados = Util.getParametrosDeScore(parametrosOriginal);
            //String [] ListaScore = Util.crearListaScoreTipoA(parametrosOriginal);


            for(int i=0;i< numeroParametros; i++)
            {
                //Para cada parametro debemos evaluar si es ScoreA , ScoreBIncompleto o ScoreBCompleto
                String tipoDeScore;
                tipoDeScore = Util.tipoScore(parametrosOriginal[i]);
                int numeroScores;

                // Si es ScoreA
                if (tipoDeScore.equals("ScoreA"))
                {
                    TextView label = new TextView(this);
                    label.setText("" +parametrosFiltrados[i]+ " es " +tipoDeScore+  "" );
                    lm.addView(label);
                    //Contamos el numero de valores que tiene ese parametro
                    numeroScores = Util.ContarScores(parametrosOriginal[i]);

                    RadioGroup grupo = new RadioGroup(this);
                    String listaCondicionPuntuacion [] =  Util.listaPuntuacionScore(parametrosOriginal[i]);

                    for(int j=0;j< numeroScores; j++)
                    {
                        RadioButton radial = new RadioButton(this);
                        radial.setText(listaCondicionPuntuacion[j]);
                        radial.setId((i + 1) * (j + 1));
                        grupo.addView(radial);
                    }

                    lm.addView(grupo);


                }



                //Si es ScoreB
                else if (tipoDeScore.equals("ScoreBIncompleto")  || tipoDeScore.equals("ScoreBCompleto")  )
                {
                    TextView label = new TextView(this);
                    label.setText(parametrosFiltrados[i]);
                    EditText escribe = new EditText(this);
                    escribe.setId(i);
                    escribe.setText("Pon algo");
                    lm.addView(label);
                    lm.addView(escribe);
                }

            }

        }
        //Sino es Score tendra que ser de tipo ecuacion.
        else
        {
            int numeroParametros = Util.ContarParametros(cadenaCompleta);
            String [] parametros  = Util.ListaParametros(cadenaCompleta);

            for(int i=0;i< numeroParametros; i++)
            {
                TextView label = new TextView(this);
                label.setText(parametros[i]);
                EditText escribe = new EditText(this);
                escribe.setId(i);
                escribe.setText("Pon algo");
                lm.addView(label);
                lm.addView(escribe);
            }

        }


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
