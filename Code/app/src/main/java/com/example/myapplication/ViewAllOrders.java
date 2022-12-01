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

import java.util.Arrays;
import java.util.List;

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

        LayoutInflater inflator = LayoutInflater.from(this);

        for (int i = 0; i < 10; i++) {

            View tr = inflator.inflate(R.layout.order_cell, null, false);
            TextView orderNumber = tr.findViewById(R.id.OrderNumberTextView);
            orderNumber.setText("Order Number: " + (i + 1));
            ll.addView(tr);


//            ll.addView(row);

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