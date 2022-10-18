package com.example.sayurkelilingrevisi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sayurkelilingrevisi.Model.OrderList;
import com.example.sayurkelilingrevisi.R;

import java.text.CollationElementIterator;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder>{

    private final Context context;
    private final List<OrderList> mOrder;

    public OrderListAdapter(Context context, List<OrderList> mOrder) {
        this.context = context;
        this.mOrder = mOrder;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderlist_item,
                parent,
                false);
        return new OrderListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {

        OrderList orderList = mOrder.get(position);
        holder.pemesan.setText(orderList.getPemesan());
        holder.pedagangOL.setText(orderList.getPedagang());
        holder.alamatpemesan.setText(orderList.getAlamatpemesan());
        holder.tgl.setText(orderList.getTgl());
        holder.jam.setText(orderList.getJam());
        holder.pesanan.setText(orderList.getPesanan());
        holder.total.setText(orderList.getTotal());

    }

    @Override
    public int getItemCount() {
        return mOrder.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pemesan;
        public TextView pedagangOL;
        public TextView alamatpemesan;
        public TextView tgl;
        public TextView jam;
        public TextView pesanan;
        public TextView total;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pemesan = itemView.findViewById(R.id.pemesan);
            pedagangOL = itemView.findViewById(R.id.pedagangOL);
            alamatpemesan = itemView.findViewById(R.id.alamatpemesan);
            tgl = itemView.findViewById(R.id.tgl);
            jam = itemView.findViewById(R.id.jam);
            pesanan = itemView.findViewById(R.id.pesanan);
            total = itemView.findViewById(R.id.total);

        }
    }
}
