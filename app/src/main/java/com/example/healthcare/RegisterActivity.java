package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText regUsername, regEmail, regPassword, regConfirmPassword;
    Button registerBtn;
    TextView haveAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regUsername = findViewById(R.id.fullname);
        regEmail = findViewById(R.id.address);
        regPassword = findViewById(R.id.contactNo);
        regConfirmPassword = findViewById(R.id.confirmPassword);
        registerBtn = findViewById(R.id.bookAptBtn);
        haveAccount = findViewById(R.id.haveAccount);

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginPage.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regUsername.getText().toString();
                String password = regPassword.getText().toString();
                String email = regEmail.getText().toString();
                String cnfPassword = regConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "HealthCare", null, 1);

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || cnfPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, " Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(cnfPassword) == 0) {
                        if (isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginPage.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "Password must contain at least 8 characters, having letter, digit and special symbols", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Password and confirm password should be same", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    public static boolean isValid(String passwordCheck) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordCheck.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordCheck.length(); p++) {
                if (Character.isLetter(passwordCheck.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordCheck.length(); r++) {
                if (Character.isDigit(passwordCheck.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordCheck.length(); s++) {
                char c = passwordCheck.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }

            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;

        }
    }


}