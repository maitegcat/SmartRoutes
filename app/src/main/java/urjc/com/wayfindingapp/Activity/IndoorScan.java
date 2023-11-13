package urjc.com.wayfindingapp.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.Region;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import urjc.com.wayfindingapp.APIInterface;
import urjc.com.wayfindingapp.Model.Lugar;
import urjc.com.wayfindingapp.R;


public class IndoorScan extends AppCompatActivity implements DialogoConfig.Config,
        DialogoInfo.Info, DialogoAcceso.Acceso{

    APIInterface apiInterface;

    //List <Baliza> balizas = new ArrayList<>();
    List <Lugar> lugares = new ArrayList<>();
    // List <LugarPasillo> lugar_pasillo = new ArrayList<>();

    //FloatingActionButton fab;
    public TextView txt_aula, info_aula;
    //public TextView txt_coord;
    public ImageView image;
    public static BluetoothAdapter miBTAdapter = null;
    public Button info;

    private static final String ALL_BEACONS_REGION = "AllBeaconsRegion";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static DataBaseHelper dataBaseHelper;
    public static String dbNombre = "bluetooth", desc = "";
    public static Handler mHandler;
    public static boolean estado = false, estado1 = false, descargado = false;
    public static String resView = "";
    public static Cursor c;

    // ArrayList <Lugar> lugares = new ArrayList <Lugar>();
 // ArrayList <Baliza> balizas = new ArrayList <Baliza>();
    ArrayList <LugarAcceso> lugar_accesos = new ArrayList <LugarAcceso>();
    public static int ciclo = 60000;
    public static int aulas_acceso = 1;

    public static TextToSpeech mTts;
    public static String toRead;

    // Para interactuar con los beacons desde una actividad
    private BeaconManager mBeaconManager;
    // Representa el criterio de campos con los que buscar beacons
    private Region mRegion;

    /**
     * Metodo sobrescrito que inicializa la app y sus elementos graficos
     */
    @SuppressLint({"ResourceType", "StringFormatMatches"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_indoor_scan);
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        info = (Button) findViewById( R.id.info );
        txt_aula = (TextView) findViewById( R.id.txt_aula );
       //txt_coord = (TextView) findViewById( R.id.txt_coord );
        image = (ImageView) findViewById( R.id.image );
        info_aula = (TextView) findViewById( R.id.info_aula );
        // fab = (FloatingActionButton) findViewById(R.id.fab);
        // fab.setVisibility(View.INVISIBLE);

        /*mBeaconManager = BeaconManager.getInstanceForApplication(this);

        // Fijar un protocolo beacon, Eddystone en este caso
        mBeaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));

        ArrayList<Identifier> identifiers = new ArrayList<>();

        mRegion = new Region(ALL_BEACONS_REGION, identifiers);*/

         /**
         Inicializacion de la base de datos SQLite
         */
            dataBaseHelper = new DataBaseHelper( this, dbNombre, null, 1 );

         /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Si los permisos de localización todavía no se han concedido, solicitarlos
            if (this.checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {

                // Notificar al usuario
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage(R.string.location_disabled);
                dialog.setPositiveButton(R.string.location_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        // TODO Auto-generated method stub
                        Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                });
                dialog.show();
            }
         }*/
        // Check if the location permissions are granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            // Location permissions are already granted
            // Perform location-re lated tasks here
        } else {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

        /**
         Llamada a la clase BluetoothAdapter
         */
        miBTAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!miBTAdapter.isEnabled()) {
            Intent turnOnIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
            startActivityForResult( turnOnIntent, 0 );
        }
         mHandler = new Handler();
        miBTAdapter = BluetoothAdapter.getDefaultAdapter();


         /**
         Boton atras
         */
          /*ImageButton home = (ImageButton)findViewById(R.id.home);
         home.setContentDescription(home.getContentDescription()+". Está en rutas de interiores");
         home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });*/

        /**
         M. sobrescrito que asociado al evento click del boton para presentar el mensaje
         de actualizacion
         */
         /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.msg_act, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

         /**
         M. sobrescrito asociado al evento click del boton para visualizar el dialogo de
         la informacion del lugar
         */
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aulas_acceso==1) {
                    Lugar registro = (Lugar) lugares.get(0);
                    crearDialogoInfo(registro);
                }else
                    crearDialogoAcceso();
            }
        });
          if (miBTAdapter.isEnabled()) {
            SharedPreferences pref = getSharedPreferences( "datos",
                    Context.MODE_PRIVATE );
            ciclo = Integer.parseInt( Objects.requireNonNull( pref.getString( "tiempo", "60000" ) ) );

            Escanear( true );
        } else {
            EstadoOriginal( false );
        }
    }

    private void setSupportActionBar(Toolbar toolbar) {

    }

    /**
     * M. sobrescrito que genera el menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; para añadir elementos a la barra de acciones .
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.credits, menu );
        /* AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Bien!!! ");
        alertDialog.setMessage("Has conseguido 10 puntos");
        alertDialog.setIcon(R.drawable.medalla);
        alertDialog.show();*/
        return true;
    }

    /**
     * M. sobrescrito asociado al la seleccion de un elemento del menu
     */
 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.tiempo) {
            crearDialogoConfig();
            return true;
        } else if (id == R.id.menu_actualizar) {
            Reiniciar();
        }
        return super.onOptionsItemSelected( item );
    }*/

    /**
     * M. encargado de reiniciar la app
     */
    public void Reiniciar() {
        BaseDeDatos.CleanTableTemp( dataBaseHelper );
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity( i );
    }

    /**
     * M. que aplica el hilo encargado de actualizar la localizacion segun el ciclo
     * programado
     */
    public void Hilo(boolean enable) {
        if (enable && !estado1) {
            mHandler.postDelayed( new Runnable() {
                @Override
                public void run() {
                    estado1 = false;
                    Escanear( true );
                }
            }, ciclo );
            estado1 = true;
        } else {
            estado1 = false;
        }
    }

    /**
     * M. encargado de representar los resultados
     */
    private void Localizar(){
        String[] coord = CrearArray();
        String res,baliza = "";
        for(int i=0;i<coord.length;i++) {
            baliza+= coord[i] + ":";
        }
        Log.i("Baliza: ",baliza.substring(0,baliza.length()-1));
        //WS_Localizacion servicio = (WS_Localizacion) new WS_Localizacion().execute(baliza);

     // Aquí realizas la consulta a la base de datos para obtener la información de la baliza
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String query = "SELECT Name, Coord_x, Coord_y FROM Baliza WHERE IDbaliza = ?";
        Cursor cursor = db.rawQuery(query, new String[]{baliza});

        try {
            if (cursor.moveToFirst()) {
                String nom_lugar = cursor.getString( cursor.getColumnIndex( "Name" ) );
                int coordX = cursor.getInt( cursor.getColumnIndex( "Coord_x" ) );
                int coordY = cursor.getInt( cursor.getColumnIndex( "Coord_y" ) );

                if (nom_lugar != null) {
                    Visualizar(nom_lugar, "X: " + coordX + "-Y: " + coordY , String.valueOf( image ) );
                } else {
                    Visualizar( "null", "null", "null" );
                }
                Toast.makeText( this, "Consulta exitosa", Toast.LENGTH_LONG ).show();
            } else {
                Visualizar( "null", "null", null);
                Toast.makeText( this, "No se encontraron datos para la baliza", Toast.LENGTH_LONG ).show();
            }
        } catch (Exception e) {
            BaseDeDatos.CleanTableTemp( dataBaseHelper );
            Toast.makeText( this, e.toString(), Toast.LENGTH_LONG ).show();
            EstadoOriginal( true );
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
    }
    /*
        try {
            res = servicio.get();
            Log.i("Res: ",res);
            res=res.substring(1,res.length());
            res=res.substring(0,res.length()-1);
            String val[]=res.split(",");
            if(val[2].trim().equals("null")) {
                Visualizar(val[0].trim(), val[1].trim(), "null" );
            }
            else {
                Visualizar(val[0].trim(),val[1].trim(),"X: " + val[2].trim() + "-Y: " +
                        val[3].trim() );
            }
            Toast.makeText(this,res,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            BaseDeDatos.CleanTableTemp(dataBaseHelper);
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            EstadoOriginal(true);
            e.printStackTrace();
        }
    }*/

    /**
     * M. que carga la informacion contextual
     */
    private void llenarLista(String cadena, int index) {
        cadena = cadena.substring( 1, cadena.length() - 1 );
        String clases[] = cadena.split( "," );
        Log.i( "index", index + "" );
        switch (index) {
            case 1: {
                for (int i = 0; i < clases.length; i++) {
                    String dato[] = clases[i].split( ";" );
                    LugarAcceso registro = new LugarAcceso( dato[0], dato[1] );
                    lugar_accesos.add( registro );
                    info.setVisibility( View.VISIBLE );
                    info.setText( R.string.btn_acceso );
                    aulas_acceso = 2;
                    info_aula.setText( txt_aula.getText().toString().substring( 10 ) + desc );
                }
                break;
            }
            case 2: {
                if (cadena.indexOf( "Sin InformaciÃ3n" ) >= 0) {
                    info.setVisibility( View.INVISIBLE );
                } else {
                    for (int i = 0; i < clases.length; i++) {
                        String dato[] = clases[i].split( ";" );
                        Lugar registro = new Lugar( dato[0], dato[1] );
                        lugares.add( registro );
                        info.setVisibility( View.VISIBLE );
                        aulas_acceso = 1;
                        info_aula.setText( " " + registro.getName() + ": \n" + "" + registro.getDescripcion());
                    }
                }
                break;
            }
        }
    }

    /**
     * M. limpia la app
     */
    public void EstadoOriginal(boolean err) {
            txt_aula.setText("");
          //txt_coord.setText("");
            if(err){
                image.setImageResource(R.color.colorPrimaryDark);
            }else{
               image.setImageResource(R.color.gris);
            }
            info.setVisibility(View.INVISIBLE);
            lugares.clear();
            lugar_accesos.clear();
        }


    /**
     * M. visualiza los resultados en los textview y la imagen
     */
    private void Visualizar(String nom_lugar, String coord, String img) {

        String info_aula = null;
        Log.i( "lugar", nom_lugar );


        txt_aula.setText( R.string.localizacion);
        txt_aula.setText( txt_aula.getText().toString() + "" + nom_lugar.split( "\n" )[0] );

        Toast.makeText( this, nom_lugar.split( "\n" )[0], Toast.LENGTH_LONG ).show();


            Bitmap myBitmap = ConvertToImage( img );
            image.setImageBitmap( myBitmap );

      //  if (coord.equals( "null" )) {
      //     txt_coord.setText( "" );
      //   } else
      //      txt_coord.setText( coord );
        if (nom_lugar.indexOf( "L" ) == 0) {
            llenarLista( info_aula, 3 );
        } else if (nom_lugar.indexOf( "P" ) == 0) {
            desc = "\n" + nom_lugar.split( "\n" )[1];
            llenarLista( info_aula, 1 );
        }

         /**
         * Leer TexToSpeech
         */
          // toRead = getString(R.string.localizacion);
          // mTts = new TextToSpeech(this, IndoorScan.this);
    }
    /**
     * M. que descomprime la imagen de Base64
     */
    private Bitmap ConvertToImage(String img) {
        try {
            InputStream stream = new
                    ByteArrayInputStream( Base64.decode( img.getBytes(), Base64.DEFAULT ) );
            Bitmap bitmap = BitmapFactory.decodeStream( stream );
            Log.v( "Bitmap", "Image Converted" );
            return bitmap;
        } catch (Exception e) {
            EstadoOriginal( true );
            return null;
        }
    }

    /**
     * M. implementa un hilo encargado de escanear los RSSI de las balizas durante 5 segundos
     **/
    private void Escanear(boolean enable) {
        int cont = 0;
        EstadoOriginal( false );
        if (miBTAdapter.isEnabled()) {
            // fab.callOnClick();
            try {
                if (enable && !estado) {
                    mHandler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            estado = false;
                            miBTAdapter.stopLeScan( scan );
                            Localizar();
                            // button.setEnabled(true);
                            resView = "";
                            BaseDeDatos.CleanTableTemp( dataBaseHelper );
                            Hilo( true );
                        }
                    }, 60000 );
                    estado = true;
                    miBTAdapter.startLeScan( scan );
                    //button.setEnabled(false);
                    //progressDialog.setProgress(cont);
                    cont++;
                } else {
                    estado = false;
                    miBTAdapter.stopLeScan( scan );
                    //button.setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BaseDeDatos.CleanTableTemp( dataBaseHelper );
            }
        } else
            Toast.makeText( this, "Bluetooth Desactivado", Toast.LENGTH_LONG ).show();
    }

    /**
     * Distancia entre baliza y móvil
     */
    private double Distancia(double rssi) {
        double res = 0;
        double c1=1.0208,c2= 0.1384,c3=(-0.0208), txPower=-59;
        res = (c1) * Math.pow( (rssi / txPower), c2 ) + c3;
        return res;
    }

    /**
     * M. sobrescrito que escanea balizas
     */
    private BluetoothAdapter.LeScanCallback scan = new BluetoothAdapter.LeScanCallback() {
        @SuppressLint("StringFormatMatches")
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (device.getName() != null) {
                if (resView.equals( "" ) || resView.indexOf( device.getName() ) < 0) {
                    resView = resView + "\n" + device.getName() + ";" + Distancia( rssi );
                    boolean flag = BaseDeDatos.IntroTemp( dataBaseHelper, device.getName(), Distancia( rssi ) );
                    Toast.makeText( getApplicationContext(), device.getName(), Toast.LENGTH_LONG );
                    if (!flag) {
                        Toast.makeText( getApplicationContext(), "Error de DB", Toast.LENGTH_LONG ).show();
                    }
                }
            }
        }
    };

    /**
     * M. crea la cadena con los nombres de las balizas y sus valores RSSI
     */
    private String[] CrearArray() {
        String res[] = null;
        int cont = 0;
        try {
            SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
            if (db != null) {
                // c=BaseDeDatos.ConsultaEspecifica(dataBaseHelper,"Kontakt");
                c = BaseDeDatos.ConsultaDistancia( dataBaseHelper );
                if (c.moveToFirst()) {
                    res = new String[c.getCount()];
                    res[cont] = c.getString( 0 ) + ";" + c.getString( 1 );
                    cont++;
                    while (c.moveToNext()) {
                        res[cont] = c.getString( 0 ) + ";" + c.getString( 1 );
                        cont++;
                    }
                } else
                    Toast.makeText( this, "Error de BD", Toast.LENGTH_LONG ).show();
            } else
                Toast.makeText( this, "Error de BD", Toast.LENGTH_LONG ).show();
        } catch (Exception e) {
            Toast.makeText( this, "Error de Ejecucion", Toast.LENGTH_LONG ).show();
            BaseDeDatos.CleanTableTemp( dataBaseHelper );
            EstadoOriginal( true );
            e.printStackTrace();
        }
        return res;
    }

    /**
     * M. crea el dialog para la informacion del lugar
     */
    public void crearDialogoInfo(Lugar lugar) {
        DialogoInfo dialogo = new
                DialogoInfo( lugar.getName(), lugar.getDescripcion() );
        dialogo.show( getSupportFragmentManager(), "informacion" );
        android.app.Fragment frag =
                getFragmentManager().findFragmentByTag( "informacion" );
        if (frag != null) {
            getFragmentManager().beginTransaction().remove( frag ).commit();
        }
    }

    /**
     * M. crea el dialog para la informacion de los lugares cercanos al acceso a las aulas
     */
    public void crearDialogoAcceso() {
        DialogoAcceso dialogo = new DialogoAcceso( lugar_accesos );
        dialogo.show( getSupportFragmentManager(), "acceso" );
        android.app.Fragment frag = getFragmentManager().findFragmentByTag( "acceso" );
        if (frag != null) {
            getFragmentManager().beginTransaction().remove( frag ).commit();
        }
    }

    /**
     * M. que crea el dialogo de configuracion
     */
    public void crearDialogoConfig() {
        SharedPreferences pref = getSharedPreferences( "datos", Context.MODE_PRIVATE );
        DialogoConfig dialogo = new DialogoConfig( pref.getString( "tiempo", "60000" ) );
        dialogo.show( getSupportFragmentManager(), "config" );
        android.app.Fragment frag = getFragmentManager().findFragmentByTag( "config" );
        if (frag != null) {
            getFragmentManager().beginTransaction().remove( frag ).commit();
        }
    }

    /**
     * M. sobreescrito para la finalizacion del dialogo de informacion de las
     * configuracion de tiempo
     */
    @Override
    public void FinalizaConfig(int time) {
        ciclo = time;
        if (ciclo != -1) {
            SharedPreferences pref = getSharedPreferences( "datos",
                    Context.MODE_PRIVATE );
            SharedPreferences.Editor edit = pref.edit();
            edit.putString( "tiempo", time + "" );
            Log.i( "config", time + "" );
            edit.commit();
        }
    }

    /**
     * M. sobreescrito para la finalizacion del dialogo de informacion de los lugares
     */
    @Override
    public void FinalizaInfo() {
    }

    /**
     * M. sobreescrito para la finalizacion del dialogo de informacion de los lugares
     * cercanos de un acceso
     */
     @Override
     public void FinalizaAcceso() {

     }
   /**
     * Metodo TextToSpeech
     */
   /* @Override
    public void onInit(int status) {
        Locale loc = new Locale("es", "ES", "");
        mTts.setLanguage(loc);
        readOption();
    }
    public static void readOption() {
        if (MainActivity.flag_volumen) {
            mTts.speak(toRead, TextToSpeech.QUEUE_FLUSH, null);
        }
        if (toRead.equals("Volumen desactivado")) {
            mTts.speak(toRead, TextToSpeech.QUEUE_FLUSH, null);
        }
    }*/

}
