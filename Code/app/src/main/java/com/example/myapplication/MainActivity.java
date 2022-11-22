package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button placeOrderBtn, viewOrderBtn, changeOrderBtn, cancelOrderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        viewOrderBtn = findViewById(R.id.viewOrderBtn);
        changeOrderBtn = findViewById(R.id.changeOrderBtn);
        cancelOrderBtn = findViewById(R.id.cancelOrderBtn);



        placeOrderBtn.setOnClickListener(createOrder);
        viewOrderBtn.setOnClickListener(searchOrder);
        changeOrderBtn.setOnClickListener(searchOrder);
        cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewAllOrders.class);
                startActivity(i);
            }
        });


    }

    private View.OnClickListener createOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, CreateOrder.class);
            startActivity(i);
        }
    };

    private View.OnClickListener searchOrder = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this, OrderNumberLookUp.class);
            startActivity(i);
        }
    };


}