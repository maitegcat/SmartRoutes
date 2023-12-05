package urjc.com.wayfindingapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import urjc.com.wayfindingapp.R;

//import android.support.v7.app.AppCompatActivity;

//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.SQLException

public class MainActivity extends AppCompatActivity /*implements TextToSpeech.OnInitListener*/ {

    public static boolean flag_volumen=true;
    private static TextToSpeech mTts;
    public static String toRead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle(R.string.title_activity_main);

        Button btn = (Button)findViewById(R.id.buttonAllGuide);
        Button btnImage = (Button)findViewById(R.id.buttonIndoor);
   //     Button btnImage1 = (Button)findViewById(R.id.buttonImage);
    //    Button btnImage2 = (Button)findViewById(R.id.buttonImage1);
//app
//        toRead =getString( R.string.mensaje_pantalla_inicio);
//        mTts = new TextToSpeech(this, MainActivity.this);


            btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

            Intent myIntent = new Intent(MainActivity.this, AllGuide.class);
            startActivity(myIntent);
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this, IndoorScan.class);
                startActivity(myIntent);
            }
        });
    /*    btnImage1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String message = getString( R.string.message ) ;
                Intent myIntent = new Intent(MainActivity.this, BalizasScan.class);
                startActivity(myIntent);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        });*/
       /* btnImage2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String message = getString( R.string.message ) ;
                Intent myIntent = new Intent(MainActivity.this, ListaSmartRoutes.class);
                startActivity(myIntent);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        });*/

    }
   /* *//**
     * Metodo TextToSpeech
     *//*

    public void onInit(int status) {
        // TODO Auto-generated method stub
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
