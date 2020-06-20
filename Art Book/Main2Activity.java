package com.ferhatiltas.artbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.ferhatiltas.artbook.R.layout.activity_main2;

public class Main2Activity extends AppCompatActivity {
Bitmap selectedImage;
    SQLiteDatabase database;
ImageView imageView2;
Button button;
EditText artNameTExt,painterNameText,yeartText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main2);
        imageView2=findViewById(R.id.imageView2);
        button=findViewById(R.id.button);
        artNameTExt=findViewById(R.id.artNameText);
        painterNameText=findViewById(R.id.painterNameText);
        yeartText=findViewById(R.id.yeartText);

        database=this.openOrCreateDatabase("arts",MODE_PRIVATE,null);

        Intent intent=getIntent();
        String info=intent.getStringExtra("info");
        if (info.matches("new")){
            artNameTExt.setText("");
            painterNameText.setText("");
            yeartText.setText("");
            button.setVisibility(View.VISIBLE); //ikinci ekrana burdan gelirse button gözüksün
            Bitmap selectImage= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.selectimage);
            imageView2.setImageBitmap(selectImage);

        }
        else {
            int artId=intent.getIntExtra("artId",1);
            button.setVisibility(View.INVISIBLE);//ikinci ekrana burdan gelirse button gözükmesin
               try {
                        Cursor cursor=database.rawQuery("SELECT*FROM arts WHERE id=?",new String[]{String.valueOf(artId)});
                        int artNameIx=cursor.getColumnIndex("artName");
                        int painterNameIx=cursor.getColumnIndex("painterName");
                        int yearIx=cursor.getColumnIndex("year");
                        int imageIx=cursor.getColumnIndex("image");

                        while(cursor.moveToNext()){
                            artNameTExt.setText(cursor.getString(artNameIx));
                            painterNameText.setText(cursor.getString(painterNameIx));
                            yeartText.setText(cursor.getString(yearIx));
                            byte[] bytes=cursor.getBlob(imageIx);
                            Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                            imageView2.setImageBitmap(bitmap);

                        }
                        cursor.close();
                  }

              catch (Exception e){

            }
        }

    }
    public void selectedImage(View view){ //izin isteme eğer izzin yoksa izin iste izin varsa kullanıcıyı galeriye gönder else ile
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else { //Kullanıcı galeriye gönderilir eğer resme tıklarsa
            Intent intentToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
        }
    }

    @Override //izin istenildiğinde ne yapılacağını kodlayacağız
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1) {
            if(grantResults.length>0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED ){ //izin verilirse kullanıcıyı alıp galeriye götürecek
            Intent intentToGallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentToGallery,2);
            }
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==2 && resultCode==RESULT_OK&& data!= null){
            Uri imageData=data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) { //telefonun versiyonu 28 den büyük ise Bitmap eskidiği için onun yerine ImageDecoder sınıfını kullanacağız
                    ImageDecoder.Source source=ImageDecoder.createSource(this.getContentResolver(),imageData);
                    selectedImage=ImageDecoder.decodeBitmap(source);
                    imageView2.setImageBitmap(selectedImage);

                }
                else {
                    selectedImage=MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageData);
                    imageView2.setImageBitmap(selectedImage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } super.onActivityResult(requestCode, resultCode, data);
    }
    public void save(View view){
        String artName=artNameTExt.getText().toString();
        String painterName=painterNameText.getText().toString();

        Bitmap smallImage=makeSmallerImage(selectedImage,300); //makeSmallerImage metodunu çağırarak fotoğrafı küçülteceğiz 300 olarak
        String yeart=yeartText.getText().toString();
        //Fotoğrafı alıp veriye çeviriyoruz
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,byteArrayOutputStream);   // smallImage yi compress ederek byte çevireceğiz
        byte[] byteArray=byteArrayOutputStream.toByteArray();

        try { //SQLite ile verileri buraya yazacağız
             database=this.openOrCreateDatabase("arts",MODE_PRIVATE,null); //TAblonun ismini ve modunu belirliyoruz
            database.execSQL("CREATE TABLE  IF NOT EXISTS arts(id INTEGER PRIMARY KEY , artName VARCHAR,painterName VARCHAR,year VARCHAR,image BLOB)");//Tabloyu oluşturup kolon ve kolonun veri tiplerini yazıyoruz

            String sqlString="INSERT INTO arts (artName,painterName,year,image)VALUES(?,?,?,?)";// string olan bir ifadeyi
            SQLiteStatement sqLiteStatement=database.compileStatement(sqlString);                // compileStatement ile SQLite komuduna çeviriyoruz
            sqLiteStatement.bindString(1,artName);
            sqLiteStatement.bindString(2,painterName);
            sqLiteStatement.bindString(3,yeart);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();
        }
        catch (Exception e){
            e.printStackTrace();


        }
        Intent intent=new Intent(Main2Activity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public  Bitmap makeSmallerImage(Bitmap image , int maximumSize){//İmagenin boyutunu oranlarıyla orantılı olarak küçülteceğiz
        int width=image.getWidth();
        int height=image.getHeight();
        float bitmapRatio=(float)width/(float)height;  //ortalama alıyoruz
        if (bitmapRatio>1){ //yatay veya dikeyliiğine bakıyoruz
            width=maximumSize;
            height=(int)(width/bitmapRatio); //dikeyse dikey tarafı uzun bıraktık
        }
        else{
            height=maximumSize;
            width=(int)(height*bitmapRatio);//yatay ise yatay tarafını uzun bıraktık
        }
        return Bitmap.createScaledBitmap(image,width,height,true);
    }
}

