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



    //Crea un array con la siguiente estrucutura [nombreParametro,condicion1,condicion2,condicion3, nombreParametro...]
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


