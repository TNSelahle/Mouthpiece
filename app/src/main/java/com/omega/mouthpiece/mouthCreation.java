package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;

public class mouthCreation extends AppCompatActivity {

    private Button btnStartUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_creation);

    }
    public void clickHandler(View view){
        Intent intent = new Intent(mouthCreation.this, mouthCreation_ImageUpload.class);
        startActivity(intent);
    }
}
