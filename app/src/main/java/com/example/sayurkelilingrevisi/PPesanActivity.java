package com.example.sayurkelilingrevisi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sayurkelilingrevisi.Adapter.PesanAdapter;
import com.example.sayurkelilingrevisi.Model.Chat;
import com.example.sayurkelilingrevisi.Model.Pedagang;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PPesanActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText pemesan, pedagangOL, alamatpemesan, tgl, jam, pesanan, total;
    private Button batal,simpan;

    TextView usernamepdg;
    ImageView imageView, btn_orderlist;

    RecyclerView recyclerViewy;
    EditText msg_editText;
    ImageButton sendBtn;

    FirebaseUser fuser;
    DatabaseReference reference;
    Intent intent;

    PesanAdapter pesanAdapter;
    List<Chat> mchat;

    RecyclerView recyclerView;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppesan);

        imageView = findViewById(R.id.imageview_profile);
        usernamepdg = findViewById(R.id.usernamepdg);
        sendBtn = findViewById(R.id.btn_kirim);
        msg_editText = findViewById(R.id.text_kirim);
        btn_orderlist = findViewById(R.id.btn_orderlist);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        intent = getIntent();
        userid = intent.getStringExtra("userid");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Pedagang").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pedagang pedagang = dataSnapshot.getValue(Pedagang.class);
                assert pedagang != null;
                usernamepdg.setText(pedagang.getUsernamepdg());

                if (pedagang.getImageURL().equals("default")) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(PPesanActivity.this)
                            .load(pedagang.getImageURL())
                            .into(imageView);
                }

                bacaPesan(fuser.getUid(),userid, pedagang.getImageURL());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msg_editText.getText().toString();
                if (!msg.equals("")){
                    sendMessage(fuser.getUid(), userid, msg);
                }else{
                    Toast.makeText(PPesanActivity.this, "Tidak ada pesan yang dikirim", Toast.LENGTH_SHORT).show();
                }
                msg_editText.setText("");
            }
        });

        btn_orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderlist();
            }
        });
    }

    public void orderlist(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popuplistView = getLayoutInflater().inflate(R.layout.popuplist, null);

        pemesan = (EditText) popuplistView.findViewById(R.id.pemesan);
        pedagangOL = (EditText) popuplistView.findViewById(R.id.pedagangOL);
        alamatpemesan = (EditText) popuplistView.findViewById(R.id.alamatpemesan);
        tgl = (EditText) popuplistView.findViewById(R.id.tgl);
        jam = (EditText) popuplistView.findViewById(R.id.jam);
        pesanan = (EditText) popuplistView.findViewById(R.id.pesanan);
        total = (EditText) popuplistView.findViewById(R.id.total);

        simpan = (Button) popuplistView.findViewById(R.id.simpan);
        batal = (Button) popuplistView.findViewById(R.id.batal);

        dialogBuilder.setView(popuplistView);
        dialog = dialogBuilder.create();
        dialog.show();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pemesan_txt = pemesan.getText().toString();
                String pedagangOL_txt = pedagangOL.getText().toString();
                String alamatpemesan_txt = alamatpemesan.getText().toString();
                String tgl_txt = tgl.getText().toString();
                String jam_txt = jam.getText().toString();
                String pesanan_txt = pesanan.getText().toString();
                String total_txt = total.getText().toString();

                catat(pemesan_txt, pedagangOL_txt, alamatpemesan_txt, tgl_txt, jam_txt,
                        pesanan_txt, total_txt);

            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void catat(final String pemesan, String pedagangOL, String alamatpemesan,
                       String tgl, String jam, String pesanan, String total){
        reference = FirebaseDatabase.getInstance().getReference("OrderList").child(userid);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pemesan", pemesan);
        hashMap.put("pedagang", pedagangOL);
        hashMap.put("alamatpemesan", alamatpemesan);
        hashMap.put("tgl", tgl);
        hashMap.put("jam", jam);
        hashMap.put("pesanan", pesanan);
        hashMap.put("total", total);

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PPesanActivity.this, "Berhasil menabahkan pesanan ke order list.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(PPesanActivity.this, "Gagal menambahkan pesanan",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendMessage(String pengirim, String penerima, String pesan){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pengirim", pengirim);
        hashMap.put("penerima", penerima);
        hashMap.put("pesan", pesan);

        reference.child("Chats").push().setValue(hashMap);

        final DatabaseReference chatRef = FirebaseDatabase.getInstance()
                .getReference("ChatList")
                .child(fuser.getUid())
                .child(userid);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    chatRef.child("id").setValue(userid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void bacaPesan(String myid, String userid, String imageurl){
        mchat =  new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Chat chat = snapshot.getValue(Chat.class);

                    assert chat != null;
                    if (chat.getPenerima().equals(myid) && chat.getPengirim().equals(userid) ||
                            chat.getPenerima().equals(userid) && chat.getPengirim().equals(myid)){

                        mchat.add(chat);
                    }

                    pesanAdapter = new PesanAdapter(PPesanActivity.this, mchat,imageurl);
                    recyclerView.setAdapter(pesanAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}