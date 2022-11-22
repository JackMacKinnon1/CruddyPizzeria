package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button placeOrderBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        placeOrderBtn.setOnClickListener(createOrder);

    }

    private View.OnClickListener createOrder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, CreateOrder.class);
            startActivity(i);
        }
    };
}