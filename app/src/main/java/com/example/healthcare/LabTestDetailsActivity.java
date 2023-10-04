package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tv_package, tv_totalCost;
    EditText ed_Details;
    Button btn_AddToCart, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tv_package = findViewById(R.id.LTD_packages);
        tv_totalCost = findViewById(R.id.LTD_textView);
        ed_Details = findViewById(R.id.LTD_EditText);
        btn_back = findViewById(R.id.LTD_backButton);
        btn_AddToCart = findViewById(R.id.LTD_AddToCart);

        ed_Details.setKeyListener(null);

        Intent intent = getIntent();
        tv_package.setText(intent.getStringExtra("text1"));
        ed_Details.setText(intent.getStringExtra("text2"));
        tv_totalCost.setText("Total Cost :" + intent.getStringExtra("text3") + "/-");

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });

        btn_AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tv_package.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(), "Healthcare", null, 1);

                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addToCart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });


    }
}