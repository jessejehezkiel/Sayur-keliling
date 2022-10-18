package com.example.sayurkelilingrevisi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterPdgActivity extends AppCompatActivity {

    EditText usernamepdg, areapdg, emailpdg, passpdg;
    Button btn_buatpdg;
    FirebaseAuth mAuth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pdg);

        usernamepdg = findViewById(R.id.usernamepdg);
        areapdg = findViewById(R.id.areapdg);
        emailpdg = findViewById(R.id.emailpdg);
        passpdg = findViewById(R.id.passpdg);
        btn_buatpdg = findViewById(R.id.btn_buatpdg);

        mAuth = FirebaseAuth.getInstance();

        btn_buatpdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamepdg_txt = usernamepdg.getText().toString();
                String areapdg_txt = areapdg.getText().toString();
                String emailpdg_txt = emailpdg.getText().toString();
                String passpdg_txt = passpdg.getText().toString();

                if (TextUtils.isEmpty(usernamepdg_txt) || TextUtils.isEmpty(areapdg_txt) || TextUtils.isEmpty(emailpdg_txt) || TextUtils.isEmpty(passpdg_txt)){
                    Toast.makeText(RegisterPdgActivity.this, "Tolong isi semuanya", Toast.LENGTH_SHORT).show();
                } else if (passpdg_txt.length() < 6 ){
                    Toast.makeText(RegisterPdgActivity.this, "Password harus lebih dari 6 karaker.", Toast.LENGTH_SHORT).show();
                } else {
                    registerpdg(usernamepdg_txt, areapdg_txt, emailpdg_txt, passpdg_txt);
                }
            }
        });
    }
    private void registerpdg(final String usernamepdg, String areapdg, String emailpdg, String passpdg){
        mAuth.createUserWithEmailAndPassword(emailpdg, passpdg)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance().getReference("Pedagang")
                                    .child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("usernamepdg", usernamepdg);
                            hashMap.put("areapdg", areapdg);
                            hashMap.put("imageURL", "default");

                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(RegisterPdgActivity.this, MainPedagangActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterPdgActivity.this, "Anda tidak bisa menggunakan email atau password ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}