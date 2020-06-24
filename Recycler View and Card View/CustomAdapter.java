package com.ferhatiltas.recyclerview_cardview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<MobileOs> mobileOsArrayList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public CustomAdapter(ArrayList<MobileOs> mobileOsArrayList, Context context) {
        this.mobileOsArrayList = mobileOsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.row_list, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      // Her bir görünümün içeriği burada belirleriz
        holder.txtAciklama.setText(mobileOsArrayList.get(position).getAciklama());
        holder.txtCikisTarihi.setText(mobileOsArrayList.get(position).getCikisTarihi());
        holder.imageView.setImageResource(mobileOsArrayList.get(position).getImgSrc());
        holder.linearLayout.setTag(holder);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHolder holder1= (ViewHolder) v.getTag();
                int position=holder1.getPosition();
                String aciklama=mobileOsArrayList.get(position).getAciklama();
                Toast.makeText(context, aciklama, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mobileOsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAciklama, txtCikisTarihi;
        ImageView imageView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAciklama = itemView.findViewById(R.id.txtAciklama);
            txtCikisTarihi = itemView.findViewById(R.id.txtCikisTarihi);
            imageView = itemView.findViewById(R.id.image);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
}
