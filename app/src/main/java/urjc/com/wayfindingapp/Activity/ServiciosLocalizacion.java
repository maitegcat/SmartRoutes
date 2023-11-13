package urjc.com.wayfindingapp.Activity;

import android.os.Build;

import androidx.annotation.RequiresApi;

@WebService(serviceName = "ServiciosLocalizacion")

    public class ServiciosLocalizacion {
        /*
        Servicio Web encargado de devolver la cadena resultante al Smartphone y de llamar al
        método localizar
        de la clase LocalizacionBeacons
        */
        @RequiresApi(api = Build.VERSION_CODES.O)
        @WebMethod(operationName = "Localizar")
        public String[] Locate(@WebParam(name = "balizas") String balizas) {
            LocationBeacons registro = new LocationBeacons(balizas.split(":"));
            return registro.Locate();
        }
       /* *//*
        Servicio Web que devuelve la información contextual tomando como parametro el lugar
        y  un indicador que especifica si es información sobre el lugar o de las clases
        *//*
        @WebMethod(operationName = "ObtenerClases")
        public ArrayList ObtenerClases(@WebParam(name = "Datos") String Datos,
                                       @WebParam(name = "indicador") String indicador) {
            return new Horarios(Datos).getContexto(indicador);
        }
    }*/
}
