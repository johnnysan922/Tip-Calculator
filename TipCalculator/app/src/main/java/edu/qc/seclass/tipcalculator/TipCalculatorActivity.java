package edu.qc.seclass.tipcalculator;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {

    double checkAmount;
    int partySize;

    EditText checkAmountValue;
    EditText partySizeValue;

    Button buttonCompute;

    EditText percent15Tip;
    EditText percent20Tip;
    EditText percent25Tip;

    EditText percent15Total;
    EditText percent20Total;
    EditText percent25Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization of identifiers to the declared objects
        checkAmountValue = findViewById(R.id.inputCheckValue);
        partySizeValue = findViewById(R.id.partySizeValue);

        buttonCompute = findViewById(R.id.buttonCompute);
        buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calls method to close keyboard upon button press
                closeKeyboard();

                //throws a toast message if the check amount is empty
                if(checkAmountValue.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a check amount", Toast.LENGTH_LONG).show();
                }

                //throws a toast message if the party size is empty
                else if (partySizeValue.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(), "Please enter a party size", Toast.LENGTH_LONG).show();
                }

                //throws a toast message if the party size is 0
                else if(Integer.valueOf(partySizeValue.getText().toString()) == 0) {
                    Toast.makeText(getApplicationContext(), "Cannot divide by 0 party size", Toast.LENGTH_LONG).show();
                }

                //otherwise starts computing tips and total
                else{
                    //inititalizes all editText Id's to corresponding objects
                    percent15Tip = findViewById(R.id.fifteenPercentTipValue);
                    percent20Tip = findViewById(R.id.twentyPercentTipValue);
                    percent25Tip = findViewById(R.id.twentyfivePercentTipValue);

                    percent15Total = findViewById(R.id.fifteenPercentTotalVal);
                    percent20Total = findViewById(R.id.twentyPercentTotalValue);
                    percent25Total = findViewById(R.id.twentyfivePercentTotalValue);

                    checkAmount = Double.valueOf(checkAmountValue.getText().toString());
                    partySize = Integer.valueOf((partySizeValue.getText().toString()));

                    percent15Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.15)));
                    percent20Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.20)));
                    percent25Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.25)));

                    percent15Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.15)));
                    percent20Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.20)));
                    percent25Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.25)));
                }
            }
        });
    }

    //method that calculates the tip
    public int calculateTip(double checkAmount, int partySize, double percent){
        int total = (int) Math.ceil((checkAmount / partySize) * percent);
        return  total;
    }

    //method that calculates the total including tip
    public int calculateTotal(double checkAmount, int partySize, double percent){
        double tip = ((checkAmount / partySize) * percent);
        int total = (int) Math.ceil((checkAmount / partySize) + tip);
        return  total;
    }

    //method to close the keyboard upon call
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}