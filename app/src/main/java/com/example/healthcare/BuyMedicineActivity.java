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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] medicine =
            {
                    {" Otezla ", "", "", "120"},
                    {" Doxycycline ", "", "", "80"},
                    {" Invokana ", "", "", "128"},
                    {" Clonazepam", "", "", "150"},
                    {" Lisinopril ", "", "", "165"},
                    {" Lyrica ", "", "", "220"},
                    {" Metformin ", "", "", "180"},
                    {" clindamycin ", "", "", "100"},


            };

    private String medicine_details[] = {
            "Otezla (apremilast) is a tablet taken twice a day that is used to lower \n" +
                    "inflammation in the treatment of specific types of psoriatic arthritis, plaque psoriasis and Behçet's disease. ",
            "Doxycycline is a tetracycline antibiotic that inhibits bacterial growth \n" +
                    "and is thought to have anti-inflammatory effects",
            "Invokana is an oral diabetes medicine that helps control blood sugar levels. Canagliflozin works by \n" +
                    " helping the kidneys get rid of glucose from your bloodstream.",
            "Clonazepam is a benzodiazepine (ben-zoe-dye-AZE-eh-peen).\n " +
                    "It is thought that benzodiazepines work by enhancing the activity of certain neurotransmitters in the brain.",
            "Lisinopril is used to treat high blood pressure (hypertension) in adults and children who are at least 6 years old",
            "Lyrica is used to treat pain caused by fibromyalgia, or nerve pain in people with diabetes (diabetic neuropathy), \n" +
                    "herpes zoster (post-herpetic neuralgia), or spinal cord injury.",
            "Metformin is an FDA-approved antidiabetic agent that manages high blood sugar levels in type 2 diabetes patients. \n" +
                    "It reduces glucose absorption from the intestines, lowers liver glucose production, \n" +
                    "and improves insulin sensitivity. Metformin is recommended with dietary changes and exercise for better results.",
            "Clindamycin is an antibiotic that fights bacteria in the body.\n" +
                    "Clindamycin is used to treat serious infections caused by bacteria.\n" +
                    "Clindamycin may also be used for purposes not listed in this medication guide."

    };

    HashMap<String, String> item;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    ListView listView;
    Button btn_back, GoToCart_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView = findViewById(R.id.BM_listView);
        btn_back = findViewById(R.id.BM_backButton);
        GoToCart_btn = findViewById(R.id.BM_GoToCart);

        GoToCart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        arrayList = new ArrayList();
        for (int i = 0; i < medicine.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", medicine[i][0]);
            item.put("line2", medicine[i][1]);
            item.put("line3", medicine[i][2]);
            item.put("line4", "Total Cost:" + medicine[i][3] + "/-");
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
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", medicine[i][0]);
                it.putExtra("text2", medicine_details[i]);
                it.putExtra("text3", medicine[i][3]);
                startActivity(it);
            }
        });
    }
}