package urjc.com.wayfindingapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.fragment.app.DialogFragment;

import urjc.com.wayfindingapp.R;

public class DialogoConfig extends DialogFragment {
    RadioButton opt1,opt2,opt3,opt4,opt5,opt6;
    Button boton,cancelar;
    Config interfaz=null;
    String actual;
    public interface Config {
        void FinalizaConfig(int time);
    }
    public DialogoConfig(String current){
        actual=current;
    }
    /**
    Metodo sobreescrito que inicializa el dialogo y sus componentes graficos
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_dialogo_config,container);
        opt1 = (RadioButton)view.findViewById(R.id.rd_a);
        opt2 = (RadioButton)view.findViewById(R.id.rd_b);
        opt3 = (RadioButton)view.findViewById(R.id.rd_c);
        opt4 = (RadioButton)view.findViewById(R.id.rd_d);
        opt5 = (RadioButton)view.findViewById(R.id.rd_0);
        opt6 = (RadioButton)view.findViewById(R.id.rd_e);
        boton = (Button)view.findViewById(R.id.btn);
        cancelar = (Button)view.findViewById(R.id.btn_can);
        switch (actual){
            case "10000":{
                opt1.setChecked(true);
                break;
            }
            case "30000":{
                opt2.setChecked(true);
                break;
            }
            case "60000":{
                opt3.setChecked(true);
                break;
            }
            case "300000":{
                opt4.setChecked(true);
                break;
            }
            case "5000":{
                opt4.setChecked(true);
                break;
            }
            case "600000":{
                opt4.setChecked(true);
                break;
            }
            default:{
                opt1.setChecked(true);
                break;
            }
        }

    /**
    Metodo sobreescrito asociado al evento click del boton de guardar
    */
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz =(Config) getActivity();
                interfaz.FinalizaConfig(seleccion());
                dismiss();
            }
        });
    /**
    Metodo sobreescrito asociado al evento click del boton regresar
    */
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interfaz =(Config) getActivity();
                interfaz.FinalizaConfig(-1);
                dismiss();
            }
        });
        getDialog().setTitle("Configurar Tiempo");
        return view;
    }
    /**
    Metodo encargado de obtenr el tiempo en base a la opcion escogida
    */
    public int seleccion(){
        int time = 0;
        if(opt1.isChecked()){
            time=10000;
        }else if(opt2.isChecked()){
            time=30000;
        }else if(opt3.isChecked()){
            time=60000;
        }else if(opt4.isChecked()){
            time=300000;
        }else if(opt5.isChecked()){
            time=5000;
        }else if(opt6.isChecked()){
            time=600000;
        }
        return time;
    }
}
