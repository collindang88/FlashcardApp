package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcard = findViewById(R.id.flashcard_question);
        TextView answer1 = findViewById(R.id.answer1);
        TextView answer2 = findViewById(R.id.answer2);
        TextView answer3 = findViewById(R.id.answer3);
        ImageView eyeToggle = findViewById(R.id.eyeToggle);
        Button resetButton = findViewById(R.id.resetButton);

        final boolean[] showAnswers = {true};
        final boolean[] flipped = {false};

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(getResources().getColor(R.color.green, null));
                answer2.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(getResources().getColor(R.color.green, null));
                answer3.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                answer2.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                answer3.setBackgroundColor(getResources().getColor(R.color.yellow, null));
                flashcard.setText("Who is the president of the United States?");
                flipped[0] = false;
            }
        });

        eyeToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (showAnswers[0]) {
                    showAnswers[0] = false;
                    // hide em all and switch to close eye
                    answer1.setVisibility(View.INVISIBLE);
                    answer2.setVisibility(View.INVISIBLE);
                    answer3.setVisibility(View.INVISIBLE);
                    eyeToggle.setImageResource(R.drawable.closeeye);

                } else {
                    showAnswers[0] = true;
                    // show em all and switch to open eye
                    answer1.setVisibility(View.VISIBLE);
                    answer2.setVisibility(View.VISIBLE);
                    answer3.setVisibility(View.VISIBLE);
                    eyeToggle.setImageResource(R.drawable.openeye);
                }
            }
        });





        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                // findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                if (!flipped[0]) {
                    flashcard.setText("Joe Biden");
                    flipped[0] = true;
                } else {
                    flashcard.setText("Who is the president of the United States?");
                    flipped[0] = false;
                }
            }
        });

        /*
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });
        */
    }
}