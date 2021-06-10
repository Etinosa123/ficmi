package com.hfad.myficmiapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.hfad.myficmiapp.MainActivity;
import com.hfad.myficmiapp.R;

public class AdminMainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(AdminMainActivity.this, RegistrationActivity.class));
                finish();
            }
        });
    }
}