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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {" Full Body Checkup", "", "", "1200"},
                    {" Blood Glucose Fasting", "", "", "800"},
                    {" Covid 19 Antibody - IgG", "", "", "400"},
                    {" Thyroid Checkup", "", "", "750"},
                    {" Immunity Checkup", "", "", "600"},


            };

    private String[] packages_details = {

            "Blood Glucose Fasting\n" +
                    "Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "Covid-19 Antibody - IgG",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };

    HashMap<String, String> item;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    Button btn_GoToCart, btn_back;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btn_GoToCart = findViewById(R.id.LT_GoToCart);
        btn_back = findViewById(R.id.LT_backButton);
        listView = findViewById(R.id.listViewLT);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        arrayList = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", "Total Cost:" + packages[i][3] + "/-");
            arrayList.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, arrayList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4",},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d,});
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                it.putExtra("text1", packages[i][0]);
                it.putExtra("text2", packages_details[i]);
                it.putExtra("text3", packages[i][3]);
                startActivity(it);
            }
        });

        btn_GoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));

            }
        });
    }
}