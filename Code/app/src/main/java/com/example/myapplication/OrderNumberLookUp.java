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

public class OrderNumberLookUp extends AppCompatActivity {

    EditText orderNumberEditText;
    Button orderNumberSubmitBtn;
    TextView orderNumberLookUpTextView, cruddyPizzaHeaderTextView;
    Bundle extras;


    SharedPreferences prefs;
    Intent i;
    int numLookup;
    List<String> Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_number_look_up);


        //Connecting views
        orderNumberEditText = findViewById(R.id.orderNumberEditText);
        orderNumberSubmitBtn = findViewById(R.id.orderNumberSubmitBtn);
        orderNumberLookUpTextView = findViewById(R.id.orderNumberLookUpTextView);
        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);

        changeLanguage();


        orderNumberSubmitBtn.setOnClickListener(submitNumberLookUp);

        extras = getIntent().getExtras();

        if (extras != null)
        {
            switch(extras.getString("ACTION")) {
                case "VIEW":
                    i = new Intent(OrderNumberLookUp.this, ViewSpecificOrder.class);
                    break;
                case "EDIT":
                    i = new Intent(OrderNumberLookUp.this, ChangeOrder.class);
                    break;
                default:
                    i = new Intent(OrderNumberLookUp.this, MainActivity.class);
                    break;
            }

        }


    }

    View.OnClickListener submitNumberLookUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (orderNumberEditText.getText().toString().equals(""))
            {
                return;
            }

            numLookup = Integer.parseInt(orderNumberEditText.getText().toString());

            i.putExtra("ORDERNUM", numLookup);
            startActivity(i);
        }
    };

    private void changeLanguage()
    {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.orderNumLookUpFrench));
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.orderNumLookUpEnglish));
        }

        //Setting the text of the views to be the language pulled in
        cruddyPizzaHeaderTextView.setText(Language.get(0));
        orderNumberLookUpTextView.setText(Language.get(1));
        orderNumberSubmitBtn.setText(Language.get(2));
    }



}