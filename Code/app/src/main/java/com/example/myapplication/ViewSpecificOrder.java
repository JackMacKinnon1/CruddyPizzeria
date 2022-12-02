package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.*;
import android.database.*;
import com.example.myapplication.DBAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

public class ViewSpecificOrder extends AppCompatActivity {

    TextView cruddyPizzaHeaderTextView, orderNumberTextView, customerNameTextView, sizeTextView, oneOrderToppingsTextView,
        orderPlacedDateTextView, customerNameEditText, sizeEditText, orderPlacedDateEditText;

    CheckBox toppingOneCheckBox, toppingTwoCheckBox, toppingThreeCheckBox;

    ArrayList<String> toppingsList = new ArrayList<String>();
    ArrayList<String> sizeList = new ArrayList<String>();

    SharedPreferences prefs;

    List<String> Language;
    List<String> toppings;



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
        orderPlacedDateEditText = findViewById(R.id.orderPlacedDateEditText);

        changeLanguage();
        getToppings();

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
                    sizeEditText.setText(sizeList.get(c.getInt(2) - 1));
                    toppingOneCheckBox.setText(toppingsList.get(c.getInt(3)));
                    toppingTwoCheckBox.setText(toppingsList.get(c.getInt(4)));
                    toppingThreeCheckBox.setText(toppingsList.get(c.getInt(5)));
                    //check the boxes for the toppings
                    toppingOneCheckBox.setChecked(true);
                    toppingTwoCheckBox.setChecked(true);
                    toppingThreeCheckBox.setChecked(true);
                    orderPlacedDateEditText.setText(c.getString(6));


                } while (c.moveToNext());
            }

            db.close();


        }



    }

    private void changeLanguage() {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.viewSpecificOrderFrench));
            toppings = Arrays.asList(getResources().getStringArray(R.array.createOrderFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.viewSpecificOrderEnglish));
            toppings = Arrays.asList(getResources().getStringArray(R.array.createOrderEnglish));
        }

        //Setting the text on the views
        cruddyPizzaHeaderTextView.setText(Language.get(0));
        orderNumberTextView.setText(Language.get(1));
        customerNameTextView.setText(Language.get(2));
        sizeTextView.setText(Language.get(3));
        oneOrderToppingsTextView.setText(Language.get(4));
        orderPlacedDateTextView.setText(Language.get(5));
    }

    private void getToppings() {
        sizeList.add(toppings.get(3));
        sizeList.add(toppings.get(4));
        sizeList.add(toppings.get(5));


        for (int i = 7; i < 13; i++) {
            toppingsList.add(toppings.get(i));
        }
    }

}