package com.omar.rh.ads;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import androidx.appcompat.app.AppCompatActivity;

public class adscontroller extends AppCompatActivity{

    Context context;
    Activity activity;
    public InterstitialAd interstitialAd;
    private adsCallback adsCallback;


    public adscontroller(Context context) {
        this.context = context;
    }

    public void initialize() {
        activity = (Activity) context;
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
    }

    public interface adsCallback {
        void adscall();
    }

    public adscontroller callBack(adscontroller.adsCallback listener) {
        adsCallback = listener;
        immediateAds();
        return this;
    }

    public void Call_New_Insertial() {
        interstitialAd.loadAd(new AdRequest.Builder()
                .build());
    }

    private void immediateAds() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    adsCallback.adscall();
                    Call_New_Insertial();
                }
            });
        } else {
            Toast.makeText(context, "hero, ", Toast.LENGTH_SHORT).show();
            adsCallback.adscall();
        }
    }

}
