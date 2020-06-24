package com.ferhatiltas.sharedpreference_basit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CheckBox chk1;
    EditText etIsim;
    Button btnSave,btnDelete,btnRemove,btnGo;
    String text;
    SharedPref sharedPref;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idTanimla();
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnGo.setOnClickListener(this);
    }

    public void idTanimla() {
        chk1=findViewById(R.id.idCheckBox);
        etIsim=findViewById(R.id.etIsim);
        btnSave=findViewById(R.id.idSave);
        btnDelete=findViewById(R.id.idDelete);
        btnRemove=findViewById(R.id.idKaldır);
        btnGo=findViewById(R.id.idAktivite);
        sharedPref=new SharedPref();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.idSave:
                if (chk1.isChecked()) {
                    text=etIsim.getText().toString();
                    sharedPref.save(context,text);
                    Toast.makeText(context, "Kayıt Edildi", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.idDelete:
                sharedPref.clear(context);
                Toast.makeText(context, "Silindi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idKaldır:
                sharedPref.remove(context);
                Toast.makeText(context, "Kaldırıldı", Toast.LENGTH_SHORT).show();
                break;
            case R.id.idAktivite:
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
}
