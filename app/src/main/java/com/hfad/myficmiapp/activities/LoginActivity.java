package com.hfad.myficmiapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hfad.myficmiapp.MainActivity;
import com.hfad.myficmiapp.R;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email, password;
    FirebaseFirestore store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        store = FirebaseFirestore.getInstance();
    }

    public void signIn(View view) {
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Enter Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6){
            Toast.makeText(this, "Password too short, Enter minimum 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Error "+ task.getException(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        finish();
    }

    @Override
    protected  void onStart(){
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

}