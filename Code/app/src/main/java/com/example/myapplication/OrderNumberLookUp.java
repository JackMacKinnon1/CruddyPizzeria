package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderNumberLookUp extends AppCompatActivity {

    EditText orderNumberEditText;
    Button orderNumberSubmitBtn;
    TextView orderNumberLookUpTextView, cruddyPizzaHeaderTextView;
    Bundle extras;

    Intent i;
    int numLookup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_number_look_up);

        //Connecting views
        orderNumberEditText = findViewById(R.id.orderNumberEditText);
        orderNumberSubmitBtn = findViewById(R.id.orderNumberSubmitBtn);
        orderNumberLookUpTextView = findViewById(R.id.orderNumberLookUpTextView);
        cruddyPizzaHeaderTextView = findViewById(R.id.cruddyPizzaHeaderTextView);

        orderNumberSubmitBtn.setOnClickListener(submitNumberLookUp);

        extras = getIntent().getExtras();

        if (extras != null)
        {
            switch(extras.getString("ACTION")) {
                case "VIEW":
                    i = new Intent(OrderNumberLookUp.this, ViewSpecificOrder.class);
                    break;
                case "DELETE":

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

}