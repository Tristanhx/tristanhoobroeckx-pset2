package com.example.trist.tristanhoobroeckx_pset2;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    EditText text;
    Story storyBuilder;
    TextView counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = (EditText)findViewById(R.id.edittext);
        counter = (TextView) findViewById(R.id.counter);


        if (savedInstanceState != null){
            storyBuilder = (Story) savedInstanceState.getSerializable("text");
            text.setHint(storyBuilder.getNextPlaceholder());
            counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount()));
        }
        else {
            try {
                storyBuilder = new Story(getAssets().open("madlib0_simple.txt"));
                text.setHint(storyBuilder.getNextPlaceholder());
                counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("text", storyBuilder);
//        outState.putString("hint", (String) text.getHint());
//        outState.putInt("counter", storyBuilder.getPlaceholderRemainingCount());
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
            text.setHint(storyBuilder.getNextPlaceholder());
            counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount()));
        }
        else
            gotoThird();
    }
}
