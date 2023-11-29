package urjc.com.wayfindingapp.Activity;

import static urjc.com.wayfindingapp.Activity.IndoorScan.dbNombre;
import static urjc.com.wayfindingapp.Activity.IndoorScan.dbVersion;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

public class LocationBeacons extends AppCompatActivity {


    String balizas[];
    DataBaseHelper base = new DataBaseHelper( this, dbNombre, null, dbVersion);

    /**Constructor de la clase que inicializa los datos de las baliza y sus respectivos
    valores de RSSI
    */
    public LocationBeacons(String[] aux) {
        balizas = aux;
    }
    /**Método que obtiene la distancia media entre la obtenida de balizas a dispositivo y
    la real entre las balizas
    */

    private double DistanciaMedia(String min, String cadena) {
        double dist = 0;
        dist = Double.parseDouble(cadena);
        if (cadena.indexOf(min) >= 0) {
            if (dist < 1) {
                dist = 1;
            }
        } else {
            dist = (dist + base.ObtenerDistancia(min, cadena.split(";")[0]) / 2);
        }
        return dist;
    }
/**
Método que comprime la imagen en Base64 para ser enviada al movil como cadena de texto
*/
@RequiresApi(api = Build.VERSION_CODES.O)
public String establecerImg(String lugar) {
    Base64.Encoder encoder = Base64.getEncoder();
    String direccion = base.ObtenerImagen(lugar);
    File file = new File(direccion.trim());
    byte[] fileArray = new byte[(int) file.length()];
    InputStream inputStream;
    String encodedFile = "";
    try {
        inputStream = new FileInputStream(file);
        inputStream.read(fileArray);
        encodedFile = encoder.encodeToString(fileArray);
    } catch (Exception e) {
        System.err.println(e.toString());
    }
    return encodedFile;
}
    /**
    Metodo que encuentra el valor del nivel RSSI de una baliza en especefico
    de toda la cadena general
    */
    public String distArrayRecibido(String baliza) {
        String res = null;
        int cont = 0;
        boolean flag = false;
        while (!flag) {
            if (balizas[cont].split(";")[0].equals(baliza)) {
                res = balizas[cont].split(";")[1];
                flag = true;
            } else {
                cont++;
            }
        }
        return res;
    }
    /**
    M. establecer la cadena que se envia al móvil, llama al metodo que impl.
    la trilateraccion y al  que comprime la imagen
    */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String[] Locate() {
        String respuesta[] = respuesta = new String[5];
        String linea[] = new String[3];
        Trilateracion tri = null;
        String A = "", B = "", C = "", min = "";
        String lugar = base.ObtenerCodLugar(balizas[0].split(";")[0]);
        String nomlugar = base.ObtenerLugar(lugar);
        if (nomlugar.indexOf("L") == 0) {
            respuesta[0] = lugar;
            respuesta[1] = nomlugar;
            respuesta[2] = "null";
            respuesta[3] = "null";
            respuesta[4] = establecerImg(lugar);
        } else if (nomlugar.indexOf("P") == 0) {
            String pasillobalizas[] = base.ObtenerBeaconsPasillo(lugar);
            A = pasillobalizas[0] + ";" + base.ObtenerCoordendadas(pasillobalizas[0])
                    + ";" + DistanciaMedia(balizas[0].split(";")[1],
                    distArrayRecibido(pasillobalizas[0]));
            B = pasillobalizas[1] + ";" + base.ObtenerCoordendadas(pasillobalizas[1])
                    + ";" + DistanciaMedia(balizas[0].split(";")[1],
                    distArrayRecibido(pasillobalizas[1]));
            C = pasillobalizas[2] + ";" + base.ObtenerCoordendadas(pasillobalizas[2])
                    + ";" + DistanciaMedia(balizas[0].split(";")[1],
                    distArrayRecibido(pasillobalizas[2]));
            linea[0] = A;
            linea[1] = B;
            linea[2] = C;
            tri = new Trilateracion(linea);
            tri.Locate();
            respuesta[0] = lugar;
            respuesta[1] = nomlugar+"\n"+base.ObtenerDescrpPasillo(lugar);
            respuesta[2] = tri.coord_x+"";
            respuesta[3] = tri.coord_y+"";
            respuesta[4] = establecerImg(lugar);
        }
        return respuesta;
    }
}