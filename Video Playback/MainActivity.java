package com.ferhatiltas.videooynatimi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;
    Context context=this;
    MediaController mediaController;
    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mediaController == null) {
            mediaController=new MediaController(context);

        }

        videoView=findViewById(R.id.videoIzle);
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Android Video Örneği");
        progressDialog.setMessage("Yüleniyor");
        progressDialog.setCancelable(false);
        progressDialog.show();

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoView.seekTo(position);
                if (position == 0) {
                    videoView.start();
                }else  videoView.pause();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position=savedInstanceState.getInt("positionn");
        videoView.seekTo(position);
    }
}
