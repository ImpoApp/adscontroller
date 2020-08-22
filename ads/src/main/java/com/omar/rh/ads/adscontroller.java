package com.omar.rh.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import static com.facebook.ads.AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE;
import static com.omar.rh.ads.config.Admob_app_id;
import static com.omar.rh.ads.config.Admob_banner;
import static com.omar.rh.ads.config.Admob_inter;
import static com.omar.rh.ads.config.Admob_native;
import static com.omar.rh.ads.config.Fb_banner;
import static com.omar.rh.ads.config.Fb_inter;
import static com.omar.rh.ads.config.Fb_native;
import static com.omar.rh.ads.config.Housead_banner;
import static com.omar.rh.ads.config.Housead_banner_link;
import static com.omar.rh.ads.config.Housead_inter_link;
import static com.omar.rh.ads.config.Housead_inter;
import static com.omar.rh.ads.config.Housead_privacy_policy;
import static com.omar.rh.ads.config.Unity_app_id;
import static com.omar.rh.ads.config.Unity_inter;
import static com.omar.rh.ads.config.isFk_inter;
import static com.omar.rh.ads.config.interval;
import static com.omar.rh.ads.config.statut;

public class adscontroller extends AppCompatActivity {

    Context context;
    Activity activity;
    private setAds setAds;

    //Admob
    public InterstitialAd interstitialAd;

    //Facebook
    public com.facebook.ads.InterstitialAd interstitialAdfb;

    // Unity
    private String unityGameID;
    private boolean testMode = false;
    private String unityInter;
    int unityboth = 0;

    // Natives
    FrameLayout nativeAdLayoutAdm;
    ScrollView scrollViewAdm, scrollViewFb;
    private com.facebook.ads.NativeAd nativeAd;
    private NativeAdLayout nativeAdLayout, nativecountainer;
    private LinearLayout adViewNativeFb;

    //banners
    public com.google.android.gms.ads.AdView mAdView2;
    public AdRequest addRequest2;
    public LinearLayout bnlinear, adContainer, separator, mybanners;
    int hasAdView = 0;
    private com.facebook.ads.AdView adView;


    public ImageView fkint, fakebnr, fk_policy_img, add, fkpolicyimgbnr, addbnr;
    public LinearLayout exfkint;
    public RelativeLayout relat;

    public LinearLayout fkinterex, fk_int;
    public RelativeLayout RelativeExFkInt;
    public Boolean expandadd = false, expandaddbnr = false;
    public LinearLayout nativecont;
    private adsCallback adsCallback;

    public adscontroller(Context context) {
        this.context = context;
    }



    public void initialize() {
        activity = (Activity) context;


//        // >> Unity
//        unityGameID = Unity_app_id;
//        unityInter = Unity_inter;
//        final UnityAdsListener myAdsListener = new UnityAdsListener();
//        UnityAds.initialize((Activity) context, unityGameID, myAdsListener, testMode);

        // >> facebook
        AudienceNetworkAds.initialize(context);
        AdSettings.setIntegrationErrorMode(INTEGRATION_ERROR_CRASH_DEBUG_MODE);
        interstitialAdfb = new com.facebook.ads.InterstitialAd(context, Fb_inter);

        //Admob
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(Admob_inter);

    }

