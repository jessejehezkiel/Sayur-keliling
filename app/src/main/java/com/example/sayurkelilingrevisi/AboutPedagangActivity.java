package com.example.sayurkelilingrevisi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutPedagangActivity extends AppCompatActivity {

    Button aboutbck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pedagang);

        aboutbck = findViewById(R.id.aboutbck);

        aboutbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutbck = new Intent(AboutPedagangActivity.this,
                        MainPedagangActivity.class);
                startActivity(aboutbck);
            }
        });
    }
}