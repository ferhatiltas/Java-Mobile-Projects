package com.ferhatiltas.sqlite_ekleguncellesil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // String DB_PATH=" /stroge/emulated/0/";

    ListView listView;
    Context context = this;
    SQLite db = new SQLite(context);
    List<Kitap> list;
    ArrayAdapter<String> mAdapter;//ekranda gözükme işlemi için adapter kullanııyoruz
    EditText txtYazarDE, txtBaslikDE;
    Button btnEkle, btnYedekAl;
    static final String DB_PATH = "/data/data/com.ferhatiltas.sqlite_ekleguncellesil/databases/KitapDB/"; // Veritabanı Yedek Alma (VYA) işlemi için eklendi
    static final String DB_Name = "KitapDB"; // (VYA)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listemiz);
        txtBaslikDE = findViewById(R.id.txtBaslikDE);
        txtYazarDE = findViewById(R.id.txtYazarDE);
        btnEkle = findViewById(R.id.btnEkle);
        btnYedekAl = findViewById(R.id.btnYedekAl);
        System.out.println(db.getDatabaseName());


       db.getWritableDatabase(); //uygulama kapanıp açılsada en son yaptığım değişiklikler kayıt olsun (database_VERSION = 2) yap



        list = db.kitaplariGetir();
        List<String> listBaslik = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listBaslik.add(i, list.get(i).getBaslik());// listede sadece kitapların isimleri gözüksün diye bu işlemi yapıyrozu

        }
        mAdapter = new ArrayAdapter<>(context, R.layout.satir_layout, R.id.listMetin, listBaslik);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Listede herhangi bir elemana tıklandıpında diğer aktiviteye geçsin
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, KitapActivity.class);
                intent.putExtra("kitap", list.get(position).getId());
                Log.i("idimiz", String.valueOf(list.get(position).getId()));
                startActivityForResult(intent, 1); //ForResult kullanmamızın nedeni kitap aktivitede bir kitabı sildiğimizde geri gelip önce aktiviteye gitmesi içindir


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //yukardaki kod parçacığını kopyalayıp buraya yapıştıracağız temel amaç listviewde varolan, eklenenleri veya silinenler olduğunda yeni  olan kitapları tekrar göstermesidir
        list = db.kitaplariGetir();
        List<String> listBaslik = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listBaslik.add(i, list.get(i).getBaslik());// listede sadece kitapların isimleri gözüksün diye bu işlemi yapıyrozu

        }
        mAdapter = new ArrayAdapter<>(context, R.layout.satir_layout, R.id.listMetin, listBaslik);
        listView.setAdapter(mAdapter);
        // kod parçacığı bu kadar
    }

    public void btnEkle_onClick(View view) {
        String baslikIsmi = txtBaslikDE.getText().toString();
        String yazarIsmi = txtYazarDE.getText().toString();
        db.KitapEkle(new Kitap(baslikIsmi, yazarIsmi));

        //yukardaki kod parçacığını kopyalayıp buraya yapıştıracağız temel amaç listviewde varolan, eklenenleri veya silinenler olduğunda yeni  olan kitapları tekrar göstermesidir
        list = db.kitaplariGetir();
        List<String> listBaslik = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listBaslik.add(i, list.get(i).getBaslik());// listede sadece kitapların isimleri gözüksün diye bu işlemi yapıyrozu

        }
        mAdapter = new ArrayAdapter<>(context, R.layout.satir_layout, R.id.listMetin, listBaslik);
        listView.setAdapter(mAdapter);
        // kod parçacığı bu kadar
        txtYazarDE.setText("");
        txtBaslikDE.setText("");
    }

    public void btnYedekAl_onClick(View vie) {  // (VYA)
       String inFileName = DB_PATH + DB_Name;
       File dbFile = new File(inFileName);
       FileInputStream fis = null;
       try {
           fis = new FileInputStream(inFileName);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       String outFileName = Environment.getExternalStorageState() + "database_copy.db";
       OutputStream myOutPut = null;
       try {
           myOutPut = new FileOutputStream(outFileName);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
       byte[] buffer = new byte[1024];
       int length;
       try {
           while ((length = fis.read(buffer)) > 0) {// içerde veri olduğu sürece
               myOutPut.write(buffer, 0, length);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       try {
           myOutPut.flush();
           myOutPut.close();
           fis.close();
           Toast.makeText(context, "Yedek Alındı.", Toast.LENGTH_SHORT).show();
       } catch (IOException e) {
           e.printStackTrace();
       }


    }


}


