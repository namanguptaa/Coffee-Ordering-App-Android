package com.example.justjavathings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2, toppings = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //  Edit text object and input
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
        //  displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java ordering app");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // This method is called when the plus button is clicked.
    public void increment(View view) {

        if (quantity == 100) {

            Toast.makeText(MainActivity.this, "Aah, you exceeded the capacity to order the coffee",
                    Toast.LENGTH_LONG).show();
            display(100);
        } else // or use return; in if() statement
            quantity = quantity + 1;
        display(quantity);
    }

    // This method is called when the order button is clicked.
    public void decrement(View view) {

        if (quantity == 1) {
            Toast.makeText(MainActivity.this, "You cannot order less than 1 cups!",
                    Toast.LENGTH_LONG).show();
            display(1);
        } else // or use return; in if() statement
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
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @param *price          of the order
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