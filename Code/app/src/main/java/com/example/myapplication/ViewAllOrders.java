package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.*;
import android.database.*;
import com.example.myapplication.DBAdapter;

import java.util.Arrays;
import java.util.List;
import java.io.*;

public class ViewAllOrders extends AppCompatActivity {

    TableRow orderRow;
    TextView cruddyPizzaHeaderTextView;
    TableLayout ll;

    SharedPreferences prefs;

    List<String> Language;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);


        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);
        ll = findViewById(R.id.displayLinear);

        changeLanguage();

        //query database for all orders
        DBAdapter db = new DBAdapter(this);
        db.open();
        Cursor c = db.getAllRecords();
        LayoutInflater inflator = LayoutInflater.from(this);

        //loop through all orders
        if (c.moveToFirst()) {
            do {
                View tr = inflator.inflate(R.layout.order_cell, null, false);
                TextView orderNumber = tr.findViewById(R.id.OrderNumberTextView);
                TextView orderName = tr.findViewById(R.id.NameOnOrderTextView);

                //get the primary key of the order
                String orderNum = c.getString(0);

                //get the name on the order
                String name = c.getString(1);

                //set the text of the textviews
                orderNumber.setText(orderNum);
                orderName.setText(name);


                ll.addView(tr);
            } while (c.moveToNext());
        }





    }
//
//    public void init() {
//
//        LayoutInflater inflator = LayoutInflater.from(this);
//
//        for (int i = 0; i < 2; i++) {
//
//            View tr = inflator.inflate(R.layout.order_cell, null, false);
//            ll.addView(tr);
//
//
////            ll.addView(row);
//
//        }
//
//    }

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