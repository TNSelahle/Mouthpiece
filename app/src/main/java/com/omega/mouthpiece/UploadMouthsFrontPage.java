package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class UploadMouthsFrontPage extends AppCompatActivity {

    private Button Accept;
    private Button Decline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouth_upload_front_page);

        Accept = findViewById(R.id.Accept_button);
        Decline = findViewById(R.id.Decline_button);

        Accept.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(UploadMouthsFrontPage.this, mouthCreation_ImageUpload.class);
                startActivity(intent);
            }
        });
        Decline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Intent intent = new Intent(RegisterPage.this, MouthSelection.class);
                //startActivity(intent);
            }
        });

    }
}
