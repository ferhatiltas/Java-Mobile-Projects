package com.ferhatiltas.countdowntimer_gerisayimsayaci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        TextView text;
        CountDownTimer countDownTimer;
        ProgressBar progressBar;
        Button start , stop;
    MyCountDownTimer myCountDownTimer;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*

        // Birinci uygulama
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.text);
        countDownTimer=new CountDownTimer(10000,2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // her tetiklendiğinde yapılması gereken işler
                text.setText("Kalan Süre: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                // iiş bittğinde
                text.setText("ook");
            }
        }.start();

         */


        setContentView(R.layout.count);
        start =findViewById(R.id.startTimer);
        stop=findViewById(R.id.stopTimer);
        progressBar=findViewById(R.id.progresBar);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer=new MyCountDownTimer(10000,1000);
                myCountDownTimer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCountDownTimer.cancel();
            }
        });

    }
    public class MyCountDownTimer extends CountDownTimer{
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress= (int) (millisUntilFinished/1000);      // 10 9 8 7 ...
            progressBar.setProgress((progressBar.getMax()+1)-progress);  // 1 2 3 4 ...
        }

        @Override
        public void onFinish() {

        }
    }
}
