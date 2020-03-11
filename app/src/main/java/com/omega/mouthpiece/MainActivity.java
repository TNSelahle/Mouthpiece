package com.omega.mouthpiece;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable MouthAnimation;
    private MediaRecorder recorder = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private static String fileName = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};


    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        recorder.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //---------------------------RECORDING SYSTEM-----------------------------
        // Record to the external cache directory for visibility
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/voiceprofile_audio.3gp";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        Button volume_based_btn = findViewById(R.id.btn_record);
        //Click on Volume button to start recording
        volume_based_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startRecording();
            }
        });
        //-------------------------MEASURE AUDIO INTENSITY-------------------------------
        //TODO
        //---------------------------ANIMATION------------------------------
        ImageView mouthImage = findViewById(R.id.img_mouth);
        mouthImage.setBackgroundResource(R.drawable.open_mouth);
        MouthAnimation = (AnimationDrawable) mouthImage.getBackground();

        //-------------------------TESTING--------------------------------
        //Click On image to check animation
        mouthImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MouthAnimation.start();
            }
        });

    }
}
