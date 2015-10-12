package net.allacalle.android.prototipourgencyassist1;


import java.math.BigDecimal;

/**
 * Created by Alfonso on 21/09/2015.
 */
public final  class Util
{
    public final static int a = 0;
    public final static int b = 1;

    private Util() {}


    //Para llamarlo seria Util.DevuelveUno()
    public static String DevuelveUno() {
        return "Funciono";
    }

    // Para funciones tipo Ecuacion.

    public static  String [] ListaParametros (String consulta  )
    {

        String [] listaParametros = consulta.split(";");
        return listaParametros;
    }

    public static  int ContarParametros (String consulta  )
    {

        String [] listaParametros = consulta.split(";");
        return listaParametros.length;
    }



    public static BigDecimal  calcularEcuacion (String ecuacion )
    {
        BigDecimal resultado;

        Expression expression = new Expression(ecuacion);
        resultado =  expression.eval();

        return  resultado;
    }

    public static String [] getParametrosDeScore (String [] listaParametros )
    {

        int posicionFinal =0;
        String [] parametroDeScore = new String[listaParametros.length];
        for(int i=0;i< listaParametros.length;i++)
        {
            for(int j=0;j < listaParametros[i].length();j++)
            {
                if (listaParametros[i].charAt(j) == '[')
                {
                    //Obtenemos la posicion donde se encontra el corchete
                    posicionFinal = j;
                }
            }

            parametroDeScore[i] = listaParametros[i].substring(0, posicionFinal);
        }

        return  parametroDeScore ;

    }

    public static String [] getListaParametro (String consulta)
    {
        String [] listaParametros ;
        listaParametros = consulta.split(";");

        return listaParametros;
    }

/*
    //requiere una lista de Parametros
    public static String [] filtroScore (String [] listaParametros )
    {

        int posicionInicial =0;
        int posicionFinal =0;
        String [] parametroDeScore = new String[listaParametros.length];
        for(int i=0;i< listaParametros.length;i++)
        {
            for(int j=0;j < listaParametros[i].length();j++)
            {
                if (listaParametros[i].charAt(j) == ']')
                {
                    //Obtenemos la posicion donde se encontra el corchete
                    posicionFinal = j;
                }

                if (listaParametros[i].charAt(j) == '[')
                {
                    //Obtenemos la posicion donde se encontra el corchete
                    posicionInicial = j +1;
                }
            }

            parametroDeScore[i] = listaParametros[i].substring(posicionInicial, posicionFinal);
        }

        return  parametroDeScore ;

    }
*/
    //Esta funcion recibe una lista con los parametros perteneciente a una formula tipo Score.
    //Devuelve False para tipo Score A y devuelve True para tipo Score B.

    public static String tipoScore (String parametro )
    {

        String tipo ="ScoreA";
        int contador=0;

            for(int i=0;i < parametro.length()  ;i++)
            {
                if (parametro.charAt(i) == '>'  || parametro.charAt(i) == '<' )
                {
                    //Obtenemos la posicion donde se encontra el corchete
                    contador++;
                }
            }

        if (contador == 1)
        {
            tipo = "ScoreBIncompleto";
        }

        else if (contador > 1)
        {
            tipo ="ScoreBCompleto";
        }

        return tipo;
    }


    public static  int ContarScores (String parametroFiltrado  )
    {

        String [] listaParametros = parametroFiltrado.split(",");
        return listaParametros.length;
    }

    public static String [] listaPuntuacionScore (String parametro)
    {
        String parametroFiltrado;


        //primero necesito filtrar el parametro eliminando "nombreParametro["   y "]"
        parametroFiltrado =  filtrarParametro(parametro);

        //Meter todos estos valores en un array
        String [] condicionPuntuacion = parametroFiltrado.split(",");

        return condicionPuntuacion;
    }

