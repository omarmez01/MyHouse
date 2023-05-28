/**
 * This is the home page source code
 */

package com.example.myhouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Here is the add post onClick button function
    public void addPost(View view) {
        Intent t = new Intent(this,Post.class);
        startActivity(t);
    }

    // Here is the explore onCLick button function
    public void explore(View view) {
        Intent t = new Intent(this, RetrieveData.class);
        startActivity(t);
    }

    // The explore onCLick button function
    public void wishlist(View view) {
        Intent t = new Intent(this,WishList.class);
        startActivity(t);
    }

    // The inbox...
    public void inbox(View view) {
//        Intent t = new Intent(this,Inbox.class);
//        startActivity(t);
    }

    // And the profile
    public void profil(View view) {
        Intent t = new Intent(this,Profile.class);
        startActivity(t);
    }
}