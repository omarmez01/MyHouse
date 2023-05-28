/**
 * Profile class: display the profile information
 */

package com.example.myhouse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;

public class Profile extends AppCompatActivity {
    ImageView iprofile;
    TextView ifirstName,iLastName,imail,iphone;
    Button edit;
    private static final int Post_Code = 1;
    Uri imageUrl = null;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // Display the user info
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        iprofile=findViewById(R.id.iprofile);
        imail=findViewById(R.id.imail);
        ifirstName=findViewById(R.id.ifirstName);
        iLastName=findViewById(R.id.iLastName);
        iphone=findViewById(R.id.iphone);
        edit=findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Post_Code);
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            imail.setText(email);
        }
    }

    // Change the profile image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Post_Code && resultCode == RESULT_OK) {
            imageUrl = data.getData();
            iprofile.setImageURI(imageUrl);
        }
    }
}