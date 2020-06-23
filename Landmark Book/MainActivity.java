package com.ferhatiltas.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);
        final ArrayList<String> Names=new ArrayList<>();
        Names.add("Pisa");
        Names.add("Colleseo");
        Names.add("Eiffel");
        Names.add("London Brige");

        final ArrayList<String> countryNames=new ArrayList<>();
        countryNames.add("İtaly");
        countryNames.add("İtaly");
        countryNames.add("France");
        countryNames.add("United Kingdom");

        Bitmap pisa= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        Bitmap colosseum= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.colosseum);
        Bitmap eyfel= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eyfel);
        Bitmap london= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.london);

        final ArrayList<Bitmap> LandmarkImage=new ArrayList<>();
        LandmarkImage.add(pisa);
        LandmarkImage.add(colosseum);
        LandmarkImage.add(eyfel);
        LandmarkImage.add(london);

        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,Names);
        listView.setAdapter(arrayAdapter ); //ArrayLİst ve ArrayAdapteri birleştirip listeleri oluşturyoruz.

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
             //   System.out.println(Names.get(position));
               // System.out.println(countryNames.get(position));
                Intent intent=new Intent(getApplicationContext(),ikinciKapi.class);
                intent.putExtra("Name",Names.get(i));
                intent.putExtra("Country",countryNames.get(i));

                singleton singletonn=singleton.getInstance();
                singletonn.setChosenImage(LandmarkImage.get(i));


                startActivity(intent);

            }
        });
    }
}
