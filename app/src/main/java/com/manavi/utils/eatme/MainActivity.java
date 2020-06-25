package com.manavi.utils.eatme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    AdRequest adRequest;
    Button button, shareButton;
    TextView textView, warningText;
    private String appName;
    private ArrayList<String> appsList;
    private ArrayList<String> suspiciosAppsList;
    private int countChanApp;
    private ArrayList<String> chanAppList = new ArrayList<>();
    private String appServiceName;
    boolean doubleBackToExitPressedOnce = false;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        System.out.println("CREATED");
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        shareButton = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        warningText = (TextView) findViewById(R.id.textView2);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setBackgroundColor(Color.BLACK);
        warningText.setMovementMethod(new ScrollingMovementMethod());
        adView = (AdView) findViewById(R.id.ad_View);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        //List app button clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getInstalledApps();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        //Share on WhatsApp Button Clicked
        shareButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    //getInstalledApps();
                    sendToWhatsApp();
                } catch (Exception e) {

                }
            }
        }));
    }

    private void sendToWhatsApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        if (chanAppList.size() > 0) {
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I just found *" + chanAppList.size() + "* banned apps installed on my phone via this nice utility, you can find it by the name  *ChanBan* on Google Playstore!!");
        } else {
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, I found one nice and small app to scan and list Banned applications on your phone, you can find it by the name  *ChanBan* on Google Playstore!!");
        }
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    public String getAlternativeApp(String appName) {
        String alternateAppName = "";

        switch (appName.toLowerCase()) {
            case "tiktok":
                alternateAppName = "Bolo Indya, Roposo";
                break;
            case "pubg mobile":
                alternateAppName = "Fortnite, Call of Duty, Garena Free Fire";
                break;
            case "shareit":
                alternateAppName = "Google Files (USA), Xender (Hong Kong)";
                break;
            case "uc browser":
                alternateAppName = "JioBrowser (India)";
                break;
            case "beautyplus":
                alternateAppName = "Selfie Camera, B612 Beauty, Filter Camera, Candy Camera";
                break;
            case "camscanner":
                alternateAppName = "Adobe Scan, Microsoft Lens";
                break;
            case "helo":
                alternateAppName = "ShareChat";
                break;
            case "club factory - online shopping app":
                alternateAppName = "Flipkart, Amazon India, Koovs";
                break;
            case "applock":
                alternateAppName = "Norton App Lock";
                break;
            case "viva video":
                alternateAppName = "KineMaster, Adobe Premier Rush";
                break;
            case "kwai":
                alternateAppName = "Periscope";
                break;
            case "uc news":
                alternateAppName = "Google News";
                break;
            case "parallel space":
                alternateAppName = "App Cloner";
                break;
            case "facebook":
                alternateAppName = "Orkut";
                break;
            case "du browser":
                alternateAppName = "Firefox, JioBrowser(India)";
                break;
            default:
                alternateAppName = "NA";
                break;
        }
        return alternateAppName;

    }

    public List<String> getSuspiciosAppsList() {
        suspiciosAppsList = new ArrayList<String>() {
            {
                add("TikTok".toLowerCase());
                add("Vault-Hide".toLowerCase());
                add("Vigo Video".toLowerCase());
                add("Bigo Live".toLowerCase());
                add("Weibo".toLowerCase());
                add("WeChat".toLowerCase());
                add("ShareIt".toLowerCase());
                add("UC News".toLowerCase());
                add("UC Browser".toLowerCase());
                add("BeautyPlus".toLowerCase());
                add("Xender".toLowerCase());
                add("Club Factory - Online Shopping App".toLowerCase());
                add("Helo".toLowerCase());
                add("LIKE".toLowerCase());
                add("Kwai".toLowerCase());
                add("ROMWE".toLowerCase());
                add("SHEIN".toLowerCase());
                add("NewsDog".toLowerCase());
                add("Photo Wonder".toLowerCase());
                add("APUS Browser".toLowerCase());
                add("VivaVideo".toLowerCase());
                add("Perfect Corp".toLowerCase());
                add("CM Browser".toLowerCase());
                add("Virus Cleaner (Hi Security Lab)".toLowerCase());
                add("Mi Community".toLowerCase());
                add("DU recorder".toLowerCase());
                add("YouCam Makeup".toLowerCase());
                add("Mi Store".toLowerCase());
                add("360 Security".toLowerCase());
                add("DU Battery Saver".toLowerCase());
                add("DU Browser".toLowerCase());
                add("DU Cleaner".toLowerCase());
                add("DU Privacy".toLowerCase());
                add("Clean Master – Cheetah".toLowerCase());
                add("CacheClear DU apps studio".toLowerCase());
                add("Baidu Translate".toLowerCase());
                add("Baidu Map".toLowerCase());
                add("Wonder Camera".toLowerCase());
                add("ES File Explorer".toLowerCase());
                add("QQ International".toLowerCase());
                add("QQ Launcher".toLowerCase());
                add("QQ Security Centre".toLowerCase());
                add("QQ Player".toLowerCase());
                add("QQ Music".toLowerCase());
                add("QQ Mail".toLowerCase());
                add("QQ NewsFeed".toLowerCase());
                add("WeSync".toLowerCase());
                add("SelfieCity".toLowerCase());
                add("Clash of Kings".toLowerCase());
                add("Mail Master".toLowerCase());
                add("Mi Video call - Xiaomi".toLowerCase());
                add("Parallel Space".toLowerCase());
                add("PUBG Mobile".toLowerCase());
                add("CamScanner".toLowerCase());
                add("Twitter".toLowerCase());
                add("Turbo VPN".toLowerCase());
            }
        };

        return suspiciosAppsList;
    }

    private void showProgress() throws InterruptedException {
        final Handler progressBarHandler = new Handler();
        final int progressBarStatus = 0;
        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Sanitizing your phone ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();
        progressBar.setMax(100);
        final int totalProgressTime = 100;
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int jumpTime = 0;
                                while (jumpTime < totalProgressTime) {
                                    try {
                                        progressBar.show();
                                        sleep(200);
                                        jumpTime += 5;
                                        progressBar.setProgress(jumpTime);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                                progressBar.dismiss();
                                listInstalledApps();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }


    public void getInstalledApps() throws InterruptedException {
        showProgress();

    }

    private void listInstalledApps() {
        List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(0);
        appsList = new ArrayList<>();
        for (PackageInfo packageInfo : packageInfoList) {
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                appsList.add(appName);
            }
        }
        Collections.sort(appsList);

        textView.setText(Html.fromHtml("<font color=#ffffff>List of installed apps on your phone: </font> <br>"));
        for (int i = 0; i < appsList.size(); i++) {
            appName = appsList.get(i);
            if (getSuspiciosAppsList().contains(appName.toLowerCase())) {
                appName = "<font color=#FF4C33><b><u>" + appName + "</b></u> - " + getAlternativeApp(appName) +"</font>";
                chanAppList.add(appName);
                countChanApp++;
            } else {
                appName = "<font color=#FFF933>" + appName + "</font>";
            }
            textView.append(Html.fromHtml("<font color=#ffffff> " + (i + 1) + ": </font> " + appName + "<br>"));
        }
        warningText.setText(Html.fromHtml("<b><i>No of Chan Apps found: " + countChanApp + "</i></b><br>"));
        if (countChanApp == 0) {
            warningText.append(Html.fromHtml("<br><hr><b>Congratulations!!!</b> <br>0 apps found, your phone is already ChanBanned!"));
        } else {
            for (int i = 0; i < chanAppList.size(); i++) {
                warningText.append(Html.fromHtml((i + 1) + ":\t" + chanAppList.get(i) + "<br>"));
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.destroy();
        }
        Toast.makeText(this, "Thanky you for using this app, kindly share this with your friends", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
                adView.destroy();
            }
        }, 2000);
    }

}
