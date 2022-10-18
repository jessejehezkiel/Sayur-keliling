package com.example.sayurkelilingrevisi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sayurkelilingrevisi.Model.Pedagang;
import com.example.sayurkelilingrevisi.PPesanActivity;
import com.example.sayurkelilingrevisi.R;

import java.util.List;

public class PedagangAdapter extends RecyclerView.Adapter<PedagangAdapter.ViewHolder> {

    private final Context context;
    private final List<Pedagang> mPedagang;

    public PedagangAdapter(Context context, List<Pedagang> mPedagang) {
        this.context = context;
        this.mPedagang = mPedagang;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pedagang_item,
                parent,
                false);
        return new PedagangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedagangAdapter.ViewHolder holder, int position) {

        Pedagang pedagang = mPedagang.get(position);
        holder.usernamepdg.setText(pedagang.getUsernamepdg());
        holder.areapdg.setText(pedagang.getAreapdg());

        if (pedagang.getImageURL().equals("default")){
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(context)
                    .load(pedagang.getImageURL())
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PPesanActivity.class);
                intent.putExtra("userid", pedagang.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPedagang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView usernamepdg;
        public TextView areapdg;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernamepdg = itemView.findViewById(R.id.usernamepdg);
            areapdg = itemView.findViewById(R.id.areapdg);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
