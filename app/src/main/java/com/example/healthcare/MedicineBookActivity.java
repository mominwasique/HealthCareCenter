package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedicineBookActivity extends AppCompatActivity {

    EditText ed_name, ed_address, ed_pin, ed_contact;
    Button book_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_book);

        ed_name = findViewById(R.id.MDB_full_name);
        ed_address = findViewById(R.id.MDB_address);
        ed_pin = findViewById(R.id.MDB_pincode);
        ed_contact = findViewById(R.id.MDB_contact_no);
        book_button = findViewById(R.id.MDB_book_Btn);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        // String time = intent.getStringExtra("time");

        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
                db.addOrder(username, ed_name.getText().toString(), ed_address.getText().toString(), ed_contact.getText().toString(),
                        ed_pin.getText().toString(), date.toString(), "",
                        Float.parseFloat(price[1].toString()), "medicine");
                db.removeCart(username, "medicine");
                Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedicineBookActivity.this, HomeActivity.class));
            }
        });
    }
}