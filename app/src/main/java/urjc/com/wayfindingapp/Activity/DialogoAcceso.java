package urjc.com.wayfindingapp.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import urjc.com.wayfindingapp.R;

public class DialogoAcceso extends DialogFragment {
        Button boton;
        Acceso interfaz=null;
        ListView nombres;
        HashMap <String,String> datos = new HashMap<>();
        public interface Acceso {
            void FinalizaAcceso();
        }
        public DialogoAcceso(ArrayList <LugarAcceso> lugares){
            for(int i=0;i<lugares.size();i++){
                datos.put(lugares.get(i).lugar,lugares.get(i).tipo+"\n");
            }
        }
        /**
        Metodo sobreescrito que inicializa el dialogo y sus componentes graficos
        */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                savedInstanceState) {
            View view = inflater.inflate( R.layout.fragment_dialogo_acesso,container);
            nombres = (ListView)view.findViewById(R.id.lista);
            boton = (Button)view.findViewById(R.id.boton);
            List <HashMap<String, String>> listItems = new ArrayList<>();
            SimpleAdapter adapter = new SimpleAdapter(view.getContext(), listItems,
                    R.layout.list_item,
                    new String[]{"First Line", "Second Line"},
                    new int[]{R.id.text1, R.id.text2});
            Iterator it = datos.entrySet().iterator();
            while (it.hasNext())
            {
                HashMap<String, String> resultsMap = new HashMap<>();
                Map.Entry pair = (Map.Entry)it.next();
                resultsMap.put("First Line", pair.getKey().toString());
                resultsMap.put("Second Line", pair.getValue().toString());
                listItems.add(resultsMap);
            }
            nombres.setAdapter(adapter);

            /**
            Metodo sobreescrito asociado al evento click del boton de regreso
            */
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interfaz =(Acceso) getActivity();
                    interfaz.FinalizaAcceso();
                    dismiss();
                }
            });
            getDialog().setTitle("Lugares Cercanos");
            return view;
        }
    }
