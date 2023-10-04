package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] order_details = {};

    HashMap<String, String> item;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    ListView listView;
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        back_button = findViewById(R.id.OD_Back);
        listView = findViewById(R.id.OD_listView);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(), "Healthcare", null, 1);
        ArrayList db_data = db.getOrderData(username);

        order_details = new String[db_data.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[4];
            String arr_data = db_data.get(i).toString();
            String[] str_data = arr_data.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = str_data[0];
            order_details[i][1] = str_data[4];
            if (str_data[7].compareTo("medicine") == 0) {
                order_details[i][1] = "Del : " + str_data[4];
            } else {
                order_details[i][1] = "Del :" + str_data[4] + " " + str_data[5];
            }
            order_details[i][2] = str_data[6];
            order_details[i][3] = str_data[7];
        }

        arrayList = new ArrayList();
        for (int i = 0; i < order_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            arrayList.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d});
        listView.setAdapter(simpleAdapter);
    }
}