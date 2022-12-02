package com.example.myapplication;
//import database tools
import android.database.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.example.myapplication.DBAdapter;
import java.io.*;

public class CreateOrder extends AppCompatActivity {

    Spinner toppingOneSpinner, toppingTwoSpinner, toppingThreeSpinner;
    RadioGroup sizeRadioGroup;
    RadioButton smallRadioBtn, mediumRadioBtn, largeRadioBtn;
    EditText nameEditText;
    Button submitBtn;
    String size = "";
    TextView newOrderTextView, cruddyPizzaHeaderTextView, sizeTextView, toppingsTextView, yourNameTextView;

    SharedPreferences prefs;

    List<String> Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);



        toppingOneSpinner = findViewById(R.id.toppingOneSpinner);
        toppingTwoSpinner = findViewById(R.id.toppingTwoSpinner);
        toppingThreeSpinner = findViewById(R.id.toppingThreeSpinner);
        submitBtn = findViewById(R.id.submitBtn);
        sizeRadioGroup = findViewById(R.id.sizeRadioGroup);
        smallRadioBtn = findViewById(R.id.smallRadioBtn);
        mediumRadioBtn = findViewById(R.id.mediumRadioBtn);
        largeRadioBtn = findViewById(R.id.largeRadioBtn);
        nameEditText = findViewById(R.id.nameEditText);
        newOrderTextView = findViewById(R.id.orderNumberTextView);
        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);
        sizeTextView = findViewById(R.id.customerNameTextView);
        toppingsTextView = findViewById(R.id.toppingsTextView);
        yourNameTextView = findViewById(R.id.yourNameTextView);



        submitBtn.setOnClickListener(submit);
        smallRadioBtn.setOnClickListener(sizeEntry);
        mediumRadioBtn.setOnClickListener(sizeEntry);
        largeRadioBtn.setOnClickListener(sizeEntry);

        changeLanguage();


    }


    View.OnClickListener sizeEntry = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton temp = (RadioButton) v;

            size = temp.getText().toString();
        }
    };

    View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Objects.equals(size, "") || nameEditText.getText().toString().equals(""))
            {
                return;
            }
            //open database and make a new order
            DBAdapter db = new DBAdapter(CreateOrder.this);
            db.open();
            long id = db.insertRecord(nameEditText.getText().toString(), size, toppingOneSpinner.getSelectedItem().toString(), toppingTwoSpinner.getSelectedItem().toString(), toppingThreeSpinner.getSelectedItem().toString());
            db.close();

            //Redirect to home
            Intent i = new Intent(CreateOrder.this, MainActivity.class);
            //Toast pop up
            Toast toast = Toast.makeText(getApplicationContext(), "Order created!", Toast.LENGTH_LONG);
            toast.show();
            startActivity(i);
        }
    };

    private void changeLanguage()
    {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        String test = prefs.getString("LANGUAGE", "");
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.createOrderFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.createOrderEnglish));
        }

        //topping indexs: 7-12

        //Setting text of items
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 7; i < 13; i++) {
            temp.add(Language.get(i));
        }

        //adding each topping to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toppingOneSpinner.setAdapter(adapter);
        toppingTwoSpinner.setAdapter(adapter);
        toppingThreeSpinner.setAdapter(adapter);

        cruddyPizzaHeaderTextView.setText(Language.get(0));
        newOrderTextView.setText(Language.get(1));
        sizeTextView.setText(Language.get(2));
        smallRadioBtn.setText(Language.get(3));
        mediumRadioBtn.setText(Language.get(4));
        largeRadioBtn.setText(Language.get(5));
        toppingsTextView.setText(Language.get(6));
        yourNameTextView.setText(Language.get(13));
        submitBtn.setText(Language.get(14));
    }


}