package urjc.com.wayfindingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import urjc.com.wayfindingapp.Model.Guide;
import urjc.com.wayfindingapp.Model.OutdoorGuide;
import urjc.com.wayfindingapp.R;

//import android.support.v7.app.AppCompatActivity;

public class AllOutdoorRoute extends AppCompatActivity {

    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_outdoor_route);

        listview = (ListView) findViewById(R.id.ListOutdoor);

        Guide guide = (Guide) this.getIntent().getSerializableExtra("outdoorGuide");
        Log.d("listOuterGuide",guide.getOutdoorGuide().size()+"");
        ArrayAdapter adapter = new ArrayAdapter(AllOutdoorRoute.this, android.R.layout.simple_list_item_1, guide.getOutdoorGuide());
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OutdoorGuide selectedItem = (OutdoorGuide) parent.getItemAtPosition(position);

                Intent myIntent = new Intent(AllOutdoorRoute.this,MapsActivity.class);
                myIntent.putExtra("outdoorGuide", selectedItem);
                startActivity(myIntent);
            }
        });

       // Log.d("listOuterGuide",listOuterGuide[0]+"");
    }
}
