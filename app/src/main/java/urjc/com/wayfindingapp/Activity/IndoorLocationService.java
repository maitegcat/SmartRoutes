package urjc.com.wayfindingapp.Activity;

import android.os.Build;

public class IndoorLocationService {

    /* Servicio Web encargado de devolver la cadena resultante al Smartphone y de llamar al
    método locate de la clase IndoorLocationService  */

    public String[] Localizar(String baliza) {
        LocationBeacons registro = new LocationBeacons(baliza.split(":"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return registro.Locate();
        }
        return new String[0];
    }
}

/*
//    Servicio Web que devuelve la información contextual tomando como parametro el lugar
//    y un indicador que especifica si es información sobre el lugar

    @WebMethod(operationName = "ObtenerClases")
    public ArrayList ObtenerClases(@WebParam(name = "Datos") String Datos,
                                   @WebParam(name = "indicador") String indicador) {
        return new Horarios(Datos).getContexto(indicador);
    }
}
*/
