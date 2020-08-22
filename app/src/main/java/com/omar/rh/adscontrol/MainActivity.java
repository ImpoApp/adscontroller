package com.omar.rh.adscontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.omar.rh.ads.adscontroller;

public class MainActivity extends AppCompatActivity {
    adscontroller ads = new adscontroller(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        new adscontroller.setAds(this)
                .Admob_app_id("koskos kabaylos")
                .Admob_banner("sss")
                .Admob_inter("ca-app-pub-3940256099942544/1033173712")
                .Fb_banner("sdsds")
                .Statut("1")
                .Housead_banner("sdsd");
        ads.initialize();
        ads.Call_New_Insertial();

    }

    public void clickme(View view) {
        ads.callBack(new adscontroller.adsCallback() {
            @Override
            public void adscall() {
                Toast.makeText(MainActivity.this, "deeem", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
