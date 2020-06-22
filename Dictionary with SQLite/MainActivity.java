package com.ferhatiltas.dbbrowsersqlitedenandroide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    //Başlamadan önce DB Browser for SQLite de yaptığımız tabloyu kopyalayı app>assets> yapıştır yapmalıyız


        EditText trEditText,enEditText;
        Button btnCevir;
        DatabaseHelper dbHelper;
        Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enEditText=findViewById(R.id.enEditText);
        trEditText=findViewById(R.id.trEditText);
        btnCevir=findViewById(R.id.btnCevir);
        dbHelper=new DatabaseHelper(context);
        try {
            dbHelper.createDatebase();
            dbHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnCevir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String turkceKelimme=trEditText.getText().toString();
                Cursor crs=dbHelper.getDatabase().query("kelimeler",new String[]{"kelimeEn"},"kelimeTr = ?",
                        new String[] {turkceKelimme},null,null,null,null);
                if (crs.getCount() > 0) {
                    //cursorun icinde bilgi varsa
                        crs.moveToFirst();
                        String ingilizceKelime=crs.getString(crs.getColumnIndex("kelimeEn"));
                        enEditText.setText(ingilizceKelime);
                }else {
                    Toast.makeText(context, "Kelimenin karşılığıg bulunamadı.", Toast.LENGTH_SHORT).show();
                trEditText.getText().clear();
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.close(); // uygulama kapatıldığında database de kapansın bunu herzaman yap
    }
}