    //Esta funcion elimina los corchetes y el nombre del parametro de un parametro de tipo Score
    public static String filtrarParametro (String Parametro)
    {
        int posicionInicial =0;
        int posicionFinal =0;
        String parametroFiltrado;

        for(int i=0;i < Parametro.length();i++)
        {
            if ( Parametro.charAt(i) == ']')
            {
                //Obtenemos la posicion donde se encontra el corchete
                posicionFinal = i;
            }

            if (Parametro.charAt(i) == '[')
            {
                //Obtenemos la posicion donde se encontra el corchete
                posicionInicial = i +1;
            }
        }

        parametroFiltrado =  Parametro.substring(posicionInicial, posicionFinal);

        return parametroFiltrado;

    }

    /*


     */

    public static  boolean condicionScoreEsMenor (String parametroFiltrado  )
{
    boolean resultado = false;

    for(int i=0;i < parametroFiltrado.length();i++)
    {
        if (parametroFiltrado.charAt(i) == '<' )
        {
            //Obtenemos la posicion donde se encontra el corchete
            resultado = true;
        }
    }


    return resultado;
}

    public static  boolean condicionScoreEsMayor (String parametroFiltrado  )
    {
        boolean resultado = false;

        for(int i=0;i < parametroFiltrado.length();i++)
        {
            if (parametroFiltrado.charAt(i) == '>' )
            {
                //Obtenemos la posicion donde se encontra el corchete
                resultado = true;
            }
        }


        return resultado;
    }

    //Saca el numero mas pequeño de un intervalo

    public static  int sacarValorMenorIntervalor (String parametroFiltrado  )
    {

        String [] listaParametros = parametroFiltrado.split(",");
        return listaParametros.length;
    }


    //Para que esta funcion de un valor acertado debe ser una Formula ScoreBCompleto
    // <valorMenor:punt,intervalo:punt,>valorMayor:punt
    public static String sacarValorMenorDeIntervalo (String parametroFiltrado)
    {

       //Primero tomamos la parte del condicion:score para todo el parametro por ejemplo
       // <valorMenor:punt,intervalo:punt,>valorMayor:punt
       String condicionConScore [] =parametroFiltrado.split(",");

        // Tomamos solo la parte de <valorMenor de la cadena  <valorMenor:punt
       String  condicion [] = condicionConScore[0].split(":");
        //Tomamos la primera parte <valorMenor
        String valor = condicion[0];
        valor = valor.substring(1);
        //int valorMenor =0;
       // valorMenor = Integer.parseInt(valor);
        return  valor;

    }

    //Para que esta funcion de un valor acertado debe ser una Formula ScoreBCompleto
    // <valorMenor:punt,intervalo:punt,>valorMayor:punt

    public static String sacarValorMayorDeIntervalo (String parametroFiltrado)
    {

        //Primero tomamos la parte del condicion:score para todo el parametro por ejemplo
        // <valorMenor:punt,intervalo:punt,>valorMayor:punt
        String condicionConScore [] =parametroFiltrado.split(",");

        // Tomamos solo la parte de <valorMenor de la cadena  >valorMenor:punt
        String  condicion [] = condicionConScore[2].split(":");
        //Tomamos la primera parte >valorMenor
        String valor = condicion[0];
        valor = valor.substring(1);

        return  valor;

    }

    //Para que esta funcion de un valor acertado debe ser una Formula ScoreBCompleto
    // <valorMenor:punt,intervalo:punt,>valorMayor:punt
    public static String crearFormatoIntervalo (String parametroFiltrado)
    {

        String valorMenor = sacarValorMenorDeIntervalo(parametroFiltrado);
        String valorMayor = sacarValorMayorDeIntervalo(parametroFiltrado);

        return "<= " +valorMayor+ " && " +valorMenor+ " <=" ;
    }


    //Para que esta funcion de un valor acertado debe ser una Formula ScoreBCompleto
    // <valorMenor:punt,intervalo:punt,>valorMayor:punt
    public static String cambiarFormatoIntervaloAParametroFiltrado (String parametroFiltrado )
    {
        String condicionConScore [] =parametroFiltrado.split(",");
        String valorIntervalo[] = condicionConScore[1].split(":");
        String puntuacionIntervalo = ":" + valorIntervalo[1] + "";
        String nuevoIntervalo = crearFormatoIntervalo(parametroFiltrado);


        return  "" +condicionConScore[0]+ "," +nuevoIntervalo+ "" + puntuacionIntervalo +  "," + condicionConScore[2] +""  ;
    }


