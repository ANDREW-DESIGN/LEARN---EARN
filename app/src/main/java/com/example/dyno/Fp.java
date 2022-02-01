package com.example.dyno;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Fp extends AppCompatActivity {

    TextInputEditText emailbutton;
    Button next;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp);

        next = findViewById(R.id.nextfor);
        emailbutton = findViewById(R.id.emailfor);
        firebaseAuth = FirebaseAuth.getInstance();
        next.setOnClickListener(v -> firebaseAuth.sendPasswordResetEmail(Objects.requireNonNull(emailbutton.getText()).toString()).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                Toast.makeText(Fp.this, "Password sent to email", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Fp.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
        }));
    }
}
