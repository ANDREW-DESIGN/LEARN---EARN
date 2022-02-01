package com.example.dyno;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class Av extends AppCompatActivity {
    ImageView imageView;
    Button button,b2;
    FirebaseStorage storage;
    private DatabaseReference mDatabaseRef;
    EditText mEditTextFileName;
    Uri imageUri;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_av);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        b2 = findViewById(R.id.text_view_show_uploads);
        mEditTextFileName = findViewById(R.id.edit_text_file_name);


        //storage = FirebaseStorage.getInstance().getReference("uploads/4315345345.jpg");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        storage = FirebaseStorage.getInstance();
        b2.setOnClickListener(v -> {
            Intent i=new Intent(Av.this,ecard.class) ;
            startActivity(i);
        });

        imageView.setOnClickListener(v -> mGetContent.launch("image/*"));

        button.setOnClickListener(v -> uploadImage());

    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference fileReference = storage.getReference().child("images*/");
            mUploadTask = fileReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {


                        Toast.makeText(Av.this, "Upload successful", Toast.LENGTH_LONG).show();

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!
                                urlTask.isSuccessful()) ;
                        Uri downloadUrl = urlTask.getResult();
                        load upload = new load(mEditTextFileName.getText().toString().trim(), downloadUrl.toString());
                        String uploadId = mDatabaseRef.push().getKey();
                        mDatabaseRef.child(uploadId).setValue(upload);
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Av.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });



        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }






    /*    if (imageUri != null) {
                               }
                           })     StorageReference reference = storage.getReference().child("images/*" + UUID.randomUUID().toString());
                           .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                               @Override     reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                               public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {         @Override
                                   double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());         public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                   mProgressBar.setProgress((int) progress);             if (task.isSuccessful()) {
                               }
                           });








                        Toast.makeText(MainActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            */
    }




    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {

                    if (result != null) {

                        imageView.setImageURI(result);
                        imageUri = result;
                    }
                }
            });


}

