package com.omega.mouthpiece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.SystemClock;
import android.util.Log;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class TrainingPage extends Fragment {
    //features for audio recording
    private MediaRecorder trainerAudio = null;

    private VideoView vw;

    private static final String LOG_TAG = "AudioRecTest";
    private static String outputF = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};


    private Button EnDisTrain;
    private Button DisableTrainer;

    private Button PlayRecording;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        //if (!permissionToRecordAccepted ) finish();
        if (!permissionToRecordAccepted ) getActivity().getFragmentManager().popBackStack();

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
    private void playRecording(){
        MediaPlayer vtPlayer = new MediaPlayer();
        try {
            vtPlayer.setDataSource(outputF);
            vtPlayer.prepare();
            vtPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

    }

    ///////////////////////////
    public void startChronometer(View view) {
        ((Chronometer) view.findViewById(R.id.recordingTimer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) view.findViewById(R.id.recordingTimer)).start();
    }

    public void stopChronometer(View view) {
        ((Chronometer) view.findViewById(R.id.recordingTimer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) view.findViewById(R.id.recordingTimer)).stop();
    }


    //////////////////////////


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.training_page, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        FragmentActivity a = getActivity();
        vw = v.findViewById(R.id.videoGif);
        vw.setVideoPath("https://i.gifer.com/Nt6v.mp4");
        vw.setVisibility(View.INVISIBLE);


        //Enable the voice training button when first opened
        EnDisTrain = v.findViewById(R.id.enableTrainingButton);
        EnDisTrain.setEnabled(true);
        EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

        //disable the disable voice training button when page is first opened
        DisableTrainer = v.findViewById(R.id.stopVoiceRecording);
        DisableTrainer.setEnabled(false);
        DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

        PlayRecording = v.findViewById(R.id.testAudioRec);
        PlayRecording.setEnabled(false);
        PlayRecording.setTextColor(Color.parseColor("#FF424242"));

        (v.findViewById(R.id.recordingTimer)).setVisibility(View.INVISIBLE);


        /*mp3/ogg/wav*/
        outputF = a.getExternalCacheDir().getAbsolutePath();
        outputF += "/voice_trainer_recorder_audio.wav";

        ActivityCompat.requestPermissions(a, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        EnDisTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    //Commented out due to functionality not working
                    startRecording();
                    startChronometer(view);
                    vw.setVisibility(View.VISIBLE);
                    vw.start();

                    /////////////
                    vw.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });

                    /////////

                    View v = getView();
                    FragmentActivity a = getActivity();

                    //Display the phonetic pangrams text for the user to read
                    TextView textview = v.findViewById(R.id.phonetic_Pangrams_Read_Text);
                    TextView prText = v.findViewById(R.id.tvPleaseRead);
                    textview.setVisibility(View.VISIBLE);
                    prText.setVisibility(View.VISIBLE);

                    (v.findViewById(R.id.recordingTimer)).setVisibility(View.VISIBLE);

                    //enable the disable voice training button for recording
                    DisableTrainer.setEnabled(true);
                    DisableTrainer.setTextColor(Color.parseColor("#FFFFFFFF"));

                    //disable the enable voice training button for recording since it is opened
                    EnDisTrain.setEnabled(false);
                    EnDisTrain.setTextColor(Color.parseColor("#FF424242"));

                    Toast.makeText(a.getApplicationContext(), "Recording your voice...", Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException ie) {
                    Toast.makeText(getActivity().getApplicationContext(), "Illegal state", Toast.LENGTH_SHORT).show();
                    ie.printStackTrace();
                }
            }
        });

        DisableTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecoring();
                stopChronometer(view);
                vw.setVisibility(View.INVISIBLE);
                vw.stopPlayback();

                View v = getView();

                //hide the phonetic pangrams text
                TextView textview = v.findViewById(R.id.phonetic_Pangrams_Read_Text);
                TextView prText = v.findViewById(R.id.tvPleaseRead);
                textview.setVisibility(View.INVISIBLE);
                prText.setVisibility(View.INVISIBLE);

                (v.findViewById(R.id.recordingTimer)).setVisibility(View.INVISIBLE);

                //since disable buton already clicked on, disable it
                DisableTrainer.setEnabled(false);
                DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

                //enable the enable voice training button
                EnDisTrain.setEnabled(true);
                EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

                PlayRecording.setEnabled(true);
                PlayRecording.setTextColor(Color.parseColor("#FFFFFFFF"));

                Toast.makeText(getActivity().getApplicationContext(), "Voice recording disabled", Toast.LENGTH_SHORT).show();
            }
        });

        PlayRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRecording();
            }
        });


    }
}
