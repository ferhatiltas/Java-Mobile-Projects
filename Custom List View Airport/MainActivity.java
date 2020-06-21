package com.ferhatiltas.customlistview_editfilter_havalimani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etFilter;
    ListView lst;
    ArrayList<Airport> airports =new ArrayList<>();
    ArrayList<Airport> tempList =new ArrayList<>();
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFilter=findViewById(R.id.etFilter);
        lst=findViewById(R.id.lst);
        airports.add(new Airport("Atatürk Havalimanı ","AHL ","İstanbul ","Türkiye",(R.mipmap.ic_launcher)));
        airports.add(new Airport("Adnan Menderes Havalimanı ","ADB ","İZMİR ","Türkiye",(R.mipmap.ic_launcher)));
        airports.add(new Airport("BAtman Havalimanı ","BAL ","BAtman ","Türkiye",(R.mipmap.ic_launcher)));
        airports.add(new Airport("ESenboğa Havalimanı ","ESB ","Ankkara ","Türkiye",(R.mipmap.ic_launcher)));
        CustomAdapter adapter=new CustomAdapter(airports,context);
        lst.setAdapter(adapter);
        etFilter.addTextChangedListener(new TextWatcher() {
            //EditTexte girilen metine göre listedeki elemanlar listelenir
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tempList.clear(); //clear yapılır ki edit tekte girilen her bir değer için tekrar tekrar dizi artmasın
                s=s.toString().toUpperCase();
                for (int i=0;i<airports.size();i++){
                    Airport temPort=airports.get(i);
                    if (temPort.getCountry().toUpperCase().contains(s)||
                            temPort.getCode().toUpperCase().contains(s)||
                            temPort.getCity().toUpperCase().contains(s)||
                            temPort.getName().toUpperCase().contains(s)){
                        tempList.add(temPort);
                    }
                }
                if (tempList != null && tempList.size() > 0) {
                    CustomAdapter adapter1=new CustomAdapter(tempList,context);
                    lst.setAdapter(adapter1);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
