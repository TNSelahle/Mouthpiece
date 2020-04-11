package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;

public class mouthCreation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_creation);
    }

    public void onClickHandler(){
        Intent intent = new Intent(mouthCreation.this, mouthCreation.class);
        startActivity(intent);
    }
}
