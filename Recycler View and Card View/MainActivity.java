package com.ferhatiltas.recyclerview_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<MobileOs> mobileOs = new ArrayList<>();
    RecyclerView recyclerView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.recyler_view);

    LinearLayoutManager  layoutManager=new LinearLayoutManager(context);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    layoutManager.scrollToPosition(0);

       // GridLayoutManager layoutManager = new GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        mobileOs.add(new MobileOs("IOS", "2001", R.mipmap.ic_launcher));
        mobileOs.add(new MobileOs("Linox", "1587", R.mipmap.ic_launcher));
        mobileOs.add(new MobileOs("Android", "7835", R.mipmap.ic_launcher));

        CustomAdapter customAdapter = new CustomAdapter(mobileOs, context);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(context, "onREfresh", Toast.LENGTH_SHORT).show();
        CustomAdapter customAdapter = new CustomAdapter(mobileOs, context);
        recyclerView.setAdapter(customAdapter);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
