package com.example.udac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        EditText Name= (EditText) findViewById(R.id.Name);
        String name= String.valueOf(Name.getText());

        CheckBox WhippedCream= (CheckBox)findViewById(R.id.WhippedCreamCheck);
        boolean hasWhippedCream= WhippedCream.isChecked();

        CheckBox Choclate= (CheckBox)findViewById(R.id.ChoclateCheck);
        boolean hasChoclate= Choclate.isChecked();

        int price=calculatePrice(hasWhippedCream,hasChoclate);
        String pricemes=createorderSummary(price,hasWhippedCream,hasChoclate,name);


            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee for " +name);
            intent.putExtra(Intent.EXTRA_TEXT, pricemes );

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


    protected void display(int n){
        TextView quantityTextView= (TextView) findViewById(R.id.quantitytextView);
        quantityTextView.setText(""+n);
    }
    protected void displayPrice(String n){
        TextView priceTextView= (TextView) findViewById(R.id.pricetextView);
        priceTextView.setText("$ "+n);

    }

    public void increment(View view) {
        quantity = quantity+1;
        if(quantity>100){quantity=100;}
        display(quantity);

    }

    public void decrement(View view) {
        quantity = quantity-1;
        if(quantity<0){quantity=0;}
        display(quantity);
    }
    public int calculatePrice(boolean hasWc,boolean hasChoclate)
    {
        int basePrice=5;
        if(hasWc){basePrice= basePrice+ 1;}
        if(hasChoclate){basePrice= basePrice+2;}
        return quantity*basePrice;
    }
    public String createorderSummary(int price,boolean WhippedCreamStatus,boolean ChoclateStatus,String name)
    {


        String pricemes= "Name: "+ name;
        pricemes= pricemes+"\nAdd Whipped Cream? " + WhippedCreamStatus;
        pricemes= pricemes+"\nAdd Choclate? " + ChoclateStatus;
        pricemes= pricemes+"\nQuantity: "+ quantity +"\nTotal: $" + price;
        pricemes = pricemes + "" + "\nThank You!";
        return pricemes;
    }


}
