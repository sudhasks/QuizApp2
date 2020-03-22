package com.example.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    TextView mDisplaydate;
    TextView tologin;
    EditText fn, ln, email, p;
    Button reg;

    private DatePickerDialog.OnDateSetListener mDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tologin = (TextView) findViewById(R.id.back_login);

        //login text in registration form will lead to the login page
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent log = new Intent(Register.this, Signin.class);
                startActivity(log);
            }
        });

        // to get date from user
        mDisplaydate = (TextView) findViewById(R.id.dob);
        mDisplaydate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Register.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListner,
                        year, day, month
                );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + year + "/" + month + "/" + dayOfMonth);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplaydate.setText(date);
            }
        };
        fn = (EditText) findViewById(R.id.fname);
        ln = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.Email);
        p = (EditText) findViewById(R.id.pass);
        reg = (Button) findViewById(R.id.register_btn);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //data validation using checkDataEntered function
                checkDataEntered();
            }
        });
    }

    //check if edittext is empty
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    //check if the email is valid
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    void checkDataEntered() {
        boolean isValid = true;
        if (isEmpty(fn)) {
            fn.setError("First name is required");
            // Toast.makeText(getApplicationContext(), "You must enter first name!", Toast.LENGTH_LONG).show();
        } else {
            if ((fn.getText().toString().length() < 3) || (fn.getText().toString().length() > 30)) {
                fn.setError("first name should at least 3 char and not more than 30");
                isValid = false;
            }

        }
        if (isEmpty(ln)) {
            ln.setError("Last name is required");
            isValid = false;
        }
        if (isEmail(email) == false) {
            email.setError("Enter valid email!");
            isValid = false;

        }
        if (isEmpty(p)) {
            p.setError("Must enter password ");
            isValid = false;
        } else {
            if (p.getText().toString().length() < 4) {
                p.setError("Password must be 4 character long");
                isValid = false;
            }

        }
        //if the validations satisfied , will take to the mainactivity
        if (isValid) {
            Intent i = new Intent(Register.this, MainActivity.class);
            startActivity(i);
            this.finish();

        }

    }
}
