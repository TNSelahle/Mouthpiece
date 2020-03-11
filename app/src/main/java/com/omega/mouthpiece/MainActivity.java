package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable mouthAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mouthImage = findViewById(R.id.img_mouth);
        mouthImage.setBackgroundResource(R.drawable.mouth_animation);
        mouthAnimation = (AnimationDrawable) mouthImage.getBackground();

        mouthImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mouthAnimation.start();
            }
        });
    }
}
