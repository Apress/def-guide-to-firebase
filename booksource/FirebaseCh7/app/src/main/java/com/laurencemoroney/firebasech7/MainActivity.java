package com.laurencemoroney.firebasech7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        Button crashButton = (Button) findViewById(R.id.crashButton);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = 1;
                int y = 0;
                int z = x/y;
            }
        });
    }
}
