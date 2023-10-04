package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Prakash Kumar", "Hospital Address : Mumbai", "Experience : 5yrs", "600"},
                    {"Doctor Name : Ateeque Shaikh", "Hospital Address : Aurangabad", "Experience : 3yrs", "400"},
                    {"Doctor Name : Nilesh Gaikwad", "Hospital Address : Pune", "Experience : 7yrs", "900"},
                    {"Doctor Name : Kailash Tiwari", "Hospital Address : Nashik", "Experience : 4yrs", "600"},
                    {"Doctor Name : Sohail Khan", "Hospital Address : Raigad", "Experience : 10yrs", "1000"}

            };


    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Aakash Tiwari ", "Hospital Address : Goa", "Experience : 3yrs", "600"},
                    {"Doctor Name : Aanand Kumar", "Hospital Address : Pune", "Experience : 2yrs", "400"},
                    {"Doctor Name : Mayank Agarwal", "Hospital Address : Mumbai", "Experience : 7yrs", "1000"},
                    {"Doctor Name : Shifa Khan", "Hospital Address : Badlapur", "Experience : 5yrs", "750"},
                    {"Doctor Name : Rafiq Siddiqui", "Hospital Address : Kalyan", "Experience : 12yrs", "1500"}
            };


    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Ahtesham Momin", "Hospital Address : Mumbai", "Experience : 5yrs", "600"},
                    {"Doctor Name : Ateeque Shaikh", "Hospital Address : Jalna", "Experience : 3yrs", "400"},
                    {"Doctor Name : Abhishek Pathak", "Hospital Address : Pune", "Experience : 7yrs", "900"},
                    {"Doctor Name : Aayush Sharma", "Hospital Address : Nashik", "Experience : 4yrs", "600"},
                    {"Doctor Name : Sahil Khan", "Hospital Address : Panvel", "Experience : 10yrs", "1300"}

            };


    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Abhisar Sharma", "Hospital Address : Mumbai", "Experience : 5yrs", "800"},
                    {"Doctor Name : Shafiq Ansari", "Hospital Address : Malegaon", "Experience : 3yrs", "400"},
                    {"Doctor Name : Nilesh Gaikwad", "Hospital Address : Pune", "Experience : 7yrs", "1000"},
                    {"Doctor Name : Kailash Thakare", "Hospital Address : Manmad", "Experience : 4yrs", "550"},
                    {"Doctor Name : Aamir Shaikh", "Hospital Address : Kharghar", "Experience : 9yrs", "1300"}

            };


    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Awesh Waadekar", "Hospital Address : Nagpur", "Experience : 5yrs", "600"},
                    {"Doctor Name : Kiran Yadav", "Hospital Address : Bhusaval", "Experience : 3yrs", "400"},
                    {"Doctor Name : Aashish Gaikwad", "Hospital Address : Igatpuri", "Experience : 7yrs", "900"},
                    {"Doctor Name : Saumya Tiwari", "Hospital Address : Thane", "Experience : 4yrs", "600"},
                    {"Doctor Name :Ranveer Singh", "Hospital Address : Vashi", "Experience : 10yrs", "1000"}
            };
    TextView textView;
    Button backbtn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        textView = findViewById(R.id.LT_location);
        backbtn = findViewById(R.id.DD_back);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        textView.setText(title);

        if (title.compareTo("Physician") == 0) {
            doctor_details = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            doctor_details = doctor_details2;
        } else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details3;
        } else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctor_details4;
        } else
            doctor_details = doctor_details5;


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", "Cons_Fees:" + doctor_details[i][3] + "/-");
            list.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d}
        );

        ListView listView = findViewById(R.id.listViewLT);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                startActivity(it);


            }
        });
    }
}