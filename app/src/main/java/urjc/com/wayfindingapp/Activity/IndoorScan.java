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
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import urjc.com.wayfindingapp.Model.Lugar;
import urjc.com.wayfindingapp.R;

public class IndoorScan extends AppCompatActivity implements DialogoInfo.Info, DialogoAcceso.Acceso {

    List<Lugar> lugares = new ArrayList<>();

    //FloatingActionButton fab;
    public TextView txt_aula, info_aula;
    //public TextView txt_coord;
    private ImageView image;
    public static BluetoothAdapter miBTAdapter = null;
    public Button info;

    private static final String ALL_BEACONS_REGION = "AllBeaconsRegion";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static DataBaseHelper dataBaseHelper;
    public static String dbNombre = "bluetooth", desc = "";
    public static int dbVersion = 1;
    public static Handler mHandler;
    public static boolean estado = false, estado1 = false, descargado = false;
    public static String resView = "";
    public static Cursor c;
    ArrayList<LugarAcceso> lugar_accesos = new ArrayList<LugarAcceso>();
    public static int ciclo = 60000;
    public static int aulas_acceso = 1;

    public static TextToSpeech mTts;
    public static String toRead;


    /**
     * Metodo sobrescrito que inicializa la app y sus elementos graficos
     */
    @SuppressLint({"ResourceType", "StringFormatMatches"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor_scan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        info = findViewById(R.id.info);
        txt_aula = findViewById(R.id.txt_aula);
        image = findViewById(R.id.image);
        info_aula = findViewById(R.id.info_aula);

        dataBaseHelper = new DataBaseHelper(this, dbNombre, null, dbVersion);

        // Check if the location permissions are granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE
            );
        }

