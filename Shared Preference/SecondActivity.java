package com.ferhatiltas.sharedpreference_basit;

import android.app.AppComponentFactory;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView text;
    SharedPref sharedPref;
    String donenDeger;
    Context context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        text = findViewById(R.id.idText);
        sharedPref=new SharedPref();
       donenDeger= sharedPref.getValue(context);
        text.setText(donenDeger);


    }
}
