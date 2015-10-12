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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
        final String valorRecibido = bundle.getString("NOMBRE") ;

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
        Cursor c = db.rawQuery("SELECT parametrosFormula ,tipoFormula,ecuacion FROM Formulas  WHERE abreviatura = '"+valorRecibido+"'  ", null);
        c.moveToFirst();
        //Cogemos la lista de parametros de esa formula
        cadenaCompleta = c.getString(0);
        //Primero cogemos el tipo de formula para saber si es Ecuación o Score
        String tipoDeFormula = c.getString(1);

        //Obtenemos la ecuacion de esa formula si la tiene sino simplemente obtendra null
        final String ecuacion = c.getString(2);


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
            final int numeroParametros = Util.ContarParametros(cadenaCompleta);
            final String [] parametrosOriginal  = Util.ListaParametros(cadenaCompleta);
            final String [] parametrosScore = Util.getParametrosDeScore(parametrosOriginal);
            final String [] listaParametros = Util.getListaParametro(cadenaCompleta);
            final String [] listaParametrosFiltrados = new  String[numeroParametros] ;


            //Se crea un array para ver de que tipo es cada parametro. ScoreA, ScoreB
            String [] tipoParametro = new String[numeroParametros];
            //String [] ListaScore = Util.crearListaScoreTipoA(parametrosOriginal);
            //Creamos contenedores para los campos EditText y los campos RadioGroup
            EditText ed;
            final List<EditText> allEds = new ArrayList<EditText>();
            //RadioGroup rd;
            //final List< RadioGroup > allRds = new ArrayList< RadioGroup >();
            //String [] puntuacionMenor = new String[numeroParametros];
            //String [] puntuacionIntervalo = new String[numeroParametros];
            //String [] puntuacionMayor = new String[numeroParametros];

            for(int i=0;i< numeroParametros; i++)
            {
                //Para cada parametro debemos evaluar si es ScoreA , ScoreBIncompleto o ScoreBCompleto
                String tipoDeScore;
                tipoDeScore = Util.tipoScore(parametrosOriginal[i]);

                //Guardamos el tipo de cada parametro en un array
                tipoParametro[i] = Util.tipoScore(parametrosOriginal[i]);
                int numeroScores;

                // Si es ScoreA
                if (tipoDeScore.equals("ScoreA"))
                {
                    TextView label = new TextView(this);
                    label.setText("" +parametrosScore[i]+ " es " +tipoDeScore+  "" );
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
                else if (tipoDeScore.equals("ScoreBIncompleto")    )
                {
                    TextView label = new TextView(this);
                    label.setText(parametrosScore[i]);
                    ed = new EditText(this);
                    allEds.add(ed);
                    ed.setId(i);
                    ed.setPadding(0, 1, 5, 3);
                    lm.addView(label);
                    lm.addView(ed);
                    listaParametrosFiltrados[i] = Util.filtrarParametro(listaParametros[i]);

                }

                else if (tipoDeScore.equals("ScoreBCompleto")  )
                {
                    TextView label = new TextView(this);
                    label.setText(parametrosScore[i]);
                    ed = new EditText(this);
                    allEds.add(ed);
                    ed.setId(i);
                    //Cambio el tamaño del ed porque sale demasiado grande.
                    //ed.setHeight(2);
                    //ed.setWidth(2);
                    lm.addView(label);
                    lm.addView(ed);
                    listaParametrosFiltrados[i] = Util.filtrarParametro(listaParametros[i]);
                    listaParametrosFiltrados[i] = Util.cambiarFormatoIntervaloAParametroFiltrado(listaParametrosFiltrados[i]);
                }

            }



            //final TextView lblEtiqueta = (TextView)findViewById(lm. );
            //String texto = lblEtiqueta.getText().toString();

            //Por ahora solo voy a probar para Score B incompleto
            final String[] valorIntroducido = new String[numeroParametros];
            final Button botonScore = new Button(this);
            botonScore.setText("Calcular formula");
            lm.addView(botonScore);
            final TextView mensaje = new TextView(this);

            lm.addView(mensaje);

            botonScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Expression expression = new Expression(ecuacion);
                    String cadena = "";
                    String tipoDeScore;
                    int sumaScore =0;
                    valorIntroducido[0] = allEds.get(0).getText().toString();
                    //expression.with(parametrosFiltrados[0], valorIntroducido[0]);

                    for (int i = 0; i < numeroParametros; i++) {
                        valorIntroducido[i] = allEds.get(i).getText().toString();
                        //cadena = cadena + "aqui va el parametro" + parametrosScore[i] + "aqui su valor" + valorIntroducido[i] + "y su tipo es" +Util.tipoScore(parametrosOriginal[i])+ ""   ;
                        //cadena = cadena + listaParametrosFiltrados[i-1];
                        //expression.and(parametrosFiltrados[i], valorIntroducido[i]);

                        tipoDeScore = Util.tipoScore(parametrosOriginal[i]);

                        //Debemos recorrer todos los valores y hacer la suma de Score

                        //Hay que distinguir el tipo de Score para trabajar de una forma u otra.
                        if (tipoDeScore.equals("ScoreA"))
                        {

                        }

                        else if (tipoDeScore.equals("ScoreBIncompleto")    )
                        {
                          //tenemos que coger la condicion y la puntuacion
                          //Evaluar que si se cumple la condicion sumaremos el valor del score al contador
                            String condicion;
                            int puntuacion;
                            condicion =   Util.getCondicion(listaParametrosFiltrados[i]);
                            puntuacion =  Integer.parseInt(Util.getScore(listaParametrosFiltrados[i]));
                            //cadena = cadena + "" + valorIntroducido[i]  + ""  +tipoDeScore+ "" ;

                             //Ahora se debe evaluar con la condicion si debemos sumar o no el score al contador.
                            Expression expressionScoreBIncompleto = new Expression("" + valorIntroducido[i] + "" +condicion+ "");
                            BigDecimal resultado = expressionScoreBIncompleto.eval();


                            //Si se cumple la condicion
                            if (resultado.toString().equals("1") )
                            {
                                sumaScore = sumaScore + puntuacion;
                            }



                        }

                        else if (tipoDeScore.equals("ScoreBCompleto")  )
                        {
                            //cadena = cadena + listaParametrosFiltrados[i];
                            //Para meter condicion menor, intervalo y mayor, indices 0,1,2
                            String condicion [] = new String[3];
                            //Para meter puntuacion menor, intervalo y mayor 0,1,2
                            int puntuacion[]= new int[3];
                            //Saco las 3 condiciones y las 3 puntuaciones del parametro que estan separados por ","
                            String condicionPuntuacion [] = listaParametrosFiltrados[i].split(",");
                            for(int j =0; j < 3;j++ )
                            {
                                condicion[j] = Util.getCondicion(condicionPuntuacion[j]);
                                puntuacion[j] = Integer.parseInt(Util.getScore(condicionPuntuacion[j]));
                                //cadena = cadena + condicion[j] +puntuacion[j] ;

                            }
                            Expression expressionMenor = new Expression("" + valorIntroducido[i] + "" +condicion[0]+ "");
                            BigDecimal resultadoMenor = expressionMenor.eval();
                            Expression expressionIntervalo = new Expression("" + valorIntroducido[i] + "" +condicion[1]+ "" +valorIntroducido[i]+ "");
                            BigDecimal resultadoIntervalo = expressionIntervalo.eval();
                            Expression expressionMayor = new Expression("" + valorIntroducido[i] + "" +condicion[2]+ "");
                            BigDecimal resultadoMayor = expressionMayor.eval();


                            //Si el valor intrucido cumple la condicion menor
                            if (resultadoMenor.toString().equals("1") )
                            {
                                sumaScore = sumaScore + puntuacion[0];
                            }

                            //Si el valor intrucido cumple la condicion del intervalo
                            else if (resultadoIntervalo.toString().equals("1") )
                            {
                                sumaScore = sumaScore + puntuacion[1];
                            }


                            //Si el valor introducido cumple la condicion mayor.
                            else if (resultadoMayor.toString().equals("1") )
                            {
                                sumaScore = sumaScore + puntuacion[2];
                            }


                        }

                    }

                    mensaje.setText(cadena +sumaScore);
                    //BigDecimal resultadoEcuacion = expression.eval();
                    //mensaje.setText(resultadoEcuacion.toString());

                }
            });



        }
        //Sino es Score tendra que ser de tipo ecuacion.
        else
        {

            //Creamos un array de EditText utilizando List y ArrayList
            EditText ed;
            final List<EditText> allEds = new ArrayList<EditText>();
            final int numeroParametros = Util.ContarParametros(cadenaCompleta);
            final String [] parametros  = Util.ListaParametros(cadenaCompleta);



            for(int i=0;i< numeroParametros; i++)
            {
                TextView label = new TextView(this);
                label.setText(parametros[i]);
                ed = new EditText(this);
                allEds.add(ed);
                ed.setId(i);
                lm.addView(label);
                lm.addView(ed);
            }

            final String[] valorIntroducido = new String[numeroParametros];
            final Button botonEcuacion = new Button(this);
            botonEcuacion.setText("Calcular formula");
            lm.addView(botonEcuacion);
            final TextView mensaje = new TextView(this);
            lm.addView(mensaje);


            botonEcuacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Expression expression = new Expression(ecuacion);
                    String cadena = "";
                    valorIntroducido[0] = allEds.get(0).getText().toString();
                    expression.with(parametros[0], valorIntroducido[0]);

                    for (int i = 1; i < numeroParametros; i++) {
                        valorIntroducido[i] = allEds.get(i).getText().toString();
                        cadena = cadena + "aqui va el parametro" + parametros[i] + "aqui su valor" + valorIntroducido[i] + "";
                        expression.and(parametros[i], valorIntroducido[i]);
                    }

                    //mensaje.setText(cadena + " Y la ecuacion original es" + ecuacion+ "");
                    BigDecimal resultadoEcuacion = expression.eval();
                    mensaje.setText(resultadoEcuacion.toString());

                    /*
                    String ecuacionprueba ="FC*( (edad/10)^2)/PS" ;
                    Expression expression = new Expression(ecuacionprueba);
                    expression.with("FC", "4");
                    expression.and("edad", "10");
                    expression.and("PS", "4");
                    BigDecimal resultado = expression.eval();
                    mensaje.setText(resultado.toString());
                    */
                }
            });


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
