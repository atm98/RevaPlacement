package com.agnt45.revaplacement.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.agnt45.revaplacement.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this,Screen2.class));
                    finish();
                    overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                }
            }, 4000);
        }
        else{
            Snackbar snackbar = Snackbar.make(findViewById(R.id.Splash_screen),"You are not connected to the Internet",Snackbar.LENGTH_LONG).setAction("TURN ON", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
                    if(isWiFi){
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }else{
                        startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                    }
                }
            });
            snackbar.show();

        }
    }


}
