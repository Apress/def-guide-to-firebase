package com.laurencemoroney.firebasech13;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private AdView myAd;
    private Button destinationButton, videoAdButton;
    private InterstitialAd myInterstitial;
    private RewardedVideoAd myVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myInterstitial = new InterstitialAd(this);
        myInterstitial.setAdUnitId("ca-app-pub-1282004902910335/8065757471");
        myInterstitial.loadAd(new AdRequest.Builder().build());

        myVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        myVideoAd.loadAd("ca-app-pub-1282004902910335/8524922474", new AdRequest.Builder().build());
        myVideoAd.setRewardedVideoAdListener(this);




        myAd = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("[Device ID]")
                .build();
        myAd.loadAd(adRequest);
        destinationButton = findViewById(R.id.destinationButton);
        destinationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myInterstitial.isLoaded()) {
                    myInterstitial.show();
                } else {
                    Log.d("Ch13", "The interstitial wasn't loaded yet.");
                }

            }
        });

        myInterstitial.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                loadDestination();
            }
        });

        videoAdButton = (Button) findViewById(R.id.videoButton);
        videoAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myVideoAd.isLoaded()) {
                    myVideoAd.show();
                }
            }
        });
    }
    private void loadDestination(){
        Intent intent = new Intent(getBaseContext(), DestinationActivity.class);
        startActivity(intent);
    }



    @Override
    public void onRewardedVideoAdLoaded() {
        Log.d("Ch13", "Video Ad Loaded");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        Log.d("Ch13", "Video Ad Failed to Load");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d("Ch13", "Video Started");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d("Ch13", "Video Ad Opened");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d("Ch13", "Video Closed");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.d("Ch13", "You left the app");
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Log.d("CH13", "On Rewarded: Type:" + reward.getType() + " Amount: " + reward.getAmount());
    }
}
