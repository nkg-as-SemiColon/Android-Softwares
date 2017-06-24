package com.loutasae.tolet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void uploadclicked(View v){
        Intent i = new Intent(this,Upload.class);
        startActivity(i);
    }

    public void findclicked(View v){
        Intent i = new Intent(this,Find.class);
        startActivity(i);
    }
}
