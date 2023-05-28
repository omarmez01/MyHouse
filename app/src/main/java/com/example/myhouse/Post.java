/**
 * Post class: this class post the image and description in the database
 */

package com.example.myhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Post extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText edtCity, edtDesc, edtPrice;
    Button btnInsert;
    private static final int Post_Code = 1;
    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageButton = findViewById(R.id.imageButton);
        edtCity = findViewById(R.id.edtCity);
        edtDesc = findViewById(R.id.edtDesc);
        edtPrice = findViewById(R.id.edtPrice);
        btnInsert = findViewById(R.id.btnInsert);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("post");
        mStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        // Retrieve a photo from the phone
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Post_Code);
            }
        });
    }

    // Uploading the data to firebase
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Post_Code && resultCode == RESULT_OK) {
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c = edtCity.getText().toString().trim();
                String d = edtDesc.getText().toString().trim();
                String p = edtPrice.getText().toString().trim();

                if (!c.isEmpty() && !d.isEmpty() && !p.isEmpty() && imageUrl != null) {
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();

                                    DatabaseReference newPost = mRef.push();

                                    newPost.child("City").setValue(c);
                                    newPost.child("Description").setValue(d);
                                    newPost.child("Price").setValue(p);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(Post.this, RetrieveData.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}