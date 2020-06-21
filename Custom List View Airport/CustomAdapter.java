package com.ferhatiltas.customlistview_editfilter_havalimani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    List<Airport> airportList = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public CustomAdapter(List<Airport> _airportList, Context _context) {
        this.airportList = _airportList;
        this.context = _context;
    }

    @Override
    public int getCount() {
        return airportList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(context);
        View satir = inflater.inflate(R.layout.satir,null);
        ImageView imageView = satir.findViewById(R.id.img);
        TextView textName = satir.findViewById(R.id.txtName);
        TextView textLocation=satir.findViewById(R.id.txtLocation);
        Airport airport=airportList.get(position);
        imageView.setBackgroundResource(airport.getImgSrc());
        textName.setText(airport.getName()+"("+airport.getCode()+")");
        textLocation.setText(airport.getCity()+"-"+airport.getCountry());
        return satir;
    }
}
