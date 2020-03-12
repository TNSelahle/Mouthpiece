package com.example.notifications;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    protected Button toast;
    protected Button email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int lastNotificationID=0;

       toast = (Button)findViewById(R.id.toast);
       email = (Button)findViewById(R.id.email);

       toast.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               JSONObject json = getJSON();
               Toast t = ToastNotification.createToast(json,getApplicationContext());
               t.show();

           }
       });

       email.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v)
           {
               JSONObject json = getJSON();
               EmailNotification.createEmail(json);
           }
       });


        //i went to far , only ganna demo with buttons for now
//        while(true) {//app will constantly look for notification Toasts from the Server if it connected
//            //if app is ofline : break
//            try {
//                if (NewNotificationAvaiable(lastNotificationID)) {
//                    JSONObject jsonInfo = getJSON();
//                    String NotificationType =jsonInfo.getString("NotiType");
//                    if (NotificationType=="Toast") {
//                        Context c = getApplicationContext();
//                        Toast toast = ToastNotification.createToast(jsonInfo, c); //creates the toast to be displayed
//                        toast.show();//displays the toast that was created.
//                    }
//                    else if (NotificationType=="Email")
//                    {
//                        EmailNotification.createEmail(jsonInfo);
//                    }
//                }
//            }catch(JSONException e)
//            {
//                System.out.println("There was an error retrieving the message of the toast: "+e);
//            }
//        }
    }

    private void pullNotification()
    {
        JSONObject json = getJSON();
        Toast t = ToastNotification.createToast(json,getApplicationContext());
        t.show();
    }

    private void NotificationCall(JSONObject json)
    {
        Toast t = ToastNotification.createToast(json,getApplicationContext());
        t.show();
    }

    //a function on the server will call the notificationCall function with a json object to create a toast.
    //the server will also be responsible for sending an email

    private boolean NewNotificationAvaiable(int lastNotificationID)//connects to the server and see if there is a new Notification waiting with a different timestamp
    {
        //try to establish connection (not possible if in ofline mode)
            //look at the API if there is a new Toast notification type for your user name

        //for now just return true for 1st demo purposes
        return true;
    }

    private JSONObject getJSON() //The json object must be provided by the API
    {
        //for now we will just use a standard json object for testing
       try {
           JSONObject obj = new JSONObject();

           obj.put("User", "MadRabit");
           obj.put("Email", "cosomegatest@gmail.com");
           obj.put("NotificationId","1");
           obj.put("NotiType", "Toast");//email
           obj.put("Msg", "You uploaded a new moutpiece");

           return obj;
       }catch (JSONException e)
       {
           System.out.println("There was an error assigning the json object");
       }
       return null;

    }


}
