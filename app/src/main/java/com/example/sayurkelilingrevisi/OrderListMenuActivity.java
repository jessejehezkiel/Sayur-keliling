package com.example.sayurkelilingrevisi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sayurkelilingrevisi.Adapter.OrderListAdapter;
import com.example.sayurkelilingrevisi.Model.OrderList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderListMenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderListAdapter orderListAdapter;
    private List<OrderList> mOrder;
    private List<OrderList> userlist;

    FirebaseUser fuser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list_menu);

        recyclerView = findViewById(R.id.recyclerviewOL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        userlist = new ArrayList<>();


        reference = FirebaseDatabase.getInstance().getReference("OrderList");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userlist.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    OrderList orderList = snapshot.getValue(OrderList.class);
                    userlist.add(orderList);
                }

                orderList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void orderList(){
        mOrder = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("OrderList");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mOrder.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    OrderList orderList = snapshot.getValue(OrderList.class);
                    mOrder.add(orderList);
                }

                orderListAdapter = new OrderListAdapter(getApplicationContext(), mOrder);
                recyclerView.setAdapter(orderListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}




