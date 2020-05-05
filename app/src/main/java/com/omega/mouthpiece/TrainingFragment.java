package com.omega.mouthpiece;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class TrainingFragment extends Fragment  {
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

    private ConstraintLayout trainingPageConstraint;
    private TextView readText;
    private Chronometer timerC;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_training, container, false);

        vw = root.findViewById(R.id.videoGif);
        vw.setVideoPath("https://i.gifer.com/Nt6v.mp4");
        vw.setVisibility(View.INVISIBLE);


        //Enable the voice training button when first opened
        EnDisTrain = root.findViewById(R.id.enableTrainingButton);
        EnDisTrain.setEnabled(true);
        EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

        //disable the disable voice training button when page is first opened
        DisableTrainer = root.findViewById(R.id.stopVoiceRecording);
        DisableTrainer.setEnabled(false);
        DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

        PlayRecording = root.findViewById(R.id.testAudioRec);
        PlayRecording.setEnabled(false);
        PlayRecording.setTextColor(Color.parseColor("#FF424242"));
        trainingPageConstraint = root.findViewById(R.id.trainingConstraint);
        readText = root.findViewById(R.id.phonetic_Pangrams_Read_Text);
        timerC = root.findViewById(R.id.recordingTimer);
        setTheme();

        ( root.findViewById(R.id.recordingTimer)).setVisibility(View.INVISIBLE);


        /*mp3/ogg/wav*/
        outputF = getActivity().getExternalCacheDir().getAbsolutePath();
        outputF += "/voice_trainer_recorder_audio.wav";

        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        EnDisTrain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


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


                    //Display the phonetic pangrams text for the user to read
                    TextView textview = getView().findViewById(R.id.phonetic_Pangrams_Read_Text);
                    TextView prText = getView().findViewById(R.id.tvPleaseRead);
                    textview.setVisibility(View.VISIBLE);
                    prText.setVisibility(View.VISIBLE);

                    ( getView().findViewById(R.id.recordingTimer)).setVisibility(View.VISIBLE);

                    //enable the disable voice training button for recording
                    DisableTrainer.setEnabled(true);
                    DisableTrainer.setTextColor(Color.parseColor("#FFFFFFFF"));

                    //disable the enable voice training button for recording since it is opened
                    EnDisTrain.setEnabled(false);
                    EnDisTrain.setTextColor(Color.parseColor("#FF424242"));


                    Toast.makeText(getActivity(), "Recording your voice...", Toast.LENGTH_SHORT).show();
                }
                catch (IllegalStateException ie) {
                    Toast.makeText(getActivity(), "Illegal state", Toast.LENGTH_SHORT).show();
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
                stopChronometer(view);
                vw.setVisibility(View.INVISIBLE);
                vw.stopPlayback();

                //hide the phonetic pangrams text
                TextView textview = getView().findViewById(R.id.phonetic_Pangrams_Read_Text);
                TextView prText = getView().findViewById(R.id.tvPleaseRead);
                textview.setVisibility(View.INVISIBLE);
                prText.setVisibility(View.INVISIBLE);

                ( getView().findViewById(R.id.recordingTimer)).setVisibility(View.INVISIBLE);

                //since disable buton already clicked on, disable it
                DisableTrainer.setEnabled(false);
                DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

                //enable the enable voice training button
                EnDisTrain.setEnabled(true);
                EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));

                PlayRecording.setEnabled(true);
                PlayRecording.setTextColor(Color.parseColor("#FFFFFFFF"));

                Toast.makeText(getActivity(), "Voice recording disabled", Toast.LENGTH_SHORT).show();
            }
        });

        PlayRecording.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                playRecording();
            }
        });

        return root;
    }









    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) getActivity().finish();

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
        ((Chronometer) getView().findViewById(R.id.recordingTimer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) getView().findViewById(R.id.recordingTimer)).start();
    }

    public void stopChronometer(View view) {
        ((Chronometer) getView().findViewById(R.id.recordingTimer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) getView().findViewById(R.id.recordingTimer)).stop();
    }

    public void setTheme() {
        if(GlobalVariableMode.mode == true){

            trainingPageConstraint.setBackgroundColor(Color.parseColor("#000000"));
            //button colours
            timerC.setTextColor(Color.parseColor("#FFFFFF"));
            readText.setTextColor(Color.parseColor("#FFFFFF"));

            //button colours

        }
        else{

            trainingPageConstraint.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //button colours
            timerC.setTextColor(Color.parseColor("#000000"));
            readText.setTextColor(Color.parseColor("#000000"));
            //button colours

        }

    }


    //////////////////////////


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }




}

