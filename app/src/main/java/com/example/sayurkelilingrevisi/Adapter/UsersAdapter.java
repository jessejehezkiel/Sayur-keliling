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
import com.example.sayurkelilingrevisi.Model.Users;
import com.example.sayurkelilingrevisi.PesanActivity;
import com.example.sayurkelilingrevisi.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private final Context context;
    private final List<Users> mUsers;

    public UsersAdapter(Context context, List<Users> mUsers) {
        this.context = context;
        this.mUsers = mUsers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.users_item,
                parent,
                false);
        return new UsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {

        Users users = mUsers.get(position);
        holder.username1.setText(users.getUsername());

        if (users.getImageURL().equals("default")){
            holder.imageView1.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context)
                    .load(users.getImageURL())
                    .into(holder.imageView1);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PesanActivity.class);
                intent.putExtra("userid", users.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username1;
        public ImageView imageView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username1 = itemView.findViewById(R.id.username1);
            imageView1 = itemView.findViewById(R.id.imageView1);
        }
    }
}
