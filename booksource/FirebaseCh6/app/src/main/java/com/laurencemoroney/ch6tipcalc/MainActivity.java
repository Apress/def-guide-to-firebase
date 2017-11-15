package com.laurencemoroney.ch6tipcalc;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button10, button15, button20;
    EditText billAmount;
    TextView tipAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcTip(10);
            }
        });
        button15 = (Button) findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcTip(15);
            }
        });
        button20 = (Button) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcTip(20);
            }
        });
        tipAmount = (TextView) findViewById(R.id.tipAmount);
        billAmount = (EditText) findViewById(R.id.billAmount);

    }

    private void calcTip(int tipPercent){
        String amount = billAmount.getText().toString();
        if(amount.isEmpty()){

        } else {
            float bill = Float.parseFloat(amount);
            float tip = bill * (tipPercent / 100f);
            tipAmount.setText(Float.toString(tip));
        }
    }
}
