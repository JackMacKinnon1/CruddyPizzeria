package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ViewAllOrders extends AppCompatActivity {

    TableRow orderRow;
    TextView cruddyPizzaHeaderTextView;
    LinearLayout stk;
    TableLayout ll;

    SharedPreferences prefs;

    List<String> Language;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);


        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);


        changeLanguage();

        //init();




    }

    public void init() {
        ll = (TableLayout) findViewById(R.id.displayLinear);
        ll.removeAllViews();

        for (int i = 0; i < 2; i++) {
//            TableRow row = (TableRow) findViewById(R.id.display_row);
//            checkBox = new CheckBox(this);
//            tv = new TextView(this);
//            addBtn = new ImageButton(this);
//            addBtn.setImageResource(R.drawable.add);
//            minusBtn = new ImageButton(this);
//            minusBtn.setImageResource(R.drawable.minus);
//            qty = new TextView(this);
//            checkBox.setText("hello");
//            qty.setText("10");
//            row.addView(checkBox);
//            row.addView(minusBtn);
//            row.addView(qty);
//            row.addView(addBtn);
//            ll.addView(row, i);

            TableRow row = new TableRow(this);
            row.setBackgroundResource(R.drawable.table_background);
            row.setMinimumWidth(200);
            row.setMinimumHeight(70);

            ll.addView(row);

        }

    }

    private void changeLanguage() {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.orderNumLookUpFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.orderNumLookUpEnglish));
        }

        //Setting the text of the views to be the language pulled in
        cruddyPizzaHeaderTextView.setText(Language.get(0));
    }
}