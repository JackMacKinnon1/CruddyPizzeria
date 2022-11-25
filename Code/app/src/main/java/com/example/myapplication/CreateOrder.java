package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateOrder extends AppCompatActivity {

    CheckBox cheeseCheckBox, pepperoniCheckBox, sausageCheckBox, pineappleCheckBox, baconCheckBox, greenPeppersCheckBox;
    RadioGroup sizeRadioGroup;
    RadioButton smallRadioBtn, mediumRadioBtn, largeRadioBtn;
    EditText nameEditText;
    Button submitBtn;
    ArrayList<String> Toppings;
    String size = "";
    TextView newOrderTextView, cruddyPizzaHeaderTextView, sizeTextView, toppingsTextView, yourNameTextView;

    SharedPreferences prefs;

    List<String> Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);



        Toppings = new ArrayList<>();


        cheeseCheckBox = findViewById(R.id.cheeseCheckBox);
        pepperoniCheckBox = findViewById(R.id.pepperoniCheckBox);
        sausageCheckBox = findViewById(R.id.sausageCheckBox);
        pineappleCheckBox = findViewById(R.id.pineappleCheckBox);
        baconCheckBox = findViewById(R.id.baconCheckBox);
        greenPeppersCheckBox = findViewById(R.id.greenPeppersCheckBox);
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


        cheeseCheckBox.setOnClickListener(addTopping);
        pepperoniCheckBox.setOnClickListener(addTopping);
        sausageCheckBox.setOnClickListener(addTopping);
        pineappleCheckBox.setOnClickListener(addTopping);
        baconCheckBox.setOnClickListener(addTopping);
        greenPeppersCheckBox.setOnClickListener(addTopping);
        submitBtn.setOnClickListener(submit);
        smallRadioBtn.setOnClickListener(sizeEntry);
        mediumRadioBtn.setOnClickListener(sizeEntry);
        largeRadioBtn.setOnClickListener(sizeEntry);

        changeLanguage();


    }

    View.OnClickListener addTopping = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CheckBox temp = (CheckBox) v;

            if (!temp.isChecked())
            {
                Toppings.remove(temp.getText().toString());
            }
            else if(Toppings.size() == 3) {
                temp.setChecked(false);
            }
            else
            {
                Toppings.add(temp.getText().toString());
            }
        }
    };

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
            //Going to do pack it up into a database and submit it

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

        //Setting text of items
        cruddyPizzaHeaderTextView.setText(Language.get(0));
        newOrderTextView.setText(Language.get(1));
        sizeTextView.setText(Language.get(2));
        smallRadioBtn.setText(Language.get(3));
        mediumRadioBtn.setText(Language.get(4));
        largeRadioBtn.setText(Language.get(5));
        toppingsTextView.setText(Language.get(6));
        cheeseCheckBox.setText(Language.get(7));
        pepperoniCheckBox.setText(Language.get(8));
        sausageCheckBox.setText(Language.get(9));
        pineappleCheckBox.setText(Language.get(10));
        baconCheckBox.setText(Language.get(11));
        greenPeppersCheckBox.setText(Language.get(12));
        yourNameTextView.setText(Language.get(13));
        submitBtn.setText(Language.get(14));
    }


}