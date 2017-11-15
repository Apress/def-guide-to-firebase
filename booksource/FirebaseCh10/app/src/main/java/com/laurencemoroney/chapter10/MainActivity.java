package com.laurencemoroney.chapter10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.builders.Indexables;

public class MainActivity extends AppCompatActivity {

    EditText titleText, noteText;
    Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText = (EditText) findViewById(R.id.titleText);
        noteText = (EditText) findViewById(R.id.noteText);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Indexable noteToIndex = Indexables.noteDigitalDocumentBuilder()
                        .setName(titleText.getText().toString())
                        .setText(noteText.getText().toString())
                        .setUrl("http://laurencemoroney.com/notes/" + System.currentTimeMillis())
                        .build();
                Task<Void> task = FirebaseAppIndex.getInstance().update(noteToIndex);
                task.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Ch10", "Successfully added note to index");
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Ch10", "Failed to add note. Reason: " + e.getMessage());
                    }
                });

            }
        });

    }

}
