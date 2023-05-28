/**
 * Sign Up class
 */

package com.example.myhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SignUp extends AppCompatActivity {
    EditText email,efirstname,elastname,epassword,ephone;
    Button esingin;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    // Signing up to firebase
    public void Signup_su(View view) {

        String firstname= efirstname.getText().toString().trim();
        String lastname= elastname.getText().toString().trim();
        String phone= ephone.getText().toString().trim();
        String mail= email.getText().toString().trim();
        String password= epassword.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUser=task.getResult().getUser();
                    DatabaseReference newUser=databaseReference.child(firebaseUser.getUid());
                    newUser.child("First Name :") .setValue(firstname);
                    newUser.child("Last Name :").setValue(lastname);
                    newUser.child("phone :").setValue(phone);
                    newUser.child("email :").setValue(mail);
                    newUser.child("Password : ").setValue(password);
                    Intent t = new Intent(SignUp.this, Home.class);
                    startActivity(t);
                    Toast.makeText(SignUp.this,"Registration Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}