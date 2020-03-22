package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Quiz_page extends AppCompatActivity {
    List<Question> quesList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestion, times, scored;
    RadioGroup radioGroup;
    RadioButton r1, r2, r3;
   // RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        QuizHelper db = new QuizHelper(this); // my question bank class
        quesList = db.getAllQuestions(); // this will fetch all quetonall
        // questions
        currentQ = quesList.get(qid); // the current question
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

       r1 = (RadioButton) findViewById(R.id.option1);
       r2 = (RadioButton) findViewById(R.id.option2);
        r3 = (RadioButton) findViewById(R.id.option3);
        scored = (TextView) findViewById(R.id.score);

        // the timer
        times = (TextView) findViewById(R.id.timers);
        // method which will set the things up for our game
        setQuestionView();
        times.setText("00:02:00");
        // A timer of 60 seconds to play for, with an interval of 1 second (1000
        // milliseconds)
        CounterClass timer = new CounterClass(60000, 1000);
        timer.start();

        // button click listeners

       r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // passing the button text to other method
                // to check whether the answer is correct or not
                // same for all three buttons
                getAnswer(r1.getText().toString());
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(r2.getText().toString());
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnswer(r3.getText().toString());
            }
        });

    }






    public void getAnswer (String AnswerString){
            if (currentQ.getANSWER().equals(AnswerString)) {
                // if conditions matches increase the int (score) by 1
                // and set the text of the score view
                score++;
                scored.setText("Score : " + score);
            } else {
                // if unlucky start activity and finish the game
                Intent intent = new Intent(Quiz_page.this,
                        result.class);
                // passing the int value
                Bundle b = new Bundle();
                b.putInt("score", score); // Your score
                intent.putExtras(b); // Put your score to your next
                startActivity(intent);
                finish();
            }

            if (qid < 5) {
                // if questions are not over then do this
                currentQ = quesList.get(qid);
                setQuestionView();
            } else {
                // if over do this
                Intent intent = new Intent(Quiz_page.this,
                        result.class);
                Bundle b = new Bundle();
                b.putInt("score", score); // Your score
                intent.putExtras(b); // Put your score to your next
                startActivity(intent);
                finish();
            }
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NewApi")
        public class CounterClass extends CountDownTimer {
            public CounterClass(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
                // TODO Auto-generated constructor stub
            }

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                long millis = millisUntilFinished;
                String hms = String.format(
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                                .toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                                .toMinutes(millis)));
                System.out.println(hms);
                times.setText(hms);

            }

            @Override
            public void onFinish() {
                times.setText("Time is up");
            }

        }
        private void setQuestionView () {
            // the method which will put all things together
            txtQuestion.setText(currentQ.getQUESTION());
            r1.setText(currentQ.getOPTA());
            r2.setText(currentQ.getOPTB());
            r3.setText(currentQ.getOPTC());

            r1.setSelected(false);
            r2.setSelected(false);
            r3.setSelected(false);
            qid++;
        }


    }

