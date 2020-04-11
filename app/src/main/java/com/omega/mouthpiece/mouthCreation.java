package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mouthCreation extends AppCompatActivity {

    private Button btnStartUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouth_creation);

        btnStartUpload.findViewById(R.id.btnStart);
        btnStartUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mouthCreation.this, mouthCreation_ImageUpload.class);
                startActivity(intent);
            }
        });
    }
}
