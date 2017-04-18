package com.example.trist.tristanhoobroeckx_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView storytext;
    String receivedStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        storytext = (TextView) findViewById(R.id.story);
        Intent intent = getIntent();
        receivedStory = intent.getStringExtra("storyextra");

        if (savedInstanceState != null){
            storytext.setText(savedInstanceState.getString("story"));
        }
        else {
            storytext.setText(receivedStory);
        }
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("story", receivedStory);
    }




}
