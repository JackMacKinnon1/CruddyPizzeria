package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewAllOrders extends AppCompatActivity {

    TableRow orderRow;
    LinearLayout stk;
    TableLayout ll;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);



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

}