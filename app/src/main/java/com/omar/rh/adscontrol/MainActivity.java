package com.omar.rh.adscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omar.rh.ads.adscontroller;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    adscontroller ads = new adscontroller(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        new adscontroller.config()
                .statut(2)
                .admob_app_id("ca-app-pub-3940256099942544")
                .admob_inter("ca-app-pub-3940256099942544/1033173712")
                .admob_banner("ca-app-pub-3940256099942544/6300978111")
                .admob_native("ca-app-pub-3940256099942544/2247696110")
                .fb_inter("")
                .Fb_banner("")
                .fb_native("")
                .Unity_app_id("")
                .unity_inter("")
                .housead_banner("")
                .housead_inter("")
                .housead_inter_link("https://google.com")
                .Housead_banner_link("https://google.com");

        ads.init();
        ads.showInter(4);
        ads.showBanners();
        ads.showNative();
    }

    public void clickme(View view) {
        ads.callBack(3,new adscontroller.adsCallback() {
            @Override
            public void adscall() {
                 //TODO function to show after ads showed or closed
                ads.showNative(1);
                ads.showBanners(1);
            }
        });
    }

    @Override
    public void onBackPressed() {
        ads.onBakcoverride(new adscontroller.onBakcoverride() {
            @Override
            public void callBack() {
                // TODO: --> you can replace the below code with your own code
                // Replace ClassName with this class name
                MainActivity.super.onBackPressed();
            }
        });
    }
}
