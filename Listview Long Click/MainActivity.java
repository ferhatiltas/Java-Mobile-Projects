package com.ferhatiltas.listviewdzenlesilmens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Context context=this;
    TextView durum;
    ListView list;
    static final int ID_SIL= Menu.FIRST;
    static final int ID_DUZENLE= Menu.FIRST+1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        durum=findViewById(R.id.durum);
        String []ismler=getResources().getStringArray(R.array.names);//listemizdeki isimleri  isimler adı altındaki diziye atıyoruz
        ArrayAdapter<String> adapter=new ArrayAdapter<>(context,R.layout.list_item,ismler);//Array adapter aracılığı ile listemizin gözükmesini sağlıyoruz
        list.setAdapter(adapter);
        registerForContextMenu(list);//Listviewdeki herhangi bir seçeneğe tıklandıgında bir tane menu penceresi gelecek

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,ID_SIL,0,"Sil");
        menu.add(Menu.NONE,ID_DUZENLE,1,"Düzenle");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TextView seciliEleman= (TextView) info.targetView;
        if (item.getItemId()==ID_SIL) {
            durum.setText(seciliEleman.getText()+" için SİL");
            return true;
        }
        if (item.getItemId() == ID_DUZENLE) {
            durum.setText(seciliEleman.getText()+" için DÜZENLE");

            return true;

        }
        return false;
    }
}
