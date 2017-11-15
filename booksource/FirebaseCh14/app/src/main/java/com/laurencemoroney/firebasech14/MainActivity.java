package com.laurencemoroney.firebasech14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    Button redButton, blueButton, favoriteButton;
    Spinner colorSpinner;
    FirebaseAnalytics mAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redButton = (Button) findViewById(R.id.redButton);
        blueButton = (Button) findViewById(R.id.blueButton);
        favoriteButton = (Button) findViewById(R.id.favoriteButton);
        colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        mAnalytics = FirebaseAnalytics.getInstance(this);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle params = new Bundle();
                params.putString("color", "red");
                mAnalytics.logEvent("color_pressed", params);
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle params = new Bundle();
                params.putString("color", "blue");
                mAnalytics.logEvent("color_pressed", params);
                mAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_UP, params);
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnalytics.setUserProperty("favorite_color", colorSpinner.getSelectedItem().toString());
            }
        });

    }
}
