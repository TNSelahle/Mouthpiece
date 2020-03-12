package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToBrowserPage(View view)
    {
        Intent intent= new Intent(this, openWebsiteActivity.class);
        startActivity(intent);
    }
    public void goToTrainingPage(View view)
    {
        Intent intent= new Intent(this, trainingActivity.class);
        startActivity(intent);
    }
    public void goToMouthpiecePage(View view)
    {
        Intent intent= new Intent(this, mouth_Display.class);
        startActivity(intent);
    }
    public void goToSettingsPage(View view)
    {
        Intent intent= new Intent(this, Activity_Settings.class);
        startActivity(intent);
    }
}
