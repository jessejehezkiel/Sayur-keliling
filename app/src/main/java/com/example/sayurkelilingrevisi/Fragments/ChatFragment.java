package com.example.sayurkelilingrevisi.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayurkelilingrevisi.Adapter.PedagangAdapter;
import com.example.sayurkelilingrevisi.Model.ChatList;
import com.example.sayurkelilingrevisi.Model.Pedagang;
import com.example.sayurkelilingrevisi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private PedagangAdapter pedagangAdapter;
    private List<Pedagang> mPedagang;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<ChatList> pedaganglist;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat,
                container,
                false);

        recyclerView = view.findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fuser = FirebaseAuth.getInstance().getCurrentUser();

        pedaganglist = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("ChatList")
                .child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pedaganglist.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChatList chatList = snapshot.getValue(ChatList.class);
                    pedaganglist.add(chatList);
                }

                chatListP();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }

    private void chatListP(){

        mPedagang = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Pedagang");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mPedagang.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Pedagang pedagang = snapshot.getValue(Pedagang.class);
                    for (ChatList chatList : pedaganglist){

                        if (pedagang.getId().equals(chatList.getId())){
                            mPedagang.add(pedagang);
                        }
                    }
                }
                pedagangAdapter = new PedagangAdapter(getContext(), mPedagang);
                recyclerView.setAdapter(pedagangAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}