package com.ferhatiltas.medyaoynatimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

        MediaPlayer mediaPlayer;
        Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer=MediaPlayer.create(context,R.raw.ahmet_kaya);
    }

    public void btnPlaay(View view) {
        mediaPlayer.start();
        // mediaPlayer.prepare();

    }

    public void btnStop(View view) {
        mediaPlayer.pause();
    }
}
