package com.example.dyno;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText1, editText2, editText3, editText4;
    Button submit, show;
    DatabaseReference root;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etValue);
        editText1 = findViewById(R.id.etValue1);
        editText4 = findViewById(R.id.etValue4);
        editText2 = findViewById(R.id.etValue2);
        editText3 = findViewById(R.id.etValue5);
        submit = findViewById(R.id.btnSubmit);
        show = findViewById(R.id.btnSubmit1);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Database reference pointing to root of database

        root = FirebaseDatabase.getInstance().getReference("uploads");
        // Database reference pointing to demo node


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tutor_name = editText.getText().toString();
                String courses = editText1.getText().toString();
                String timings = editText2.getText().toString();
                String base_price = editText3.getText().toString();
                String phone = editText4.getText().toString();
                if (phone.length() != 10) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter valid phonenumber",
                            Toast.LENGTH_LONG)
                            .show();

                }
                if (TextUtils.isEmpty(tutor_name)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter tutorname",
                            Toast.LENGTH_LONG)
                            .show();

                }
                if (TextUtils.isEmpty(courses)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter course!!",
                            Toast.LENGTH_LONG)
                            .show();

                }
                if (TextUtils.isEmpty(timings)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter timings",
                            Toast.LENGTH_LONG)
                            .show();
                }
                if (TextUtils.isEmpty(base_price)) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter baseprice!! or else if it is free enter free!!",
                            Toast.LENGTH_LONG)
                            .show();
                }

                //  String value = editText.getText().toString();

                // Push creates a unique id in database
           /* demoRef.setValue(value);
            demoRef.setValue(value1);
            demoRef.setValue(value2);
            demoRef.setValue(value3);

            */
                Model model = new Model(tutor_name, courses, timings, base_price, phone);
                root.push().setValue(model);
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Imagesactivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.advertisemnt) {
            Intent i = new Intent(MainActivity.this, Av.class);
            Toast.makeText(MainActivity.this, "move to advertisements", Toast.LENGTH_SHORT).show();
            startActivity(i);

        } else if (id == R.id.certificate) {
            Intent i = new Intent(MainActivity.this, Cr.class);
            Toast.makeText(MainActivity.this, "move to certificate", Toast.LENGTH_SHORT).show();
            startActivity(i);
        } else if (id == R.id.updatePassword) {
            Intent i = new Intent(MainActivity.this, Up.class);
            Toast.makeText(MainActivity.this, "move to update Password", Toast.LENGTH_SHORT).show();
            startActivity(i);
        } else if (id == R.id.Logout) {
            Intent i = new Intent(MainActivity.this, Login.class);
            Toast.makeText(MainActivity.this, "succcessfully logged out", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        return true;
    }

}

