package com.laurencemoroney.chapter10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent intent = getIntent();
        String action = intent.getAction();
        String data = intent.getDataString();
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(data);

    }
}
