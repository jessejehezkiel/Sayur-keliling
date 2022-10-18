package com.example.sayurkelilingrevisi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class MainPedagangActivity extends AppCompatActivity {

    LinearLayout about, OL, logout;
    Button chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pedagang);

        chat = findViewById(R.id.chat);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        OL = findViewById(R.id.OL);


        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chat = new Intent(MainPedagangActivity.this,ChatPedagangActivity.class);
                startActivity(chat);
            }
        });

        OL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OL = new Intent(MainPedagangActivity.this, OrderListMenuActivity.class);
                startActivity(OL);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(MainPedagangActivity.this,AboutActivity.class);
                startActivity(about);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainPedagangActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}