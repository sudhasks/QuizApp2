package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    TextView toreg;
    EditText uid,pa;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        setupUI();
        setupListners();
    }
    private void setupUI() {
        uid = (EditText) findViewById(R.id.username);
        pa = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_btn);
        toreg = (TextView)findViewById(R.id.text_register);
    }

    // add listners with the setupListners method
    private void setupListners() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
            }


        });

        toreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg = new Intent(Signin.this,Register.class);
                startActivity(reg);
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean isEmail(EditText text){
        CharSequence email=text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //template for checkUsername
    private void checkUsername() {
        boolean isValid = true;

        // check username validation for login
        if (isEmpty(uid)) {
            uid.setError("Enter username to login!!");
            isValid = false;
        } else {
            if (!isEmail(uid)) {
                uid.setError("Enter valid Email");
                isValid = false;
            }
        }

        //checks password validation for login
        if (isEmpty(pa)) {
            pa.setError("Must enter password to login!");
            isValid = false;
        } else {
            if (pa.getText().toString().length() < 4) {
                pa.setError("Password must be 4 character long");
                isValid = false;
            }
        }

        //check email and password
        //here should be call to backend or safer function for local check
        if (isValid) {
            String usernameValue = uid.getText().toString().trim();
            String passwordValue = pa.getText().toString().trim();
            if (usernameValue.equals("shahs37@montclair.edu") && passwordValue.equals("password1")) {
                // everything checked and will open new activity
                Intent i = new Intent(Signin.this, Main2Activity.class);
                startActivity(i);
                //we close this activity
                this.finish();
            } else {
                Toast t = Toast.makeText(this, "wrong Email or password!", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }}

