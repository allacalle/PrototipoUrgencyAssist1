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
import android.widget.TextView;

import java.math.BigDecimal;


public class MainActivity extends ActionBarActivity {
   //private TextView txtNombre;

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
            Cursor c = db.rawQuery(" SELECT  COUNT(*) FROM Formulas  ", null);
            c.moveToFirst();
            String nCampos = c.getString(0);
            //Vamos a ponerle a un nombre el resultado de un select
            //txtNombre = (TextView)findViewById(R.id.TxtNombre);
            //txtNombre.setText(nCampos);

            //Comprobamos si la tabla esta vacia. Insertamos solo el listado de formulas
            // en una tabla vacia.

            //Esta vacio pues insertamos

            //Mucho cuidado con los espacios en blanco entre parametros

            if( nCampos.equals("0") )
            {
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('Alb[<2.8:3,intervalo:2,>3.5:1];Bili[<2:1,intervalo:2,>3:3];TP[<4:1,intervalo:2,>6:3];INR[<1.7:1,intervalo:2,>2.3:3]','score','Child- Pugh(con birumina total no colestatica)','Child-Pugh');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('FC;edad;PS','ecuacion','TIMI con indice de riesgo','TIMI','FC*((edad/10)^2)/PS');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('edad[>55:1];Leucos[>15:1];gluc[>180:1];LDH[>600:1];Alb[<3.2:1];Ca++[<8:1];PaO2[<60:1];SUN[>45:1] ','score','Glasgow criterios para pancreatitis','Glasgow pancreatitis');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('Ojos(apertura)[espontanea:4,por indicación:3,estimulo doloroso:2,no respuesta:1];Verbal(mejor)[orientado:5,confuso:4,palabras inapropiadas:3,incomprensible:2,no respuesta:1];Motor(mejor)[obedece indicaciones:6,localiza dolor:5,retira el dolor:4,flexion anormal:3,extension:2,no respuesta:1]','score','Glasgow,Escala de Coma','Glasgow coma');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('edad[<65:0,intervalo:1,>74:2];sexo[hombre:0,mujer:1];insuficiencia cardiaca[si:1,no:0];hipertension[si:1,no:0];AVC[si:1,no:0];AIT[si:1,no:0];TEV[si:1,no:0];enfermedad vascular[si:1,no:0];diabetes mellitus[si:1,no:0]','score','Escala de riesgo de AVC por fibriliacion atrial','CHAD2DS2-VASc');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('TVP y S&S[si:3,no:0];Ep mas probable[si:3,no:0]; FC[>100:1.5]; Inmob/Cirugia[si:3,no:0]; Previo EP o TVP[si:1.5,no:0]; hemoptisis [si:1,no:0]; malignidad[si:1, no:0]', 'score','Wells Score para Embolismo Pulmonar (EP) (modificado, Canada) ','Wells Score EP');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura)  VALUES ('Cancer[si:1,no:0];En cama/cirugia[si:1,no:0];edema en pantorrilla[si:1,no:0];venas superficiales[si:1,no:0];dolor[si:1,no:0];godete positivo[si:1,no:0];paralisis[si:1,no:0];TVP previa[si:1,no:0];Diagnostico alternartivo[si:-2,no:0]','score','Wells Score para Trombosis Venosa Profunda','Wells Score TVP');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('BUN;CrS','ecuacion','BUN:Creatinina(S)relacion','BUN','BUN/CrS');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('CrU;volU;CrS','ecuacion','Creatinina Depuracion','ClCr','(CrU*volU )/CrS');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('edad;peso;CrS','ecuacion','Filtracion Glomerural P (Cockcrof)','FGP','((140-edad)*peso)(72*CrS)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('H2O;peso;Na','ecuacion','Agua libre, exceso','H2Oxs','H2O*peso*(1- Na/140)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('H2O;peso;Na','ecuacion','Agua corporal,deficit','H2Odef','H2O*peso*(Na/140 -1)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;ClVd;Cld;Clm','ecuacion','Cloro,deficit','Cldef', ' peso * ClVd (Cld  - Clm ) ');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;altura','ecuacion','Superficie corporal total','SCT', 'peso^0.425/altura^0.725*0.007184');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;altura;edad','ecuacion','Gasto de energia basal en mujer','GeBmu','665 + 9.6*(peso)+ 1.8 *(altura)- 4.7 * (edad)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;altura;edad','ecuacion','Gasto de energia basal en hombre','GeBhom','66 + 13.7*(peso)+ 5 *(altura)- 6.8 * (edad)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('Na;K;glucosa;BUN','ecuacion','Osmolaridad calculada','Osm','2*(Na+K)+glucosa/18+BUN/2.8');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('H2O;peso','ecuacion','Agua Corpotal Total','TBW','H20/100*peso');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;VD;HCO3m;HCO3d','ecuacion','Bicarbonato exceso','HCO3xs','peso*VD/100*(HCO3m-HCO3d)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('peso;VD;HCO3m;HCO3d','ecuacion','Bicarbonato deficit','HCO3def','peso*VD/100*(HCO3d-HCO3m)');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('FIO2;PB;PH2O; R;PACO2','ecuacion','Gradiente A-a de O2','PAO2','FIO2*(PB-PH2O)–PACO2/R');");
                db.execSQL("INSERT INTO Formulas (parametrosFormula,tipoFormula,nombreCompleto,abreviatura,ecuacion)  VALUES ('FIO2;PB;PH2O;','ecuacion','Tensión inspirada de oxigeno','PIO2','FIO2*(PB-PH2O)');");







                //db.execSQL("INSERT INTO Formulas (parametrosFormula, tipoFormula, nombreCompleto, abreviatura) VALUES('hola','hola','hola','hola') ");


                //txtNombre = (TextView)findViewById(R.id.TxtNombre);
                //txtNombre.setText("Se ha creado la base de datos");

            }
 /*
            else
            {
                 txtNombre = (TextView)findViewById(R.id.TxtNombre);
                 txtNombre.setText("La base de datos sigue igual");
            }
*/



            //Leemos las formulas insertadas en la base de datos y cogemos las abreviaturas.
            Cursor abreviatura = db.rawQuery(" SELECT  abreviatura FROM Formulas  ", null);
            //Button botonazo = new Button(this);
            int numeroFormulas;
            numeroFormulas = abreviatura.getCount();
            abreviatura.moveToFirst();



            final String Nombreabreviatura = abreviatura.getString(0);

            for(int i=0;i< numeroFormulas; i++)
            {
                final Button boton = new Button(this);
                boton.setId(i);

                if (i == 0)
                    abreviatura.moveToFirst();
                else
                    abreviatura.moveToNext();

                boton.setText(abreviatura.getString(0));
                lm.addView(boton);

                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Creamos el Intent
                        Intent intent =
                                new Intent(MainActivity.this, Detalles.class);

                        //Creamos la información a pasar entre actividades
                        Bundle b = new Bundle();
                        b.putString("NOMBRE", (String) boton.getText());

                        //Añadimos la información al intent
                        intent.putExtras(b);

                        //Iniciamos la nueva actividad
                        startActivity(intent);
                    }
                });


            }


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