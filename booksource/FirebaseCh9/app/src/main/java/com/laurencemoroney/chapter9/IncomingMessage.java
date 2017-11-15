package com.laurencemoroney.chapter9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IncomingMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_message);
        Bundle bundle = getIntent().getExtras();
        String notificationText = bundle.getString("Notification");
        TextView txtNotification = (TextView) findViewById(R.id.txtNotification);
        txtNotification.setText(notificationText);
    }
}
