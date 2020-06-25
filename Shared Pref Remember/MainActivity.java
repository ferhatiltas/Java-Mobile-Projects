package com.ferhatiltas.sharedpreference_benihatirla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
            TextView txtHosgeldin;
            SharedPref sharedPref;
            Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHosgeldin=findViewById(R.id.txtHosgeldin);
        sharedPref=new SharedPref();
        txtHosgeldin.setText(sharedPref.getValue(context,"username"));
    }
}
