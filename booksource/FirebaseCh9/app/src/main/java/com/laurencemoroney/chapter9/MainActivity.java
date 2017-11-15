package com.laurencemoroney.chapter9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button londonButton, tokyoButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        londonButton = (Button) findViewById(R.id.londonButton);
        londonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("London");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Tokyo");
            }
        });
        tokyoButton = (Button) findViewById(R.id.tokyoButton);
        tokyoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Tokyo");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("London");
            }
        });
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap();
                params.put("regid", FirebaseInstanceId.getInstance().getToken());
                new CallFCM().execute(params);
            }
        });
    }
}
