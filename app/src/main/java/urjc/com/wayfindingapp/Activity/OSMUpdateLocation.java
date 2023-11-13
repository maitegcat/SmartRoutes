package urjc.com.wayfindingapp.Activity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class OSMUpdateLocation implements LocationListener {
    private MapsActivity actividad;

    public OSMUpdateLocation(MapsActivity actividad) {
        this.actividad = actividad;
    }

    @Override
    public void onLocationChanged(Location location) {
        actividad.actualizaPosicionActual(location);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
