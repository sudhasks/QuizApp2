package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView rule_page;
    Button game_start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rule_page=(TextView)findViewById(R.id.game_rule);
        game_start_btn=(Button)findViewById(R.id.start_Quiz);

        rule_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Main2Activity.this,Quiz_Rules.class);
                startActivity(i);


            }
        });

        game_start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startQuiz();
                Intent i =new Intent(Main2Activity.this,Quiz_page.class);
                startActivity(i);

            }
        });
    }
    }

