package com.omega.mouthpiece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.graphics.Color;
import android.util.Log;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;

public class TrainingPage extends AppCompatActivity {
    //features for audio recording
    private MediaRecorder trainerAudio = null;
    private static final String LOG_TAG = "AudioRecTest";
    private static String outputF = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};


    private Button EnDisTrain;
    private Button DisableTrainer;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    private void startRecording(){

        trainerAudio = new MediaRecorder();
        trainerAudio.setAudioSource(MediaRecorder.AudioSource.MIC);
        trainerAudio.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        trainerAudio.setOutputFile(outputF);
        trainerAudio.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            trainerAudio.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        trainerAudio.start();
    }
    private void stopRecoring(){
        trainerAudio.stop();
        trainerAudio.release();
        trainerAudio = null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_page);

        //Enable the voice training button when first opened
        EnDisTrain = findViewById(R.id.enableTrainingButton);
        EnDisTrain.setEnabled(true);
        EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

        //disable the disable voice training button when page is first opened
        DisableTrainer = findViewById(R.id.stopVoiceRecording);
        DisableTrainer.setEnabled(false);
        DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

        /*mp3/ogg/wav*/
        outputF = getExternalCacheDir().getAbsolutePath();
        outputF += "/voice_trainer_recorder_audio.wav";

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        EnDisTrain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                try {
                    //Commented out due to functionality not working
                    startRecording();


                    //Display the phonetic pangrams text for the user to read
                    TextView textview = findViewById(R.id.phonetic_Pangrams_Read_Text);
                    TextView prText = findViewById(R.id.tvPleaseRead);
                    textview.setVisibility(View.VISIBLE);
                    prText.setVisibility(View.VISIBLE);

                    //enable the disable voice training button for recording
                    DisableTrainer.setEnabled(true);
                    DisableTrainer.setTextColor(Color.parseColor("#FFFFFFFF"));

                    //disable the enable voice training button for recording since it is opened
                    EnDisTrain.setEnabled(false);
                    EnDisTrain.setTextColor(Color.parseColor("#FF424242"));

                    Toast.makeText(getApplicationContext(), "Recording your voice...", Toast.LENGTH_SHORT).show();
                }
                catch (IllegalStateException ie) {
                    Toast.makeText(getApplicationContext(), "Illegal state", Toast.LENGTH_SHORT).show();
                    ie.printStackTrace();
                }
            }
        });

        DisableTrainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                stopRecoring();

                //hide the phonetic pangrams text
                TextView textview = findViewById(R.id.phonetic_Pangrams_Read_Text);
                TextView prText = findViewById(R.id.tvPleaseRead);
                textview.setVisibility(View.INVISIBLE);
                prText.setVisibility(View.INVISIBLE);

                //since disable buton already clicked on, disable it
                DisableTrainer.setEnabled(false);
                DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

                //enable the enable voice training button
                EnDisTrain.setEnabled(true);
                EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

                Toast.makeText(getApplicationContext(), "Voice recording disabled", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
