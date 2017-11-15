package com.laurencemoroney.firebasech12_dl;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appinvite.FirebaseAppInvite;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView contentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentText = (TextView) findViewById(R.id.contentText);
        Button gotoShareButton = findViewById(R.id.gotoShareButton);
        gotoShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShareableActivity.class);
                startActivity(intent);
            }
        });
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }

                        if (deepLink != null) {
                            Set<String> params = deepLink.getQueryParameterNames();
                            if(params.contains("shareactivity")){
                                Intent intent = new Intent(getApplicationContext() , ShareableActivity.class);
                                startActivity(intent);
                            }
                            contentText.setText(deepLink.toString());
                        } else {
                            Log.d("CH12-DL", "getDynamicLink: no link found");
                        }

                        FirebaseAppInvite invite = FirebaseAppInvite.getInvitation(pendingDynamicLinkData);
                        if (invite != null) {
                            String invitationId = invite.getInvitationId();
                        }
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("CH12-DL", "getDynamicLink:onFailure", e);
                    }
                });
    }

}
