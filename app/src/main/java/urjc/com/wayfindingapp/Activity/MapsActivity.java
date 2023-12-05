package urjc.com.wayfindingapp.Activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

import urjc.com.wayfindingapp.BuildConfig;
import urjc.com.wayfindingapp.Model.OutdoorGuide;
import urjc.com.wayfindingapp.R;


public class MapsActivity extends AppCompatActivity {

    private OutdoorGuide params;

    private String[] latLong;
    private String[] city;
    private String[] country;
    private MapView myOpenMapView;
    private MapController myMapController;
    private GeoPoint posicionActual;
    ArrayList <OverlayItem> puntos = new ArrayList <OverlayItem>();
    private IGeoPoint ciudad;

    private MyLocationNewOverlay mLocationOverlay;
    private CompassOverlay mCompassOverlay;
    private MinimapOverlay mMinimapOverlay;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
  //  private ResourceProxy mResourceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Configuration.getInstance().setUserAgentValue( BuildConfig.APPLICATION_ID);

       if (tengoPermisoEscritura()) {
                cargarMapas();
            }

    }

        private void cargarMapas() {

         //  GeoPoint madrid = new GeoPoint(40.416775, -3.70379);

            Bundle bundle= getIntent().getExtras();
            if (bundle!= null) {// to avoid the NullPointerException
                params = (OutdoorGuide) this.getIntent().getExtras().get("outdoorGuide");
                latLong = params.getPlaceOrigin().getLatLng().split(",");
                city = params.getPlaceOrigin().getCity().getNameDef().split( ",");
                country = params.getPlaceOrigin().getCity().getState().getCountry().getNameDef().split( ",");
                Log.d("Latlong",latLong[0]+"");

            GeoPoint ciudad = new GeoPoint( Double.parseDouble(latLong[0]), Double.parseDouble(latLong[1]));

                myOpenMapView = (MapView) findViewById(R.id.openmapview);
                myOpenMapView.setBuiltInZoomControls(true);
                myOpenMapView.setClickable(true);
                myOpenMapView.setTileSource( TileSourceFactory.MAPNIK);
                myMapController = (MapController) myOpenMapView.getController();
                myMapController.setCenter(ciudad);
                myMapController.setZoom(19);
                myOpenMapView.setMultiTouchControls(true);

                myOpenMapView.setTilesScaledToDpi(true);
                myOpenMapView.setFlingEnabled(true);
                myOpenMapView.getOverlays().add(this.mLocationOverlay);
                myOpenMapView.getOverlays().add(this.mCompassOverlay);
                myOpenMapView.getOverlays().add(this.mScaleBarOverlay);

                /*  mLocationOverlay.enableMyLocation();
            mLocationOverlay.enableFollowLocation();
            mLocationOverlay.setOptionsMenuEnabled(true);
           mCompassOverlay.enableCompass();*/

             ///////////////////////////////////
            //Centrar en la posición actual
           final MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), myOpenMapView);
            myOpenMapView.getOverlays().add(myLocationoverlay); //No añadir si no quieres una marca
            myLocationoverlay.enableMyLocation();
            myLocationoverlay.runOnFirstFix(new Runnable() {
               public void run() {
                    myMapController.animateTo(myLocationoverlay.getMyLocation());
                }
            });
            /////////////////////////////////////////
            // Añadir un punto en el mapa

           puntos.add(new OverlayItem(String.format(city[0]), String.format(country[0]), ciudad));
                refrescaPuntos();

            /////////////////////////////////////////
            // Detectar cambios de ubicación mediante un listener (OSMUpdateLocation)
            LocationManager locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE);
            OSMUpdateLocation detectaPosicion = new OSMUpdateLocation(this);
            if (tengoPermisoUbicacion()) {
                Location ultimaPosicionConocida = null;
                for (String provider : locationManager.getProviders(true)) {
                    if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                        ultimaPosicionConocida = locationManager.getLastKnownLocation(provider);
                    if (ultimaPosicionConocida != null) {
                        actualizaPosicionActual(ultimaPosicionConocida);
                    }
                    //Pedir nuevas ubicaciones
                    locationManager.requestLocationUpdates(provider, 0, 0, detectaPosicion);
                    break;
                }
            } else {
                // No tengo permiso de ubicación
            }
        }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setClass(this, this.getClass());
                startActivity(intent);
                finish();
            } else {
                // El usuario no ha dado permiso
            }
        }

       public boolean tengoPermisoEscritura() {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return false;
                }
            } else {
                return true;
            }
        }

        public boolean tengoPermisoUbicacion() {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    return true;
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                    return false;
                }
            } else {
                return true;
            }
       }

        public void actualizaPosicionActual(Location location) {
            posicionActual = new GeoPoint(location.getLatitude(), location.getLongitude());
            myMapController.setCenter(posicionActual);
            if (puntos.size() > 1)
                puntos.remove(1);
            OverlayItem marcador = new OverlayItem("Estás aquí", "Posicion actual", posicionActual);
            marcador.setMarker( ResourcesCompat.getDrawable(getResources(), R.drawable.center, null));
            puntos.add(marcador);
            refrescaPuntos();

        }

        private void refrescaPuntos() {
            myOpenMapView.getOverlays().clear();
            ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                @Override
                public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                    return false;
                }

                @Override
                public boolean onItemSingleTapUp(int index, OverlayItem item) {
                    return true;
                }
            };

            ItemizedOverlayWithFocus <OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
            capa.setFocusItemsOnTap(true);
            myOpenMapView.getOverlays().add(capa);
        }
    }
