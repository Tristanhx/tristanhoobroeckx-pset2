package com.example.trist.tristanhoobroeckx_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    EditText text;
    Story storyBuilder = new Story();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = (EditText)findViewById(R.id.edittext);
        text.setHint(storyBuilder.getNextPlaceholder());
    }

    protected void gotoThird(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
        finish();
    }

    protected void SubmitWord(View view){
        if (storyBuilder.isFilledIn() != true){
            String word = text.getText().toString();
            storyBuilder.fillInPlaceholder(word);
        }
        else
            gotoThird();
    }
}
