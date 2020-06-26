package com.ferhatiltas.sqlite_ekleguncellesil;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KitapActivity extends AppCompatActivity {
    TextView txtBaslik, txtYazar;
    EditText txtBaslikD, txtYazarD;
    Button btnSil, btnGuncelle;
    SQLite db;
    Kitap seciliKitap;

    @Override

    public void onCreate(@Nullable Bundle s) {

        super.onCreate(s);
        setContentView(R.layout.activity_kitap);
        idTanimlari();
        db = new SQLite(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("kitap", -1);
        seciliKitap = db.kitaplar(id);
        txtBaslik.setText(seciliKitap.getBaslik());
        txtYazar.setText(seciliKitap.getYazar());

    }

    public void idTanimlari() {
        btnSil = findViewById(R.id.btnSil);
        txtYazar = findViewById(R.id.txtYazar);
        txtBaslik = findViewById(R.id.txtBaslik);
        txtBaslikD = findViewById(R.id.txtBaslikD);
        txtYazarD = findViewById(R.id.txtYazarD);
        btnGuncelle = findViewById(R.id.btnGuncelle);
    }


    public void btnSil_onClick(View v) {
        db.KitapSil(seciliKitap);
        finish();
    }

    public void btnGuncelle_onClick(View v) {
        seciliKitap.setBaslik(txtBaslikD.getText().toString());
        seciliKitap.setYazar(txtYazarD.getText().toString());
        db.kitapGuncelle(seciliKitap);
        finish();
    }
}
