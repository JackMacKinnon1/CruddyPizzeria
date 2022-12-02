package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.*;
import android.database.*;
import com.example.myapplication.DBAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    Button placeOrderBtn, viewOrderBtn, changeOrderBtn, cancelOrderBtn, viewAllOrdersBtn;

    TextView cruddyPizzaHeaderTextView, orderNumberLoopUpTextView, languageTextView;

    SharedPreferences prefs;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch languageSwitch;

    List<String> Language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Open or create database
        try {
            String destPath = "/data/data/" + getPackageName() + "/database/CruddyPizza";

            File f = new File(destPath);

            if (!f.exists()) {
                CopyDB(getBaseContext().getAssets().open("CruddyPizza"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //check if there is a order id in the intent
        Intent intent = getIntent();
        int orderID = intent.getIntExtra("ORDERNUM", 0);

        if (orderID != 0)
        {
            DBAdapter db = new DBAdapter(this);
            db.open();
            db.deleteRecord(orderID);
            db.close();

            Toast.makeText(this, "Order " + orderID + " has been deleted", Toast.LENGTH_LONG).show();
        }





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

        //Setting the language
        changeLanguage();


        languageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LANG_VALUE = "";

                if (Objects.equals(prefs.getString("LANGUAGE", ""), "FRENCH"))
                {
                    LANG_VALUE = "ENGLISH";
                }
                else
                {
                    LANG_VALUE = "FRENCH";
                }

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("LANGUAGE", LANG_VALUE);
                editor.apply();

                changeLanguage();

            }
        });



    }

    public void CopyDB (InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void DisplayContact(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Size: " + c.getString(2) + "\n" +
                        "Toppings: " + c.getString(3) + "\n" +
                        "Toppings: " + c.getString(4) + "\n" +
                        "Toppings: " + c.getString(5) + "\n",
                Toast.LENGTH_LONG).show();
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

    private void changeLanguage()
    {
        prefs = getSharedPreferences("LanguageValue", MODE_PRIVATE);
        if ("FRENCH".equals(prefs.getString("LANGUAGE", ""))) {
            Language = Arrays.asList(getResources().getStringArray(R.array.mainPageFrench));
            languageSwitch.setChecked(true);
        } else {
            Language = Arrays.asList(getResources().getStringArray(R.array.mainPageEnglish));
        }

        //Setting the text of the views
        cruddyPizzaHeaderTextView.setText(Language.get(0));
        orderNumberLoopUpTextView.setText(Language.get(1));
        placeOrderBtn.setText(Language.get(2));
        viewOrderBtn.setText(Language.get(3));
        changeOrderBtn.setText(Language.get(4));
        cancelOrderBtn.setText(Language.get(5));
        viewAllOrdersBtn.setText(Language.get(6));
        languageTextView.setText(Language.get(7));
        languageSwitch.setText(Language.get(8));
    }



}