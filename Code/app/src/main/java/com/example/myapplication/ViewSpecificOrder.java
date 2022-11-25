package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class ViewSpecificOrder extends AppCompatActivity {

    TextView cruddyPizzaHeaderTextView, orderNumberTextView, customerNameTextView, sizeTextView, oneOrderToppingsTextView,
        orderPlacedDateTextView;

    CheckBox toppingOneCheckBox, toppingTwoCheckBox, toppingThreeCheckBox;

    SharedPreferences prefs;

    List<String> Language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_order);


        //Connecting views
        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);
        orderNumberTextView = findViewById(R.id.orderNumberTextView);
        customerNameTextView = findViewById(R.id.customerNameTextView);
        sizeTextView = findViewById(R.id.sizeTextView);
        oneOrderToppingsTextView = findViewById(R.id.oneOrderToppingsTextView);
        orderPlacedDateTextView = findViewById(R.id.orderPlacedDateTextView);
        toppingOneCheckBox = findViewById(R.id.toppingOneCheckBox);
        toppingTwoCheckBox = findViewById(R.id.toppingTwoCheckBox);
        toppingThreeCheckBox = findViewById(R.id.toppingThreeCheckBox);

        changeLanguage();



    }

    private void changeLanguage() {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.viewSpecificOrderFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.viewSpecificOrderEnglish));
        }

        //Setting the text on the views
        cruddyPizzaHeaderTextView.setText(Language.get(0));
        orderNumberTextView.setText(Language.get(1));
        customerNameTextView.setText(Language.get(2));
        sizeTextView.setText(Language.get(3));
        oneOrderToppingsTextView.setText(Language.get(4));
        orderPlacedDateTextView.setText(Language.get(5));
    }
}