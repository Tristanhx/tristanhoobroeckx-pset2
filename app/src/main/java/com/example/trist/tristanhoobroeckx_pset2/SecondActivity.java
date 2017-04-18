package com.example.trist.tristanhoobroeckx_pset2;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    EditText text;
    Story storyBuilder;
    TextView counter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text = (EditText)findViewById(R.id.edittext);
        counter = (TextView) findViewById(R.id.counter);
        button = (Button) findViewById(R.id.buttonsubmit);


        if (savedInstanceState != null){
            storyBuilder = (Story) savedInstanceState.getSerializable("object");
            text.setHint(storyBuilder.getNextPlaceholder());
            counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount())+" of "+
                    Integer.toString(storyBuilder.getPlaceholderCount()));
        }
        else {
            try {
                storyBuilder = new Story(getAssets().open("madlib0_simple.txt"));
                text.setHint(storyBuilder.getNextPlaceholder());
                counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount())+" of "+
                Integer.toString(storyBuilder.getPlaceholderCount()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("object", storyBuilder);
    }

    protected void gotoThird(){
        String finished_story = storyBuilder.toString();

        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("storyextra", finished_story);

        startActivity(intent);
        finish();
    }

    protected void SubmitWord(View view) {
        if (text.getText().toString().trim().length() > 0) {
            if (storyBuilder.getPlaceholderRemainingCount() > 1) {
                String word = text.getText().toString();
                storyBuilder.fillInPlaceholder(word);
                text.setHint(storyBuilder.getNextPlaceholder());
                counter.setText(Integer.toString(storyBuilder.getPlaceholderRemainingCount()) + " of " +
                        Integer.toString(storyBuilder.getPlaceholderCount()));
                text.setText("");
            }
        else {
            button.setText("Go to Story!");
            String word = text.getText().toString();
            storyBuilder.fillInPlaceholder(word);
            gotoThird();
            }
        }
        else {
            Toast.makeText(this, "Fill in a word!", Toast.LENGTH_SHORT).show();
        }
    }
}
