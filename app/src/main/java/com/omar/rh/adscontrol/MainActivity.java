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




        new adscontroller.config(this)
                .statut(1)
                .admob_app_id("ca-app-pub-3940256099942544")
                .admob_inter("ca-app-pub-3940256099942544/1033173712")
                .admob_banner("ca-app-pub-3940256099942544/6300978111")
                .admob_native("ca-app-pub-3940256099942544/2247696110")
                .fb_inter("IMG_16_9_APP_INSTALL#760988004308874_760988490975492")
                .Fb_banner("IMG_16_9_APP_INSTALL#760988004308874_760990247641983")
                .fb_native("IMG_16_9_APP_INSTALL#760988004308874_803370760070598")
                .Unity_app_id("3680887")
                .unity_inter("video")
                .housead_banner("https://i.ibb.co/P9tGZND/manebnr1.gif")
                .housead_inter("https://i.ibb.co/7rsCZRp/bdint.gif")
                .housead_inter_link("https://i.ibb.co/7rsCZRp/bdint.gif")
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
                Toast.makeText(MainActivity.this, "good for you", Toast.LENGTH_SHORT).show();
                ads.showNative();
                ads.showBanners();
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
