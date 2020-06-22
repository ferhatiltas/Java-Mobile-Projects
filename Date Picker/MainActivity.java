package com.ferhatiltas.datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnTarihDegistir;
    TextView txtTarih;
    DatePicker dpSonuc;
    StringBuilder sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTarihDegistir=findViewById(R.id.btnTarihDegistir);
        txtTarih=findViewById(R.id.txtTarih);
        dpSonuc=findViewById(R.id.dpSonuc);
        sonuc=new StringBuilder();
        btnTarihDegistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sonuc.delete(0,sonuc.length());//her tıkladığında tekrar tekrar tarihi yazmasın diye
                Calendar simdikiZaman=Calendar.getInstance();
                int yil=simdikiZaman.get(Calendar.YEAR);//Güncel yıl ay ve gün kodları
                int ay=simdikiZaman.get(Calendar.MONTH);
                int gun=simdikiZaman.get(Calendar.DAY_OF_MONTH);

          //      sonuc.append(gun).append("-").append(ay+1).append("-").append(yil);//değerleri atıyoruz
          //      txtTarih.setText(sonuc);//değerleri textviewe atıyoruz
           //     dpSonuc.init(yil,ay,gun,null); buda çalışır ama aşağıda datepikerde tarih değişince izlenece olayları ekleyeceğiz
                dpSonuc.updateDate(1066,12,26);
                dpSonuc.init(yil, ay, gun, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        sonuc.delete(0,sonuc.length());//her tıkladığında tekrar tekrar tarihi yazmasın diye

                        sonuc.append(year).append("-").append(monthOfYear+1).append("-").append(dayOfMonth);//değerleri atıyoruz
                        txtTarih.setText(sonuc);//değerleri textviewe atıyoruz
                    }
                });
            }
        });
    }
}
