package com.example.dyno;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Imagesactivity extends AppCompatActivity  {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;



    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Model> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mRecyclerView = findViewById(R.id.list_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter(Imagesactivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener=mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mUploads.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                   Model upload = postSnapshot.getValue(Model.class);
                    Model.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Imagesactivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
