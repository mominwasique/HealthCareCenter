package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    HashMap<String, String> item;
    ArrayList arrayList;
    SimpleAdapter simpleAdapter;
    TextView tv_totalCost;
    ListView listView;
    private DatePickerDialog datePickerDialog;
    //private TimePickerDialog timePickerDialog;
    private Button date_button, checkOut_button, back_button;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        date_button = findViewById(R.id.CBM_Apt_button);
        checkOut_button = findViewById(R.id.CBM_checkout);
        back_button = findViewById(R.id.CBM_backButton);
        tv_totalCost = findViewById(R.id.CBM_totalCost);
        listView = findViewById(R.id.CBM_listView);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "Healthcare", null, 1);

        float total_amount = 0;
        ArrayList db_data = db.getCartData(username, "medicine");
        // Toast.makeText(getApplicationContext(),""+db_data,Toast.LENGTH_LONG).show();

        packages = new String[db_data.size()][];
        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[4];
        }

        for (int i = 0; i < db_data.size(); i++) {
            String arr_data = db_data.get(i).toString();
            String[] str_data = arr_data.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = str_data[0];
            packages[i][3] = "Cost :" + str_data[1] + "/-";
            total_amount = total_amount + Float.parseFloat(str_data[1]);
        }

        tv_totalCost.setText("Total Cost :" + total_amount);

        arrayList = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", "" + packages[i][3] + "");
            arrayList.add(item);
        }

        simpleAdapter = new SimpleAdapter(this, arrayList,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4",},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d,});
        listView.setAdapter(simpleAdapter);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class));
            }
        });

        checkOut_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CartBuyMedicineActivity.this, MedicineBookActivity.class);
                it.putExtra("price", tv_totalCost.getText());
                it.putExtra("date", date_button.getText());
                // it.putExtra("time",time_button.getText());
                startActivity(it);
            }
        });

        initDatePicker();   //datePicker

        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

         /*initTimePicker();   //timePicker
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });*/

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date_button.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() + 86400000);
    }

  /* private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                time_button.setText(hour + ":" + minute);

            }
        };
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hour, minute, true);

    }*/
}