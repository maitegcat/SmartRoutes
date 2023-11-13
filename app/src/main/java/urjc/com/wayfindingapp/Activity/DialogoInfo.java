package urjc.com.wayfindingapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import urjc.com.wayfindingapp.R;

public class DialogoInfo extends DialogFragment {
    TextView text1,text2;
    Button boton;
    Info interfaz=null;
    HashMap<String,String> datos = new HashMap<>();
    ListView lista;
    public interface Info {
        void FinalizaInfo();
    }
    public DialogoInfo(String name, String descripcion){

        datos.put("Nombre:",name+"\n");
        datos.put("Descripcion:",descripcion+"\n");
    }

    /**
    Metodo sobreescrito que inicializa el dialogo y sus componentes graficos
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogo_info,container);
        lista = (ListView)view.findViewById(R.id.lista);
        boton = (Button)view.findViewById(R.id.boton);
        List <HashMap<String, String>> listItems = new ArrayList <>();
        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), listItems,
                R.layout.list_item,
                new String[]{"Primera Linea", "Segunda Linea"},
                new int[]{R.id.text1, R.id.text2});
        Iterator it = datos.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap <String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("Primera Linea", pair.getKey().toString());
            resultsMap.put("Segunda Linea", pair.getValue().toString());
            listItems.add(resultsMap);
        }
        lista.setAdapter(adapter);
    /**
    Metodo sobreescrito asociado al evento click del boton regresar
    */
        boton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
                interfaz =(Info) getActivity();
                interfaz.FinalizaInfo();
                dismiss();
            }
        });
        getDialog().setTitle("Datos del Lugar");
        return view;
    }
}
