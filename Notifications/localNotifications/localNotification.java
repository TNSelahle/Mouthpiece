package com.example.push;

import android.content.Context;
import android.widget.Toast;

public class localNotification {
    public static void makeToast(Context c, String msg){
        Toast t = Toast.makeText(c,msg,Toast.LENGTH_SHORT);
        t.show();
    }
}
