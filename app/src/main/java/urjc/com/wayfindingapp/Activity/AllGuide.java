package urjc.com.wayfindingapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import urjc.com.wayfindingapp.APIClient;
import urjc.com.wayfindingapp.APIInterface;
import urjc.com.wayfindingapp.Model.Guide;
import urjc.com.wayfindingapp.R;

//import android.support.v7.app.AppCompatActivity;


public class AllGuide extends AppCompatActivity {

    APIInterface apiInterface;
    List<Guide> guides = new ArrayList<>();

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_guide);

        listview = (ListView) findViewById(R.id.ListGuides);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        /**
         GET List Resources
         **/
        Call<List<Guide>> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<List<Guide>>() {
            @Override
            public void onResponse(Call<List<Guide>> call, Response<List<Guide>> response) {

                Log.d("TAG",response.code()+"");

                List<Guide> resource = response.body();

                for (Guide guide : resource) {
                    guides.add(guide);
                }

                ArrayAdapter adapter = new ArrayAdapter(AllGuide.this, android.R.layout.simple_list_item_1, guides);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Guide selectedItem = (Guide) parent.getItemAtPosition(position);

                        Intent myIntent = new Intent(AllGuide.this,AllOutdoorRoute.class);
                        myIntent.putExtra("outdoorGuide", selectedItem);
                        startActivity(myIntent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Guide>> call, Throwable t) {
                call.cancel();
            }
        });

        ImageButton volver = (ImageButton)findViewById(R.id.volver);
        volver.setContentDescription(volver.getContentDescription()+". Está en gestión de rutas");
        volver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }

        });

    }
}

