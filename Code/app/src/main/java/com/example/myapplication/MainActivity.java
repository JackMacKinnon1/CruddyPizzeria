package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button placeOrderBtn, viewOrderBtn, changeOrderBtn, cancelOrderBtn, viewAllOrdersBtn;

    TextView cruddyPizzaHeaderTextView, orderNumberLoopUpTextView, languageTextView;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch languageSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connecting views
        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        viewOrderBtn = findViewById(R.id.viewOrderBtn);
        changeOrderBtn = findViewById(R.id.changeOrderBtn);
        cancelOrderBtn = findViewById(R.id.cancelOrderBtn);
        viewAllOrdersBtn = findViewById(R.id.viewAllOrdersBtn);
        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);
        orderNumberLoopUpTextView = findViewById(R.id.orderNumberLookUpTextView);
        languageTextView = findViewById(R.id.languageTextView);
        languageSwitch = findViewById(R.id.languageSwitch);


        //Connecting listeners
        viewOrderBtn.setOnClickListener(searchOrder);
        changeOrderBtn.setOnClickListener(searchOrder);
        cancelOrderBtn.setOnClickListener(searchOrder);
        placeOrderBtn.setOnClickListener(createOrder);
        viewAllOrdersBtn.setOnClickListener(viewAllOrders);




    }

    private View.OnClickListener searchOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Determine the action in the order look up
            String action = "";

            switch(v.getId()){
                case R.id.viewOrderBtn:
                    action = "VIEW";
                    break;
                case R.id.changeOrderBtn:
                    action = "EDIT";
                    break;
                case R.id.cancelOrderBtn:
                    action = "DELETE";
                    break;
            }

            //Creating intent
            Intent i = new Intent(MainActivity.this, OrderNumberLookUp.class);

            Bundle extras = new Bundle();
            extras.putString("ACTION", action);
            i.putExtras(extras);
            startActivity(i);

        }
    };

    private View.OnClickListener createOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, CreateOrder.class);
            startActivity(i);
        }
    };

    private View.OnClickListener viewAllOrders = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, ViewAllOrders.class);
            startActivity(i);
        }
    };



}