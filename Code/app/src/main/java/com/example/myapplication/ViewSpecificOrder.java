package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.*;
import android.database.*;
import com.example.myapplication.DBAdapter;

import java.util.Arrays;
import java.util.List;
import java.io.*;

public class ViewSpecificOrder extends AppCompatActivity {

    TextView cruddyPizzaHeaderTextView, orderNumberTextView, customerNameTextView, sizeTextView, oneOrderToppingsTextView,
        orderPlacedDateTextView, customerNameEditText, sizeEditText;

    CheckBox toppingOneCheckBox, toppingTwoCheckBox, toppingThreeCheckBox;

    SharedPreferences prefs;

    List<String> Language;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_order);

        //pull the order number from the intent
        int orderNumber = getIntent().getIntExtra("ORDERNUM", 0);




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
        customerNameEditText = findViewById(R.id.customerNameEditText);
        sizeEditText = findViewById(R.id.sizeEditText);

        changeLanguage();

        //check if the order number is not zero and query the database for the order
        if (orderNumber != 0) {
            //query the database for the order
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor c = db.getRecord(orderNumber);

            //set the text views to the order information
            if (c.moveToFirst()) {
                do {
                    //append the order number to the order text view
                    orderNumberTextView.setText(orderNumberTextView.getText() + c.getString(0));

                    customerNameEditText.setText(c.getString(1));
                    sizeEditText.setText(c.getString(2));
                    toppingOneCheckBox.setText(c.getString(3));
                    toppingTwoCheckBox.setText(c.getString(4));
                    toppingThreeCheckBox.setText(c.getString(5));
                    //check the boxes for the toppings
                    toppingOneCheckBox.setChecked(true);
                    toppingTwoCheckBox.setChecked(true);
                    toppingThreeCheckBox.setChecked(true);


                } while (c.moveToNext());
            }

            db.close();


        }



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