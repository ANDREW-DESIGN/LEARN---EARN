package com.example.dyno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText1,editText2,editText3,editText4;
    Button submit,show;
    DatabaseReference root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.etValue);
        editText1=findViewById(R.id.etValue1);
        editText4=findViewById(R.id.etValue4);
        editText2=findViewById(R.id.etValue2);
        editText3=findViewById(R.id.etValue5);
        submit = findViewById(R.id.btnSubmit);
        show=findViewById(R.id.btnSubmit1);

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
                if(phone.length()!=10){
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
                Model model = new Model(tutor_name, courses,timings,base_price, phone);
                root.push().setValue(model);
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Imagesactivity.class));
            }
        });


    }
}