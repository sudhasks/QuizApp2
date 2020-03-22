package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz_Rules extends AppCompatActivity {
    Button bacK_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__rules);
        bacK_btn = (Button)findViewById(R.id.to_back);
        bacK_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Quiz_Rules.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
