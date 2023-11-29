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
        try {
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
        } catch (NumberFormatException e) {
            // Manejo de la excepción.
            System.err.println("Error al convertir cadenas a números en el constructor Trilateracion.");
        }
    }
    /**
    Algoritmo de trilateracion
    */
    public void Locate() {
        try {
        DecimalFormatSymbols simbolos =
                DecimalFormatSymbols.getInstance( Locale.ENGLISH);
        DecimalFormat decimas = new DecimalFormat("0.00", simbolos);

        double denominator = (A.coorY - B.coorY) * (B.coorX - C.coorX) - (C.coorY - B.coorY) * (B.coorX - C.coorX);

        // Verificar división por cero
        if (denominator != 0) {
        double term1 = ((Math.pow(disB, 2) - Math.pow(disC, 2)));
        double term2 = ((Math.pow(disB, 2) - Math.pow(disA, 2)));

            double y = (term2 * (B.coorX - C.coorX) - term1 * (B.coorX - A.coorX)) / denominator;
            double x = (y * (A.coorY - B.coorY) - term2) / (B.coorX - C.coorX);

            // Ajustar valores negativos y límites
            x = Math.abs(x);
            y = Math.abs(y);
            if (x > 14) {
                x = 13;
            }
            if (y > 12) {
                y = 12;
            }

            coord_x = decimas.format(x);
            coord_y = decimas.format(y);
        } else {
            // Manejar el caso de división por cero
            coord_x = "Indefinido";
            coord_y = "Indefinido";
        }
        } catch (NumberFormatException e) {
            // Manejo de la excepción.
            System.err.println("Error al convertir cadenas a números en el método Locate.");
        }
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
