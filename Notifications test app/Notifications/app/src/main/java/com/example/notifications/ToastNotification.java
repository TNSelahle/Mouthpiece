package com.example.notifications;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class ToastNotification {

    public static Toast createToast(JSONObject json, Context c)
    {
        try {
            String ToastMsg = json.getString("User");
            ToastMsg+=": ";
            ToastMsg+=json.getString("Msg");
            Toast toast = Toast.makeText(c,ToastMsg,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,0);
            return toast;
        }catch (JSONException e)
        {
            System.out.println("Error creating a toast with json data: "+e);
        }
        System.out.println("Toast not successfully created");
        return null;
    }


}
