package urjc.com.wayfindingapp.Activity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Trilateracion {

    public String RSSI[];
    double disA;
    double disB;
    double disC;
    Posicion A, B, C;
    String coord_x = null;
    String coord_y = null;


    /** Constructor de la clase que toma los valores RSSI y crea tres objetos de la clase punto, para realizar la trilateracion */

    public Trilateracion(String coordenadas[]) {
        RSSI = coordenadas;
        for (int i = 0; i < coordenadas.length; i++) {
            String balizas[] = coordenadas[i].split(";");
            switch (i) {
                case 0:
                    A = new Posicion(balizas[0], Double.parseDouble(balizas[1]),
                            Double.parseDouble(balizas[2]));
                    disA = Double.parseDouble(balizas[3]);
                    break;
                case 1:
                    B = new Posicion(balizas[0], Double.parseDouble(balizas[1]),
                            Double.parseDouble(balizas[2]));
                    disB = Double.parseDouble(balizas[3]);
                    break;
                case 2:
                    C = new Posicion(balizas[0], Double.parseDouble(balizas[1]),
                            Double.parseDouble(balizas[2]));
                    disC = Double.parseDouble(balizas[3]);
                    break;
                default:
                    break;
            }
        }
    }
    /**
    Algoritmo de trilateracion
    */
    public void Locate() {
        DecimalFormatSymbols simbolos =
                DecimalFormatSymbols.getInstance( Locale.ENGLISH);
        DecimalFormat decimas = new DecimalFormat("0.00", simbolos);
        double Pa = ((Math.pow(disB, 2) - Math.pow(disC, 2)));
        double Pb = ((Math.pow(disB, 2) - Math.pow(disA, 2)));
        double x = 0, y = 0;
        y = (Pb * (B.coorX - C.coorX) - Pa * (B.coorX - A.coorX)) / ((A.coorY - B.coorY)
                * (B.coorX - C.coorX) - (C.coorY - B.coorY) * (B.coorX - C.coorX));
        x = (y  * (A.coorY - B.coorY) - Pb) / (B.coorX - C.coorX);

        if (x < 0) {
            x = x * -1; }
        if (y < 0) {
            y = y * -1; }
        if (x > 14) {
            x = 13;  }
        if (y > 12){
            y = 12; }

        coord_x= decimas.format(x);
        coord_y= decimas.format(y);
        }
/**
Clase interna para almacenar el nombre de cada posicion, y sus coordenadas
*/
private class Posicion {
    public String name;
    public double coorX, coorY, coorX2, coorY2;
    public Posicion(String baliza, double x, double y) {
        name = baliza;
        coorY = y;
        coorX = x;
        coorY2 = Math.pow(y, 2);
        coorX2 = Math.pow(x, 2);
    }
    public Posicion() {
     }
   }
}
