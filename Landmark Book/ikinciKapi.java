package com.ferhatiltas.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class ikinciKapi extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ikinci_kapi);
        TextView nametext=findViewById(R.id.nametext);
        TextView  countrytext=findViewById(R.id.countrytext);
        ImageView imageView =findViewById(R.id.imageView);
        Intent intent=getIntent();                                       //Öbür penceredeki İsim ve
        String landmarkName=intent.getStringExtra("Name");         //kent isimlerini
        nametext.setText(landmarkName);                                  //buraya
        String countryNames=intent.getStringExtra("Country");     //taşır.
        countrytext.setText(countryNames);
        singleton singletonn=singleton.getInstance();
        imageView.setImageBitmap(singletonn.getChosenImage());




    }
}
