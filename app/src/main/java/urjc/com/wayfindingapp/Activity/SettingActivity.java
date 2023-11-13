package urjc.com.wayfindingapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import urjc.com.wayfindingapp.R;

public class SettingActivity extends DialogFragment {
    EditText time;
    Button boton;
   // LoginActivity.Login interfaz=null;
    public interface Setting {
        void FinalizaSetting(String cant);
    }
    /**
    Metodo sobrescrito que inicializa el dialgo y sus componentes
    */
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate( R.layout.activity_setting, container);
        super.onCreate(savedInstanceState);
        time = (EditText) view.findViewById(R.id.time);
        boton = (Button) view.findViewById(R.id.btn_time);
/**
Metodo sobrescrito asociado al evento click del boton
*/
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    interfaz = (LoginActivity.Login) getActivity();
             //   interfaz.FinalizaLogin(Integer.parseInt(time.getText().toString())+"");
                dismiss();
            }
        });
        getDialog().setTitle("Configurar Tiempo");
        return view;
    }
}
