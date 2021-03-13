package com.example.flashcardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final String[] questionAnswer = {"Who is the president of the United States?", "Joe Biden"};

    // final boolean[] showAnswers = {true};
    final boolean[] flipped = {false};
    int currentCardDisplayedIndex = -1;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcard = findViewById(R.id.flashcard_question);
        /*
        TextView answer1 = findViewById(R.id.answer1);
        TextView answer2 = findViewById(R.id.answer2);
        TextView answer3 = findViewById(R.id.answer3);
        ImageView eyeToggle = findViewById(R.id.eyeToggle);
        Button resetButton = findViewById(R.id.resetButton);
         */
        ImageView addCardButton = findViewById(R.id.addCardButton);
        ImageView nextCardButton = findViewById(R.id.nextCardButton);



        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            questionAnswer[0] = allFlashcards.get(0).getQuestion();
            questionAnswer[1] = allFlashcards.get(0).getAnswer();
            flashcard.setText(questionAnswer[0]);
            flipped[0] = false;
            currentCardDisplayedIndex = 0;
        }

        /*
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
                flashcard.setText(questionAnswer[0]);
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
         */

        flashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                // findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                if (!flipped[0]) {
                    flashcard.setText(questionAnswer[1]);
                    flipped[0] = true;
                } else {
                    flashcard.setText(questionAnswer[0]);
                    flipped[0] = false;
                }
            }
        });

        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });

        nextCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCardDisplayedIndex == -1) {
                    return;
                }
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(v,
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                    questionAnswer[0] = allFlashcards.get(0).getQuestion();
                    questionAnswer[1] = allFlashcards.get(0).getAnswer();
                    flashcard.setText(questionAnswer[0]);
                    flipped[0] = false;
                } else {
                    questionAnswer[0] = allFlashcards.get(currentCardDisplayedIndex).getQuestion();
                    questionAnswer[1] = allFlashcards.get(currentCardDisplayedIndex).getAnswer();
                    flashcard.setText(questionAnswer[0]);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            questionAnswer[0] = question;
            questionAnswer[1] = answer;

            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();
            currentCardDisplayedIndex = allFlashcards.size() - 1;

            ((TextView) findViewById(R.id.flashcard_question)).setText(questionAnswer[0]);
            flipped[0] = false;
            // showAnswers[0] = false;
            // hide em all and switch to close eye
            /*
            findViewById(R.id.answer1).setVisibility(View.INVISIBLE);
            findViewById(R.id.answer2).setVisibility(View.INVISIBLE);
            findViewById(R.id.answer3).setVisibility(View.INVISIBLE);
            ((ImageView) findViewById(R.id.eyeToggle)).setImageResource(R.drawable.closeeye);
            */


        }
    }
}