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

public class ChangeOrder extends AppCompatActivity {
    Spinner toppingOneSpinner, toppingTwoSpinner, toppingThreeSpinner;
    RadioGroup sizeRadioGroup;
    RadioButton smallRadioBtn, mediumRadioBtn, largeRadioBtn;
    TextView newOrderTextView, cruddyPizzaHeaderTextView, sizeTextView, toppingsTextView, yourNameTextView;
    EditText nameEditText;
    Button submitBtn;
    int size = 0;
    int orderID;

    SharedPreferences prefs;

    List<String> Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        //get the order id from the intent
        Intent intent = getIntent();
        orderID = intent.getIntExtra("ORDERNUM", 0);

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

        //open the database and get the order info if the order id is not 0
        if (orderID != 0) {
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor c = db.getRecord(orderID);
            if (c.moveToFirst()) {
                do {
                    //get the order name
                    String name = c.getString(1);
                    nameEditText.setText(name);

                    //get the order size
                    size = Integer.parseInt(c.getString(2));
                    //set the radio button to the correct size
                    if (size == 1) {
                        smallRadioBtn.setChecked(true);
                    } else if (size == 2) {
                        mediumRadioBtn.setChecked(true);
                    } else if (size == 3) {
                        largeRadioBtn.setChecked(true);
                    }

                    //for each item in the spinner
                    toppingOneSpinner.setSelection(Integer.parseInt(c.getString(3)));
                    toppingTwoSpinner.setSelection(Integer.parseInt(c.getString(4)));
                    toppingThreeSpinner.setSelection(Integer.parseInt(c.getString(5)));

                } while (c.moveToNext());
            }
            db.close();
        }



    }

    View.OnClickListener sizeEntry = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int idOfSize = sizeRadioGroup.getCheckedRadioButtonId();

            //switch statement to determine which size was selected
            switch (idOfSize) {
                case R.id.smallRadioBtn:
                    size = 1;
                    break;
                case R.id.mediumRadioBtn:
                    size = 2;
                    break;
                case R.id.largeRadioBtn:
                    size = 3;
                    break;
            }
        }
    };

    View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Objects.equals(size, "") || nameEditText.getText().toString().equals(""))
            {
                return;
            }
            //update an order in the database if the order id is not 0
            if (orderID != 0)
            {
                DBAdapter db = new DBAdapter(ChangeOrder.this);
                db.open();
                //get the current date and time
                String date = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());

                db.updateOrder(orderID, nameEditText.getText().toString(), size, toppingOneSpinner.getSelectedItemPosition(), toppingTwoSpinner.getSelectedItemPosition(), toppingThreeSpinner.getSelectedItemPosition(), date);
                db.close();
            }

            //Redirect to home
            Intent i = new Intent(ChangeOrder.this, MainActivity.class);
            //Toast pop up
            Toast toast = Toast.makeText(getApplicationContext(), "Order changed!", Toast.LENGTH_LONG);
            toast.show();
            startActivity(i);
        }
    };

    private void changeLanguage()
    {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.changeOrderFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.changeOrderEnglish));
        }

        //Toppings indexes: 7-12
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

        //Setting text of items
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