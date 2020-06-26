package com.ferhatiltas.sqlite_ekleguncellesil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {
    // datebase ismi ve kitap alanlar isimleri
    private static final String database_NAME = "KitapDB";
    private static final int database_VERSION = 2;
    private static final String table_BOOKS = "kitaplar"; //tablo adı
    private static final String book_ID = "id";
    private static final String book_TITLE = "baslik";
    private static final String book_OUTHER = "yazar";
    private static final String COLUMN[] = {book_ID, book_TITLE, book_OUTHER};
    private static final String CREATE_BOOK_TABLE = "CREATE TABLE "
            + table_BOOKS + " ("
            + book_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + book_TITLE + " TEXT, "
            + book_OUTHER + " TEXT )"; //SQLite programı ile yaptığımız tablonun kodlarını buraya yapışturacağız ama isimlerin değişkenlerini yazmamız gerek


    public SQLite(@Nullable Context context) {
        // super(context, new File(Environment.getExternalStorageDirectory(), database_NAME).toString(), null, database_VERSION);
        super(context, database_NAME, null, database_VERSION);

        /// Log.i("PATH", Environment.getExternalStorageDirectory() + database_NAME);

    }

    @Override
    public void onCreate(SQLiteDatabase db) { // Veri tabanı ilk çağrıldığında bu metot çağrılır
        //Burada genellikle tablo oluşturma işlemlerini yaparız
        db.execSQL(CREATE_BOOK_TABLE);// tabloyu etkinleştiriyor
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Genelde güncelleeme olursa burada yao-pılır
        db.execSQL("DROP TABLE IF EXISTS " + table_BOOKS);//her seferinde tabe_BOOKS i silecek ve
        this.onCreate(db);                              //yeniden oncreate metoduna gidip baştan oluşturacak
    }

    public void KitapEkle(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();
        // ekle guncelle ve silme islemi icin ContentValues sınıfı kullanılır
        ContentValues degerler = new ContentValues();
        degerler.put(book_TITLE, kitap.getBaslik()); //ekleme işlemi yapıyoruz
        degerler.put(book_OUTHER, kitap.getYazar());
        db.insert(table_BOOKS, null, degerler);//ekleme işlemi tamamlandı
        db.close();
    }

    public List<Kitap> kitaplariGetir() {
        List<Kitap> kitaplar = new ArrayList<>();
        String sorgu = "SELECT * FROM " + table_BOOKS; // table_BOOKS adlı tabloyu getir
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sorgu, null);
        Kitap kitap = null;
        if (cursor.moveToFirst()) {//birinci kitab varsa ona git
            do {
                kitap = new Kitap(); // Kitap sınıı-fında bide boş counstarctr yaptığımız için hata almıyoruz
                kitap.setId(Integer.parseInt(cursor.getString(0)));//kitap değişkeninin içini doldurdukk
                kitap.setBaslik(cursor.getString(1));
                kitap.setYazar(cursor.getString(2));
                kitaplar.add(kitap); // listeye atıyoruz
            } while (cursor.moveToNext());//bir sonraki kitaba gider en sondaki kitaba geldiğinde kitap kalmadığı için döngüden çıkar
        }
        return kitaplar;

    }

    public Kitap kitaplar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_BOOKS, COLUMN, " id = ? ",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Kitap kitap = new Kitap();
        kitap.setId(Integer.parseInt(cursor.getString(0)));//kitap değişkeninin içini doldurdukk
        kitap.setBaslik(cursor.getString(1));
        kitap.setYazar(cursor.getString(2));
        return kitap;
    }

    public void KitapSil(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();// içerdeki data değişeceği için writetabeDatabsae kullanacağız
        db.delete(table_BOOKS, book_ID + " = ? ", new String[]{String.valueOf(kitap.getId())});// Bilmem tablodaki book_ID ide = new String[]{String.valueOf(kitap.getId())} ise siler
        db.close();

    }

    public int kitapGuncelle(Kitap kitap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put("baslik", kitap.getBaslik());
        degerler.put("yazar", kitap.getYazar());
        int i = db.update(table_BOOKS, degerler, book_ID + " = ? ", new String[]{String.valueOf(kitap.getId())});// Bilmem tablodaki book_ID ide = new String[]{String.valueOf(kitap.getId())} ise guncelle
        db.close();
        return i;
    }
}