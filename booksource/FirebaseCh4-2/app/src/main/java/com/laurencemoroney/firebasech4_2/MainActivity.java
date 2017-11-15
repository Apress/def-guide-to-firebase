package com.laurencemoroney.firebasech4_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button LoginButton, GetPictureButton, GetMetadataButton;
    TextView ShowPathTextView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSignIn();
            }
        });
        GetPictureButton = (Button) findViewById(R.id.GetPictureButton);
        GetPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPicture();

            }
        });
        GetMetadataButton = (Button) findViewById(R.id.GetMetadataButton);
        GetMetadataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMetadata();
            }
        });
        ShowPathTextView = (TextView) findViewById(R.id.ShowPath);
    }
    private void doSignIn() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                GetPictureButton.setEnabled(true);
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String foo = e.getLocalizedMessage();
            }
        });

    }

    private void getMetadata(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("image.jpg");
        imageRef.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
            @Override
            public void onSuccess(StorageMetadata storageMetadata) {
                String url = storageMetadata.getDownloadUrl().toString();
                ShowPathTextView.setText(url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String foo = e.getLocalizedMessage();
            }
        });
    }
    private void downloadPicture(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("image.jpg");
        try {
            final File localFile = File.createTempFile("image", "jpg");
            imageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local file has been created, load it into ImageView
                    Bitmap myBitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(R.id.ShowPicture);
                    myImage.setImageBitmap(myBitmap);
                    GetMetadataButton.setEnabled(true);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String foo = e.getLocalizedMessage();
                }
            });
        } catch (Exception ex){
            String foo = ex.getLocalizedMessage();
        }
    }

}