    public void fk_int_init() {
        fk_int = this.activity.findViewById(R.id.fk_int);
        View view = View.inflate(context, R.layout.fk_inter, null);
        fk_int.addView(view);

        RelativeExFkInt = this.activity.findViewById(R.id.relativeExFkInt);
        fkinterex = (LinearLayout) this.activity.findViewById(R.id.fkinterex);
        fkint = (ImageView) this.activity.findViewById(R.id.fkint);
        fk_policy_img = (ImageView) this.activity.findViewById(R.id.fkpolicyimg);
        add = this.activity.findViewById(R.id.add);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandadd) {
                    fk_policy_img.setVisibility(View.GONE);
                    expandadd = false;
                } else {
                    fk_policy_img.setVisibility(View.VISIBLE);
                    expandadd = true;
                }
            }
        });

        fk_policy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(Housead_privacy_policy));
                    context.startActivity(viewIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        fkint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(Housead_inter));
                    context.startActivity(viewIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fkinterex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeExFkInt.animate().alpha(0).setDuration(300);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fk_int_callback();
                    }
                }, 300);
            }
        });
    }

    public void fk_int_callback() {
        fk_int.setVisibility(View.GONE);
        RelativeExFkInt.animate().alpha(1);
        isFk_inter = false;
        adsCallback.adscall();
    }

    public void setNative() {

        nativecont = this.activity.findViewById(R.id.nativecont);
        View view = View.inflate(context, R.layout.nativecountainer, null);
        nativecont.addView(view);
        nativeAdLayout = this.activity.findViewById(R.id.native_ad_container);
        scrollViewFb = this.activity.findViewById(R.id.scrollViewFb);
        scrollViewAdm = this.activity.findViewById(R.id.scrollViewAdm);
        nativeAdLayoutAdm = this.activity.findViewById(R.id.id_native_ad);
    }

    public void setBanners() {
        //banners
        mybanners = this.activity.findViewById(R.id.banners);
        View view = View.inflate(context, R.layout.bannercountainer, null);
        mybanners.addView(view);
        adContainer = this.activity.findViewById(R.id.banner_container);
        separator = this.activity.findViewById(R.id.separator);
        bnlinear = this.activity.findViewById(R.id.bnrlinear);

        //        nativeAdLayout = this.activity.findViewById(R.id.native_ad_container);
        //        scrollViewAdm = this.activity.findViewById(R.id.scrollViewAdm);
        //        scrollViewFb = this.activity.findViewById(R.id.scrollViewFb);
        //        nativeAdLayoutAdm = this.activity.findViewById(R.id.id_native_ad);

        // add unit to banners

        // ->Facebook
        adView = new com.facebook.ads.AdView(context, Fb_banner, AdSize.BANNER_HEIGHT_50);

        // ->Admob
        mAdView2 = new com.google.android.gms.ads.AdView(context);
        mAdView2.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
        mAdView2.setAdUnitId(Admob_banner);

        // fkads

        relat = this.activity.findViewById(R.id.relt);
        fakebnr = this.activity.findViewById(R.id.fakebnr);
        fkpolicyimgbnr = this.activity.findViewById(R.id.fkpolicyimgbnr);
        addbnr = this.activity.findViewById(R.id.addbnr);

        fkpolicyimgbnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(Housead_privacy_policy));
                    context.startActivity(viewIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addbnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandaddbnr) {
                    fkpolicyimgbnr.setVisibility(View.GONE);
                    expandaddbnr = false;
                } else {
                    fkpolicyimgbnr.setVisibility(View.VISIBLE);
                    expandaddbnr = true;
                }
            }
        });

        fakebnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(Housead_banner_link));
                    context.startActivity(viewIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void showBanners(String statut) {
        switch (Integer.valueOf(statut)) {
            case 1:
                admBnr();
//                admNativeLoader();
                relat.setVisibility(View.GONE);
                adContainer.setVisibility(View.GONE);
                separator.setVisibility(View.VISIBLE);
//                scrollViewAdm.setVisibility(View.VISIBLE);
//                scrollViewFb.setVisibility(View.GONE);
                break;
            case 2:
                relat.setVisibility(View.GONE);
                adContainer.setVisibility(View.VISIBLE);
                separator.setVisibility(View.VISIBLE);
                bnlinear.setVisibility(View.GONE);
//                scrollViewAdm.setVisibility(View.GONE);
//                scrollViewFb.setVisibility(View.VISIBLE);
                if (hasAdView == 0) {
                    adContainer.removeAllViews();
                    adContainer.addView(adView);
                    adView.loadAd();
                    hasAdView = 1;
                }
                break;
            case 4:
                bnlinear.setVisibility(View.GONE);
                separator.setVisibility(View.VISIBLE);
                adContainer.setVisibility(View.GONE);
                get_fk_bnr();
                break;
            default:
                relat.setVisibility(View.GONE);
                adContainer.setVisibility(View.GONE);
                bnlinear.setVisibility(View.GONE);
                separator.setVisibility(View.GONE);
//                scrollViewFb.setVisibility(View.GONE);
//                scrollViewAdm.setVisibility(View.GONE);
        }
    }

    public interface adsCallback {
        void adscall();
    }

    public adscontroller callBack(adscontroller.adsCallback listener) {
        adsCallback = listener;
        intentStar();
        return this;
    }

    public void Call_New_Insertial() {
        switch (Integer.valueOf(statut)) {
            case 1:
                AdRequest adRequest = new AdRequest.Builder()
                        .build();
                interstitialAd.loadAd(adRequest);
                break;
            case 2:
                try {
                    interstitialAdfb.loadAd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
//            case 3:
//                Log.d("here: loding int unity", unityInter);
//                try {
//                    UnityAds.load(unityInter);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
        }
    }

//    private void UnityInterDisplay() {
//        if (UnityAds.isReady(unityInter)) {
//            UnityAds.show((Activity) context, unityInter);
//        }
//    }

    public void intentStar() {
//        Toast.makeText(context, Admob_app_id, Toast.LENGTH_SHORT).show();
        immediateAds(statut);
    }


    private void immediateAds(final String statut) {
        switch (Integer.valueOf(statut)) {
            case 1:
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                    interstitialAd.setAdListener(new AdListener() {
                        public void onAdClosed() {
                            adsCallback.adscall();
                            Call_New_Insertial();
                        }
                    });
                } else {
                    adsCallback.adscall();
                    Call_New_Insertial();
                }
                break;
            case 2:
                if (interstitialAdfb.isAdLoaded()) {
                    interstitialAdfb.show();
                    interstitialAdfb.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onError(Ad ad, AdError adError) {
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDisplayed(Ad ad) {
                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            adsCallback.adscall();
                            Call_New_Insertial();
                        }
                    });
                } else {
                    adsCallback.adscall();
                    Call_New_Insertial();
                }
                break;
//            case 3:
//                UnityInterDisplay();
//                adsCallback.adscall();
//                break;
            case 4:
                get_fk_int();
                break;
            default:
                adsCallback.adscall();
                Call_New_Insertial();
                interval = 1;
        }
    }

    public void admBnr() {
        bnlinear.setVisibility(View.VISIBLE);
        addRequest2 = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        if (mAdView2.getAdSize() != null || mAdView2.getAdUnitId() != null)
            mAdView2.loadAd(addRequest2);
        // else Log state of adsize/adunit
        ((LinearLayout) this.activity.findViewById(R.id.adlinear)).removeAllViews();
        ((LinearLayout) this.activity.findViewById(R.id.adlinear)).addView(mAdView2);
    }
