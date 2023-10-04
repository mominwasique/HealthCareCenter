package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticles extends AppCompatActivity {

    private String[][] health_articles = {
            {"Walking daily", "", "", "Click More Details"},
            {"home care of Covid 19", "", "", "Click More Details"},
            {"stop Smoking", "", "", "Click More Details"},
            {"Menstrual Cramps", "", "", "Click More Details"},
            {"Healthy Gut", "", "", "Click More Details"},
    };

    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };

    HashMap<String, String> item;
    ArrayList arrayList;
    ListView listView;
    SimpleAdapter simpleAdapter;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        btn_back = findViewById(R.id.HA_Back);
        listView = findViewById(R.id.HA_ListView);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticles.this, HomeActivity.class));
            }
        });

        arrayList = new ArrayList();
        for (int i = 0; i < health_articles.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_articles[i][0]);
            item.put("line2", health_articles[i][1]);
            item.put("line3", health_articles[i][2]);
            item.put("line4", health_articles[i][3]);
            arrayList.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(HealthArticles.this, HealthArticlesDetails.class);
                it.putExtra("text1", health_articles[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });
    }
}