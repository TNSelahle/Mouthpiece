package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class LoadingPage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);
        //TODO: Implement actual loading and Redesign.
        Thread background = new Thread() {
            public void run() {
                runOnUiThread(new Runnable()
                {
                    public void run() {

                        try {
                            // Thread will sleep for 5 seconds
                            sleep(3*1000);
                            // After 5 seconds redirect to another intent
                            sharedPreferences=getSharedPreferences("LoginPrefs",MODE_PRIVATE);
                            String remAPI=sharedPreferences.getString("API","");
                            Intent intent;
                            if(remAPI=="")
                                intent = new Intent(LoadingPage.this, LoginPage.class);
                            else
                                intent = new Intent(LoadingPage.this,MainActivity.class);
                            startActivity(intent);

                            //Remove activity
                            finish();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        };
        // start thread
        background.start();
    }

}