        /**
         Llamada a la clase BluetoothAdapter
         */
        miBTAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!miBTAdapter.isEnabled()) {
            Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOnIntent, 0);
        }

        mHandler = new Handler();

        info.setOnClickListener(v -> {
            if (aulas_acceso == 1) {
                Lugar registro = (Lugar) lugares.get(0);
                crearDialogoInfo(registro);
            } else
                crearDialogoAcceso();
        });

        if (miBTAdapter.isEnabled()) {
            SharedPreferences pref = getSharedPreferences("datos", Context.MODE_PRIVATE);
            ciclo = Integer.parseInt(Objects.requireNonNull(pref.getString("tiempo", "60000")));
            Escanear(true);
        } else {
            EstadoOriginal(false);
        }
    }

    /**
     * M. sobrescrito que genera el menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void Reiniciar() {
        DataBaseHelper.CleanTableTemp(dataBaseHelper);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    /**
     * M. que aplica el hilo encargado de actualizar la localizacion segun el ciclo
     * programado
     */
    public void Hilo(boolean enable) {
        if (enable && !estado1) {
            mHandler.postDelayed(() -> {
                estado1 = false;
                Escanear(true);
            }, ciclo);
            estado1 = true;
        } else {
            estado1 = false;
        }
    }

    private void Localizar() {
        String[] nombreDistancia = CrearArray();
        StringBuilder baliza = new StringBuilder();

        for (String s : nombreDistancia) {
            baliza.append(s).append(":");
        }

        Log.i("Baliza: ", baliza.substring(0, baliza.length() - 1));

        // Aquí se realiza la consulta a la base de datos para obtener la información de la baliza
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        String query = "SELECT Name, Coord_x, Coord_y FROM Baliza WHERE IDbaliza = ?";
        Cursor cursor = db.rawQuery(query, new String[]{baliza.toString()});

        try {
            if (cursor.moveToFirst()) {
                String nom_lugar = cursor.getString(cursor.getColumnIndex("Name"));
                int coordX = cursor.getInt(cursor.getColumnIndex("Coord_x"));
                int coordY = cursor.getInt(cursor.getColumnIndex("Coord_y"));

                if (nom_lugar != null) {
                    Visualizar(nom_lugar, "X: " + coordX + "-Y: " + coordY, String.valueOf(image));
                } else {
                    Visualizar("null", "null", "null");
                }
                Toast.makeText(this, "Consulta exitosa", Toast.LENGTH_LONG).show();
            } else {
                Visualizar("null", "null", null);
                Toast.makeText(this, "No se encontraron datos para la baliza", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            DataBaseHelper.CleanTableTemp(dataBaseHelper);
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            EstadoOriginal(true);
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }
    }
    /* codigo antiguo conexion por servidor
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
        cadena = cadena.substring(1, cadena.length() - 1);
        String clases[] = cadena.split(",");
        Log.i("index", index + "");
        switch (index) {
            case 1: {
                for (int i = 0; i < clases.length; i++) {
                    String dato[] = clases[i].split(";");
                    LugarAcceso registro = new LugarAcceso(dato[0], dato[1]);
                    lugar_accesos.add(registro);
                    info.setVisibility(View.VISIBLE);
                    info.setText(R.string.btn_acceso);
                    aulas_acceso = 2;
                    info_aula.setText(txt_aula.getText().toString().substring(10) + desc);
                }
                break;
            }
            case 2: {
                if (cadena.indexOf("Sin InformaciÃ3n") >= 0) {
                    info.setVisibility(View.INVISIBLE);
                } else {
                    for (int i = 0; i < clases.length; i++) {
                        String dato[] = clases[i].split(";");
                        Lugar registro = new Lugar(dato[0], dato[1]);
                        lugares.add(registro);
                        info.setVisibility(View.VISIBLE);
                        aulas_acceso = 1;
                        info_aula.setText(" " + registro.getName() + ": \n" + "" + registro.getDescripcion());
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
        if (err) {
            // image.setImageResource( R.color.colorPrimaryDark );
        } else {
            // image.setImageResource( R.color.gris );
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
        Log.i("lugar", nom_lugar);

        txt_aula.setText(R.string.localizacion);
        txt_aula.setText(txt_aula.getText().toString() + "" + nom_lugar.split("\n")[0]);

        Toast.makeText(this, nom_lugar.split("\n")[0], Toast.LENGTH_LONG).show();

        if (nom_lugar.indexOf("L") == 0) {
            llenarLista(info_aula, 3);
        } else if (nom_lugar.indexOf("P") == 0) {
            desc = "\n" + nom_lugar.split("\n")[1];
            llenarLista(info_aula, 1);
        }
    }

    /**
     * M. implementa un hilo encargado de escanear los RSSI de las balizas durante 5 segundos
     **/
    private void Escanear(boolean enable) {
        EstadoOriginal(false);
        if (miBTAdapter.isEnabled()) {
            try {
                if (enable && !estado) {
                    mHandler.postDelayed(
                            () -> {
                                estado = false;
                                miBTAdapter.stopLeScan(scan);
                                Localizar();
                                resView = "";
                                DataBaseHelper.CleanTableTemp(dataBaseHelper);
                                Hilo(true);
                            },
                            ciclo
                    );
                    estado = true;
                    miBTAdapter.startLeScan(scan);
                } else {
                    estado = false;
                    miBTAdapter.stopLeScan(scan);
                }
            } catch (Exception e) {
                e.printStackTrace();
                dataBaseHelper.CleanTableTemp(dataBaseHelper);
            }
        } else
            Toast.makeText(this, "Bluetooth Desactivado", Toast.LENGTH_LONG).show();
    }

    /**
     * Distancia entre baliza y móvil
     */
    private double Distancia(double rssi) {
        double res;
        double c1 = 1.0208, c2 = 0.1384, c3 = (-0.0208), txPower = -59;
        res = (c1) * Math.pow((rssi / txPower), c2) + c3;
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
                if (resView.equals("") || !resView.contains(device.getName())) {
                    double distanciaDispositivo = Distancia(rssi);
                    resView = resView + "\n" + device.getName() + ";" + distanciaDispositivo;
                    boolean flag = DataBaseHelper.IntroTemp(dataBaseHelper, device.getName(), distanciaDispositivo);
                    Toast.makeText(getApplicationContext(), device.getName(), Toast.LENGTH_LONG).show();
                    if (!flag) {
                        Toast.makeText(getApplicationContext(), "Error de DB", Toast.LENGTH_LONG).show();
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
                c = DataBaseHelper.ConsultaDistancia(dataBaseHelper);
                if (c.moveToFirst()) {
                    res = new String[c.getCount()];
                    res[cont] = c.getString(0) + ";" + c.getString(1);
                    cont++;
                    while (c.moveToNext()) {
                        res[cont] = c.getString(0) + ";" + c.getString(1);
                        cont++;
                    }
                } else
                    Toast.makeText(this, "Error de BD", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(this, "Error de BD", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error de Ejecucion", Toast.LENGTH_LONG).show();
            dataBaseHelper.CleanTableTemp(dataBaseHelper);
            EstadoOriginal(true);
            e.printStackTrace();
        }
        return res;
    }

    /**
     * M. crea el dialog para la informacion del lugar
     */
    public void crearDialogoInfo(Lugar lugar) {
        DialogoInfo dialogo = new
                DialogoInfo(lugar.getName(), lugar.getDescripcion());
        dialogo.show(getSupportFragmentManager(), "informacion");
        android.app.Fragment frag =
                getFragmentManager().findFragmentByTag("informacion");
        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
        }
    }

    /**
     * M. crea el dialog para la informacion de los lugares cercanos al acceso a las aulas
     */
    public void crearDialogoAcceso() {
        DialogoAcceso dialogo = new DialogoAcceso(lugar_accesos);
        dialogo.show(getSupportFragmentManager(), "acceso");
        android.app.Fragment frag = getFragmentManager().findFragmentByTag("acceso");
        if (frag != null) {
            getFragmentManager().beginTransaction().remove(frag).commit();
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