//
//    //Unity
//    @Override
//    public void onUnityAdsReady(String s) {
//
//    }
//
//    @Override
//    public void onUnityAdsStart(String s) {
//
//    }
//
//    @Override
//    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
//
//    }
//
//    @Override
//    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
//
//    }
//
//    // Implement the IUnityAdsListener interface methods:
//    public class UnityAdsListener implements IUnityAdsListener {
//
//        @Override
//        public void onUnityAdsReady(String placementId) {
//            // Implement functionality for an ad being ready to show.
//        }
//
//        @Override
//        public void onUnityAdsStart(String placementId) {
//            // Implement functionality for a user starting to watch an ad.
//        }
//
//        @Override
//        public void onUnityAdsFinish(String placementId, UnityAds.FinishState finishState) {
//
//        }
//
//        @Override
//        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
//            // Implement functionality for a Unity Ads service error occurring.
//        }
//    }
//    // END UNITY


    public void loadNativeFbAd() {
        scrollViewAdm.setVisibility(View.GONE);
        scrollViewFb.setVisibility(View.VISIBLE);
        // Instantiate a NativeAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        nativeAd = new com.facebook.ads.NativeAd(context, Fb_native);

        nativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e("ads", "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("ads", "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d("ads", "Native ad is loaded and ready to be displayed!");

                // Race condition, load() called again before last ad was displayed
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                // Inflate Native Ad into Container

                nativeAdLayout.removeAllViews();
                inflateAd(nativeAd);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d("ads", "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d("ads", "Native ad impression logged!");
            }
        });
        // Request an ad
        nativeAd.loadAd();
    }

    private void inflateAd(NativeAd nativeAd) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = this.activity.findViewById(R.id.native_ad_container);
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        adViewNativeFb = (LinearLayout) inflater.inflate(R.layout.nativefacebook, nativeAdLayout, false);
        nativeAdLayout.addView(adViewNativeFb);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = this.activity.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        AdIconView nativeAdIcon = adViewNativeFb.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adViewNativeFb.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adViewNativeFb.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adViewNativeFb.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adViewNativeFb.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adViewNativeFb.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adViewNativeFb.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adViewNativeFb,
                nativeAdMedia,
                nativeAdIcon,
                clickableViews);
    }

    public void Show_native(int pos) {
        switch (pos) {
            case 1:
                Admob_nativee_loader();
                break;
            case 2:
                loadNativeFbAd();
                break;
            default:
                scrollViewAdm.setVisibility(View.GONE);
                scrollViewFb.setVisibility(View.GONE);

        }

    }

    public void Admob_nativee_loader() {
        scrollViewFb.setVisibility(View.GONE);
        scrollViewAdm.setVisibility(View.VISIBLE);
        AdLoader adLoader = new AdLoader.Builder(context, Admob_native)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        //the native ad will be available inside this method  (unifiedNativeAd)
                        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        UnifiedNativeAdView unifiedNativeAdView = (UnifiedNativeAdView) inflater.inflate(R.layout.nativeadmob, null);
                        mapUnifiedNativeAdToLayout(unifiedNativeAd, unifiedNativeAdView);


                        nativeAdLayoutAdm.removeAllViews();
                        nativeAdLayoutAdm.addView(unifiedNativeAdView);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                        scrollViewAdm.setVisibility(View.GONE);
                    }
                })
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    public void mapUnifiedNativeAdToLayout(UnifiedNativeAd adFromGoogle, UnifiedNativeAdView myAdView) {
        com.google.android.gms.ads.formats.MediaView mediaView = myAdView.findViewById(R.id.ad_media);
        myAdView.setMediaView(mediaView);

        myAdView.setHeadlineView(myAdView.findViewById(R.id.ad_headline));
        myAdView.setBodyView(myAdView.findViewById(R.id.ad_body));
        myAdView.setCallToActionView(myAdView.findViewById(R.id.ad_call_to_action));
        myAdView.setIconView(myAdView.findViewById(R.id.ad_icon));
        myAdView.setPriceView(myAdView.findViewById(R.id.ad_price));
        myAdView.setStarRatingView(myAdView.findViewById(R.id.ad_rating));
        myAdView.setStoreView(myAdView.findViewById(R.id.ad_store));
        myAdView.setAdvertiserView(myAdView.findViewById(R.id.ad_advertiser));

        ((TextView) myAdView.getHeadlineView()).setText(adFromGoogle.getHeadline());

        if (adFromGoogle.getBody() == null) {
            myAdView.getBodyView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getBodyView()).setText(adFromGoogle.getBody());
        }

        if (adFromGoogle.getCallToAction() == null) {
            myAdView.getCallToActionView().setVisibility(View.GONE);
        } else {
            ((Button) myAdView.getCallToActionView()).setText(adFromGoogle.getCallToAction());
        }

        if (adFromGoogle.getIcon() == null) {
            myAdView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) myAdView.getIconView()).setImageDrawable(adFromGoogle.getIcon().getDrawable());
        }

        if (adFromGoogle.getPrice() == null) {
            myAdView.getPriceView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getPriceView()).setText(adFromGoogle.getPrice());
        }

        if (adFromGoogle.getStarRating() == null) {
            myAdView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) myAdView.getStarRatingView()).setRating(adFromGoogle.getStarRating().floatValue());
        }

        if (adFromGoogle.getStore() == null) {
            myAdView.getStoreView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getStoreView()).setText(adFromGoogle.getStore());
        }

        if (adFromGoogle.getAdvertiser() == null) {
            myAdView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) myAdView.getAdvertiserView()).setText(adFromGoogle.getAdvertiser());
        }

        myAdView.setNativeAd(adFromGoogle);
    }


    //fk ads
    public void get_fk_bnr() {
        relat.setVisibility(View.VISIBLE);
        Glide.with(context).load(Housead_banner).thumbnail(0.01f).into(fakebnr);
//        Glide.with(context).load(Fk_about_img_expand).thumbnail(0.01f).into(fkpolicyimgbnr);
//        Glide.with(context).load(Fk_about_img).thumbnail(0.01f).into(addbnr);
    }

    public void getbnr() {
        bnlinear.setVisibility(View.VISIBLE);
        final com.google.android.gms.ads.AdView mAdView2 = new com.google.android.gms.ads.AdView(this);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
        mAdView2.setAdUnitId(Admob_banner);
        AdRequest addRequest2 = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        if (mAdView2.getAdSize() != null || mAdView2.getAdUnitId() != null)
            mAdView2.loadAd(addRequest2);
        // else Log state of adsize/adunit
        ((LinearLayout) findViewById(R.id.adlinear)).removeAllViews();
        ((LinearLayout) findViewById(R.id.adlinear)).addView(mAdView2);
    }


    public void get_fk_int() {
        isFk_inter = true;
        RelativeExFkInt.setVisibility(View.VISIBLE);
        fk_int.setVisibility(View.VISIBLE);
        Glide.with(context).load(Housead_inter).thumbnail(0.01f).into(fkint);
//        Glide.with(context).load(setting_js.get(0).getFk_about_img_expand()).thumbnail(0.01f).into(fk_policy_img);
//        Glide.with(context).load(setting_js.get(0).getFk_about_img()).thumbnail(0.01f).into(add);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fkinterex.animate().alpha(1).setDuration(500);
            }
        }, 2000);
    }



    public static class setAds {

        private final Context context;
        public setAds(Context context) {
            this.context = context;
        }

        public setAds Admob_app_id(String admob_app_id) {
            Admob_app_id = admob_app_id;
            return this;
        }

        public setAds Admob_inter(String admob_inter) {
            Admob_inter = admob_inter;
            return this;
        }

        public setAds Admob_banner(String admob_banner) {
            Admob_banner = admob_banner;
            return this;
        }

        public setAds Fb_inter(String fb_inter) {
            Fb_inter = fb_inter;
            return this;
        }

        public setAds Fb_banner(String fb_banner) {
            Fb_banner = fb_banner;
            return this;
        }

        public setAds Fb_native(String fb_native) {
            Fb_native = fb_native;
            return this;
        }

        public setAds Unity_app_id(String unity_app_id) {
            Unity_app_id = unity_app_id;
            return this;
        }

        public setAds Unity_inter(String unity_inter) {
            Unity_inter = unity_inter;
            return this;
        }

        public setAds Housead_banner(String housead_banner) {
            Housead_banner = housead_banner;
            return this;
        }

        public setAds Housead_inter(String housead_inter) {
            Housead_inter = housead_inter;
            return this;
        }

        public setAds Housead_banner_link(String housead_banner_link) {
            Housead_banner_link = housead_banner_link;
            return this;
        }

        public setAds housead_inter_link(String housead_inter_link) {
            Housead_inter_link = housead_inter_link;
            return this;
        }
//
        public setAds Statut(String statut) {
            config.statut = statut;
            return this;
        }

//
        public setAds privacy_url(String privacy_url) {
            config.privacy_url = privacy_url;
            return this;
        }


    }

}