    //Recibe un string con el formato condicion:score y devuelve la condicion como cadena de texto
    public static  String getCondicion (String condicionScore)
    {
      String resultado [] =  condicionScore.split(":");

        return  resultado[0];
    }


    public static  String getScore (String condicionScore)
    {
        String resultado [] =  condicionScore.split(":");

        return  resultado[1];
    }










    //Crea un array con la siguiente estrucutura [nombreParametro,condicion1,condicion2,condicion3, nombreParametro...]

    //El tipo A se refiere al parametro asi que no tiene sentido llamar a una lista tipo A o tipo B, ya que el Score puede tener ambos a la vez.


    /*

    public static String [] crearListaScoreTipoA (String [] listaParametros )
    {
        String [] parametroDeScore = getParametrosDeScore(listaParametros);
        String [] puntuacionesScore =  filtroScore(listaParametros);
        String [] arrayFinalScore  =  new String[listaParametros.length *4] ;
        int j =0;


        for(int i=0;i< listaParametros.length;i++)
        {

            String [] condicion ;

            arrayFinalScore[j]= parametroDeScore[i];
            j++;
            condicion = puntuacionesScore[i].split(",");
            arrayFinalScore[j] =  condicion[0];
            j++;
            arrayFinalScore[j] = condicion[1];
            j++;
            arrayFinalScore[j] =  condicion[2];
            j++;

        }

        return arrayFinalScore;
    }

    public static String [] crearListaScoreTipoB (String [] listaParametros )
    {
        String [] parametroDeScore = getParametrosDeScore(listaParametros);
        String [] puntuacionesScore =  filtroScore(listaParametros);
        String [] arrayFinalScore  =  new String[listaParametros.length * 5 ] ;
        int j =0;


        for(int i=0;i< listaParametros.length;i++)
        {

            String [] condicion ;
            String nScores ="";

            arrayFinalScore[j]= parametroDeScore[i];
            j++;
            nScores = nScores + parametroDeScore.length ;
            arrayFinalScore[j] = nScores;
            j++;
            condicion = puntuacionesScore[i].split(",");
            for(int k=0;k <= parametroDeScore.length ;k++)
            {
                arrayFinalScore[j] = condicion[k];
                j++;
            }

        }

        return arrayFinalScore;
    }


    /*

    Funciones que hay que implementar.

    Para formulas tipo ecuacion:
    1. Funcion que saque los parametros de una consulta y los introduzca
    en un array que devuelve.
    2. Funcion que tome la formula de una consulta y ejecute esa formula con los
    parametros introducidos en tiempo de ejecucion y devuelva un resultado.

    Para formulas tipo Score
    1. Funcion que divida la consulta de parametros en parametro[scores] (Es la misma que para eduacion)
    2. Funcion que saque los parametros de una consulta y los introduzca
    en un array que devuelva.
    3. Funcion que evalue si el Score es de tipo 1 o de tipo 2.
        Tipo1: Son Score con varias puntuaciones que dependen de si es un parametro es
        mayor, menor o esta en un intervalo de valores. El parametro es un numero.
        Tipo2: Son Score en el que cada puntuacion es una opcion distinta.El parametro
        es un checkpoint.
    4.Funcion que filtre un String de un parametro eliminando el nombre del parametro
    y los caracteres "[" y "]"
    5.Funcion que obtenga los valores de Score para un parametro.
    Para Tipo 1 Devolvera: parametro, condicion menor, puntuacion, condicion intervalo,
     puntuacion, condicion mayor puntuacion, parametro...)
    Para tipo 2:  Devolvera: parametro, numeroScore, condicion1, puntuacion1,
    condicion2,puntuacion2, parametro, numeroScore...



     */


}


