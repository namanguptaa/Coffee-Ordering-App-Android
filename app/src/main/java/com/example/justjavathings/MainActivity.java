package com.example.justjavathings;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0, toppings = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Edit text object and input
        EditText nameEditText = (EditText) findViewById(R.id.name_field);
        String name = nameEditText.getText().toString();
        Log.v("Main Activity", "Name: " + name);

        // Whipped Cream checkbox object & input
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        // chocolate checkbox object and input
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        // log for whipped cream Verbose type
        Log.v("MainActivity", "Has whipped Cream" + hasWhippedCream);

        int orderPrice = calculatePrice(hasWhippedCream, hasChocolate);

        // function call: createOrderSummary(args: name, price, haswhippedcream, haschocolate);
        String priceMessage = createOrderSummary(name, orderPrice, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }
    // This method is called when the plus button is clicked.
    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }
    // This method is called when the order button is clicked.
    public void decrement(View view) {
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * Create summary of the order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate    is whether or not the user wants chocolate topping
     * @param price           of the order
     * @return text summary
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int price = 5;

        if (addWhippedCream) {
            price += 1;
        }
        if (addChocolate) {
            price += 2;
        }

        return price * quantity;
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\n Add Chocolate?" + hasChocolate;
        priceMessage += "\n Add Whipped Cream? " + hasWhippedCream;
        priceMessage += "\n Quantity: " + quantity;
        priceMessage += "\n Total: $" + price;
        priceMessage += "\n Thank you!";
        return priceMessage;
    }
}