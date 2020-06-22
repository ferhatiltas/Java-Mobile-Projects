package com.ferhatiltas.fragmentyapisi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager  manager =  getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnEkleA_click(View view) {
        FragmentA fa = new FragmentA();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.grup, fa, "A");
        transaction.commit();
    }

    public void btnKaldirA_click(View view) {
        //tag i bilinen fragmentin tagi ne göre kaldıracağız
        FragmentA fa= (FragmentA) manager.findFragmentByTag("A"); /// tag ı A olan ı bul
        FragmentTransaction transaction = manager.beginTransaction();
        if (fa != null) {
            transaction.remove(fa);
            transaction.commit();
        }else Toast.makeText(this, "Fragment bulunamadı", Toast.LENGTH_SHORT).show();
    }

    public void btnDegistirA_click(View view) {
        FragmentB fb= new FragmentB();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.grup,fb);
        transaction.commit();

    }

    public void btnEkleB_click(View view) {
        FragmentB fb = new FragmentB();
        FragmentTransaction transaction = manager.beginTransaction(); // Bütün işlemler için FragmentTransaction gerekli
        transaction.add(R.id.grup, fb, "B");
        transaction.commit();
    }

    public void btnKaldirB_click(View view) {
        //tag i bilinen fragmentin tagi ne göre kaldıracağız
        FragmentB fb= (FragmentB) manager.findFragmentByTag("B"); /// tag ı A olan ı bul
        FragmentTransaction transaction = manager.beginTransaction();
        if (fb != null) {
            transaction.remove(fb);
            transaction.commit();
        }else Toast.makeText(this, "Fragment bulunamadı", Toast.LENGTH_SHORT).show();
    }

    public void btnDegistirB_click(View view) {
        FragmentA fa= new FragmentA();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.grup,fa);
        transaction.commit();
    }


}
