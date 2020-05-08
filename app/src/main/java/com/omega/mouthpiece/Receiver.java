package com.omega.mouthpiece;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class Receiver extends BroadcastReceiver {
    public Receiver() {}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            boolean isVisible = MyApp.isActivityVisible();// Check if
            Log.i("Activity is Visible ", "Is activity visible : " + isVisible);

            if (isVisible) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    //new MainActivity().changeTextStatus(true);
                } else {
                    //new MainActivity().changeTextStatus(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}