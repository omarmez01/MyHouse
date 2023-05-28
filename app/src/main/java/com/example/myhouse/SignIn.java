/**
 * Sign In class
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
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    EditText email,epassword;
    Button esingin;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    // Auth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email=findViewById(R.id.emailInput_li);
        epassword=findViewById(R.id.passwordInput_li);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void Login_li(View view) {

        // Try to log to firebase if success then display a toast message and log in
        String mail=email.getText().toString().trim();
        String password=epassword.getText().toString().trim();
        if(!mail.isEmpty() && !password.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignIn.this,("welcome"),Toast.LENGTH_SHORT).show();
                        Intent t = new Intent(SignIn.this,Home.class);
                        startActivity(t);
                    }
                }
            });

        }
    }

    // Sign up button
    public void signup(View view) {
        Intent t = new Intent(this, SignUp.class);
        startActivity(t);
    }
}