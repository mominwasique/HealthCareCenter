package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetails extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_details);

        btn_back = findViewById(R.id.HAD_Back);
        textView = findViewById(R.id.HAD_healthArticles);
        imageView = findViewById(R.id.HADImage);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("text2");
            imageView.setImageResource(resId);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesDetails.this, HealthArticles.class));
            }
        });
    }
}