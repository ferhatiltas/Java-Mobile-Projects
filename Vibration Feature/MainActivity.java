package com.ferhatiltas.vibratesinifi_titresim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonVibrate1, buttonVibrate2, buttonVibrate3;
    Vibrator vibrator;//Başlamadan önce manifest dosysında izin alın

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        buttonVibrate1 = findViewById(R.id.buttonVibrate);
        buttonVibrate2 = findViewById(R.id.buttonVibrate2);
        buttonVibrate3 = findViewById(R.id.buttonVibrate3);
        buttonVibrate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(500);
            }
        });
        buttonVibrate2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {//Üzerine basılı ise
                        vibrator.vibrate(100);//1 saniye titreştir
                } else if (action == MotionEvent.ACTION_UP) {//elimizi buttondan kaldırdığımızda titreşimdursun
                    vibrator.cancel();//titreşim durdurulacak

                }
                return true;
            }
        });
        buttonVibrate3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {//Üzerine basılı ise///// }

                    long[] pat={100,100,100,100,5000};//geçikme,titreşim,geçikme,titreşim,on-off saniyeleri
                    vibrator.vibrate(pat,0);// repeat=tekrar etme 0 koysan sürekli tekrar eder
                }
                else if (action == MotionEvent.ACTION_UP) {//elimizi buttondan kaldırdığımızda titreşimdursun
                    vibrator.cancel();//titreşim durdurulacak

                }
                return true;

            }
        });
    }
}
