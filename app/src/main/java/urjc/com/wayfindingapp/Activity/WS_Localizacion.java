package urjc.com.wayfindingapp.Activity;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

//prueba con SOAP

public class WS_Localizacion extends AsyncTask <String, Void, String> {

    final String SOAP_ACTION = "http://services/Location";
    final String METHOD = "Location";
    final String NAMESPACE = "http://services/";
    final String ENDPOINTWS =
         //  "http://10.0.2.2:8080/ServiceWebSoap/IndoorLocationService?wsdl=1";
       // "http://127.0.0.1:8080/ServiceWebSoap/IndoorLocationService?xsd=1";
          "http://193.147.52.3:8082/ServiceWebSoap/IndoorLocationService?wsdl";
  //  String response = "";
    String resp = null;
    /*
    Metodo sobreescrito que inicializa la clase y llama al servicio web
    */
    @Override
    protected String doInBackground(String... args) {
        SoapObject userRequest = new SoapObject(NAMESPACE, METHOD);
        userRequest.addProperty("baliza", args[0]);
        SoapSerializationEnvelope envelope = new
                SoapSerializationEnvelope( SoapEnvelope.VER11);
        envelope.setOutputSoapObject(userRequest);
        try{
            HttpTransportSE androidHttpTransport = new HttpTransportSE(ENDPOINTWS);
            androidHttpTransport.debug = true;
            androidHttpTransport.call(SOAP_ACTION, envelope);
            resp = envelope.getResponse().toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
}
