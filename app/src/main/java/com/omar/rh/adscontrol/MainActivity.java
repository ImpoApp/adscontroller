package com.omar.rh.adscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
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




        new adscontroller.setAds(this)
                .Statut("3")
                .Unity_app_id("3680887")
                .Unity_inter("video")
                .Admob_inter("ca-app-pub-3940256099942544/1033173712")
                .Admob_banner("ca-app-pub-3940256099942544/6300978111")
                .Housead_banner("https://i.ibb.co/P9tGZND/manebnr1.gif")
                .Housead_inter("https://i.ibb.co/7rsCZRp/bdint.gif")
                .Housead_inter_link("https://i.ibb.co/7rsCZRp/bdint.gif")
                .Housead_banner_link("https://google.com");

        ads.initialize();
        ads.Call_New_Insertial();
        ads.showBanners();
    }

    public void clickme(View view) {
        ads.callBack(new adscontroller.adsCallback() {
            @Override
            public void adscall() {
//                Toast.makeText(MainActivity.this, "good for you", Toast.LENGTH_SHORT).show();
                ads.showBanners("1");
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
