package com.example.dyno;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity {
Button register,login;

    EditText username,phone_number,email,password,confirm_password;
     FirebaseAuth mAuth;
    DatabaseReference root;
    FirebaseDatabase  db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register);
        username=findViewById(R.id.person);
        phone_number=findViewById(R.id.phonenumber);
        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm);
mAuth=FirebaseAuth.getInstance();

        root = FirebaseDatabase.getInstance().getReference("Upload");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }

    private void register() {


       String mail = email.getText().toString();
       String pass = password.getText().toString();
       String user=username.getText().toString();
       String phone=phone_number.getText().toString();
       String confirm=confirm_password.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter name!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if(!mail.endsWith("@gmail.com")){
            Toast.makeText(getApplicationContext(),
                    "Please enter valid-email!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter phonenumber!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if (phone.length()!=10) {
            Toast.makeText(getApplicationContext(),
                    "Please enter phonenumber!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if (!confirm.equals(pass)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter correct password!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if (TextUtils.isEmpty(mail)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();

        }
        if(pass.length()<=6){
            Toast.makeText(getApplicationContext(),
                    "Please enter password of length 6!!",
                    Toast.LENGTH_LONG)
                    .show();
        }
        mAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Upload upload = new Upload(mail, pass, user, phone);
                            root.push().setValue(upload);

                            FirebaseDatabase.getInstance().getReference("Upload").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(upload).addOnCompleteListener(task1 -> {
                                Toast.makeText(Register.this.getApplicationContext(),

                                        "Registration successful!",
                                        Toast.LENGTH_LONG)
                                        .show();
                                Intent i = new Intent(Register.this, Login.class);
                                startActivity(i);
                            });


                            // hide the progress bar


                            // if the user created intent to login activity

                        } else {

                            // Registration failed
                            Toast.makeText(
                                    Register.this.getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();


                        }
                    }
                });
    }
}