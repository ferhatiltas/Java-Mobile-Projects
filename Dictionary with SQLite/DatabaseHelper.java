package com.ferhatiltas.dbbrowsersqlitedenandroide;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLTimeoutException;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_PATH = "data/data/com.ferhatiltas.dbbrowsersqlitedenandroide/databases/";
    private static final int database_VERSION = 1;
    static final String DB_Name = "sozlukSQLite.db";
    SQLiteDatabase sqLiteDatabase;
    Context context;

    public DatabaseHelper(@Nullable Context mContext) {
        super(mContext, DB_Name, null, database_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    } // burada veri tabanı oluşturulur bizim elimizde olduğu için bu ve altındakine dokunmayacağız

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checekDatabase() {
        // Veri tabanının daha önce oluşturulup oluşturulmadığını kontrol edecek. Oluşturulmuşsa true, değilse false döndür
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_Name;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;

    }

    public void createDatebase()  throws IOException{ // throws SQLException ile metodun tamamına try and catch ekliyoruz. SQLException sadece SQLite ile ilgili hataları verir ama bunu IOException yapalım
        //Eğer veritabanı yoksa oluşturacak, varsa var olanın üzerinden işlem yapacak
        boolean dbExist = checekDatabase();
        if (dbExist) {
            // database varsa
        } else {
            getReadableDatabase();
            // Veri tabanı yoksa buraya kopyalıyoruz
            copyDatabase();
        }
    }

    public void copyDatabase() throws IOException{ // throws SQLException ile metodun tamamına try and catch ekliyoruz. SQLException sadece SQLite ile ilgili hataları verir ama bunu IOException yapalım
        // assets --> DB_PATH + DB_Name
        try{
            InputStream myInput= context.getAssets().open(DB_Name);
            String outFileName= DB_PATH+DB_Name;
            OutputStream myOutput= new FileOutputStream(outFileName);
            byte[] buffer= new byte[1024];
            int length;
            while ((length=myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException w){
            w.printStackTrace();
        }
    }
    public void openDatabase() throws IOException{ // throws SQLException ile metodun tamamına try and catch ekliyoruz. SQLException sadece SQLite ile ilgili hataları verir ama bunu IOException yapalım
        String myPath=DB_PATH+DB_Name;
        sqLiteDatabase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY); // bu şekilde veritabanımızı açıyoruz

    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
        super.close(); // veri tabanında bağlantıyı açma ve kapatma önemli bağlantı açık kalırsa bir sonraki açılışta açık diye hata verir
    }
    public SQLiteDatabase getDatabase(){
        return sqLiteDatabase;
    }
}
