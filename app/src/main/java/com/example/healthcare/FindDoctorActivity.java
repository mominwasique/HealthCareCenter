package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView back = findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView physician = findViewById(R.id.physician);
        physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Physician");
                startActivity(intent);
            }
        });

        CardView dietitian = findViewById(R.id.dietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Dietician");
                startActivity(intent);
            }
        });

        CardView surgeon = findViewById(R.id.surgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Surgeon");
                startActivity(intent);
            }
        });

        CardView dentist = findViewById(R.id.dentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Dentist");
                startActivity(intent);
            }
        });

        CardView cardiologist = findViewById(R.id.cardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                intent.putExtra("title", "Cardiologist");
                startActivity(intent);
            }
        });
    }
}