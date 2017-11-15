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

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class ShareableActivity extends AppCompatActivity {

    TextView shareTextView;
    private static final int REQUEST_INVITE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shareable);
        shareTextView = (TextView) findViewById(R.id.shareTextView);
        Button shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = getDynamicLink(Uri.parse("http://laurencemoroney.com/?shareactivity=1234"),0);
                Task<ShortDynamicLink> task = FirebaseDynamicLinks.getInstance()
                        .createDynamicLink()
                        .setLongLink(uri)
                        .buildShortDynamicLink()
                        .addOnCompleteListener(new OnCompleteListener<ShortDynamicLink>() {
                            @Override
                            public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                                if (task.isSuccessful()) {
                                    Uri shortLink = task.getResult().getShortLink();
                                    shareTextView.setText(shortLink.toString());
                                    Log.d("CH12", shortLink.toString());
                                } else {
                                    shareTextView.setText("Error retrieving link");
                                }
                            }
                        });
            }
        });

        Button inviteFriendsButton = (Button) findViewById(R.id.inviteButton);
        inviteFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new AppInviteInvitation.IntentBuilder("Try out my cool app!")
                        .setMessage("Hey, this app is really cool. I thought you might like it!")
                        .setDeepLink(Uri.parse("http://laurencemoroney.com/?shareactivity=1234"))
                        .setCallToActionText("Let's do this!")
                        .build();
                startActivityForResult(intent, REQUEST_INVITE);
            }
        });

      }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_INVITE){
            if(resultCode == RESULT_OK){
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for(String id : ids){
                    Log.d("CH12", "Sent invitation to: " + id);
                }
            } else {
                Log.d("CH12", "Invitation failed");
            }
        }
    }

    private Uri getDynamicLink(@NonNull Uri destinationLink, int minVersion){
        String linkdomain = "jpv99.app.goo.gl";
        DynamicLink.Builder builder = FirebaseDynamicLinks.getInstance()
                .createDynamicLink()
                .setDynamicLinkDomain(linkdomain)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder()
                    .setMinimumVersion(minVersion)
                    .build())
                .setLink(destinationLink);
        DynamicLink link = builder.buildDynamicLink();


        return link.getUri();
    }
}
