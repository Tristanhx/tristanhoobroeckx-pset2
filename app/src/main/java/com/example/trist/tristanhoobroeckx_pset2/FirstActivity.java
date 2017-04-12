package com.example.trist.tristanhoobroeckx_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    protected void gotoSecond(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
