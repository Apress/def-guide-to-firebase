package com.laurencemoroney.firebasech11;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.appcompat.*;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    TextView greetingsTextView;
    String greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greetingsTextView = (TextView) findViewById(R.id.greetingsText);
        final FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.defaults);
        greeting = mFirebaseRemoteConfig.getString("GREETINGS_TEXT");
        updateUI();
        mFirebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            mFirebaseRemoteConfig.activateFetched();
                            greeting = mFirebaseRemoteConfig.getString("GREETINGS_TEXT");
                            updateUI();
                        } else {
                            // Task Failed, handle it
                        }
                    }
                });
    }

    private void updateUI(){
        greetingsTextView.setText(greeting);
    }
}
