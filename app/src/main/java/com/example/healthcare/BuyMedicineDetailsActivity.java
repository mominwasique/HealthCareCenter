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

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tv_packageName, tv_totalCost;
    EditText ed_details;
    Button AddToCart_btn, Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tv_packageName = findViewById(R.id.BMD_packages);
        tv_totalCost = findViewById(R.id.BMD_totalCost);
        ed_details = findViewById(R.id.BMD_EditText);
        AddToCart_btn = findViewById(R.id.BMD_AddToCart);
        Back_btn = findViewById(R.id.BMD_backButton);

        Intent intent = getIntent();
        tv_packageName.setText(intent.getStringExtra("text1"));
        ed_details.setText(intent.getStringExtra("text2"));
        tv_totalCost.setText("Total Cost :" + intent.getStringExtra("text3") + "/-");

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        AddToCart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String product = tv_packageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addToCart(username, product, price, "medicine");
                    Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}