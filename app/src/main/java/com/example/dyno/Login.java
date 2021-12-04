package com.example.dyno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    Button login,signup;
    EditText email,password;
    FirebaseAuth mAuth;
    FirebaseDatabase root;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.register);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance();
        login.setOnClickListener(v -> login());
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    private void login() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if(!mail.endsWith("@gmail.com")){
            Toast.makeText(getApplicationContext(),
                    "Please enter valid-email!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if(pass.length()<=6){
            Toast.makeText(getApplicationContext(),
                    "Please enter correct password!!",
                    Toast.LENGTH_LONG)
                    .show();
        }
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Login.this.getApplicationContext(),

                        "Login successful!",
                        Toast.LENGTH_LONG)
                        .show();
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);

            }
            else {

                // Registration failed
                Toast.makeText(
                        Login.this.getApplicationContext(),
                        "Login failed!!"
                                + " Please try again later",
                        Toast.LENGTH_LONG)
                        .show();


            }
        });
    }
}