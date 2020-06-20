package com.ferhatiltas.artbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> nameArray;
    ArrayList<Integer> idArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        nameArray =new ArrayList<String>();
        idArray =new ArrayList<Integer>();
        ArrayAdapter arrayAdapter;

         arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,nameArray);
         listView.setAdapter(arrayAdapter);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//Listenere tıklanma özelliği veriyoruz
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//tıklayınce ne olsun
                 Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                 intent.putExtra("artId",idArray.get(position));
                 intent.putExtra("info","old"); //İkinci ekrana geçmek için old ve altta olan new elemanlarını ekladıik maksat hangi clicck ile ikinci ekrana geçtiğimiz belli olsun
                 startActivity(intent);

             }
         });

        getData();
    }
    public void getData(){ //SQLite tablo yoksa oluşturacak varsa açacak az önce yaptığımız için onu açacak
        try {
            SQLiteDatabase database=this.openOrCreateDatabase("arts",MODE_PRIVATE,null);
            Cursor cursor =database.rawQuery("SELECT*FROM arts",null);
            int nameix=cursor.getColumnIndex("artName");
            int idix=cursor.getColumnIndex("id");
            while(cursor.moveToNext()){
                nameArray.add(cursor.getString(nameix));
                idArray.add(cursor.getInt(idix));
            }
            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_art,menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.add_art_item){
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
