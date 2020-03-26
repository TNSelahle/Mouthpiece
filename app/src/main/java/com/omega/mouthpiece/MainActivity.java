package com.omega.mouthpiece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Random;

import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable MouthAnimation;
    //------------------------RECORDING VAR----------------------------------
    private MediaRecorder recorder = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private static String fileName = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private boolean recording = false;
    private boolean formants = false;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    //-----------------------------------------------------------------------

    //------------------------HANDLER VAR--------------------------------------
    Handler h2 = new Handler();
    //-----------------------------------------------------------------------

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

    private void startVolumeRecording() {
        if(recording) stopRecording();

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

        if(h2 == null) h2 = new Handler();

        try {
            recorder.start();
            h2.postDelayed(measure,0);
            recording = true;
        }catch(java.lang.IllegalStateException e){
//            e.printStackTrace();
        }
    }


    private void stopRecording()
    {
        if(recorder != null) {
            try {
                recorder.stop();
                recorder.release();
                if(h2 != null) h2.removeCallbacks(measure);
            }catch(java.lang.IllegalStateException e){
//                e.printStackTrace();
            }
            recorder = null;
        }
        recording = false;
    }

    private AudioRecord formRec = null;
    public int i = 0;
    public short[][]   buffers  = new short[256][160];

    private void startFormantRecording()
    {
        if(recording && formants) stopFormantRecording();

        int source = MediaRecorder.AudioSource.MIC;
        int rate = 8000;
        int config = AudioFormat.CHANNEL_IN_MONO;
        int format = AudioFormat.ENCODING_PCM_16BIT;
        int buffSize = AudioRecord.getMinBufferSize(rate, config, format) * 10;

        formRec = new AudioRecord(source, rate, config, format, buffSize);



        if(h2 == null) h2 = new Handler();

        try {
            formRec.startRecording();
            h2.postDelayed(formant,0);
            recording = true;
            formants = true;
        }catch(java.lang.IllegalStateException e){
//            e.printStackTrace();
        }


    }


    private void stopFormantRecording()
    {
        if(formRec != null) {
            try {
                formRec.stop();
                formRec.release();
                if(h2 != null) h2.removeCallbacks(formant);
            }catch(java.lang.IllegalStateException e){
//                e.printStackTrace();
            }
            formRec = null;
        }
        formants = false;
    }


    //----------------------------------MEASURE AMP------------------------------
    public double getAmplitude() {
        if (recorder != null)
            return  (recorder.getMaxAmplitude());
        else
            return 0;

    }

    //----------------------------------MOCK NEURAL FUNCTION-----------------------

    public int convert_audio(short[] buffer)
    {
        Random rand = new Random();
        return rand.nextInt(15);
    }

    //----------------------------------AUDIO SEGMENT------------------------------
    public int getFormant(short[] buffer) {
        int form = 0;
        form = convert_audio(buffer);
        return form;
    }

    //-----------------------------------------------------------------------------
    //-----------------------TIMER FUNCTIONS---------------------------------------
    Runnable measure = new Runnable() {

        @Override
        public void run() {
            ImageView mouthImage = findViewById(R.id.img_mouth);
            double amp = getAmplitude();
            double db = 20 * Math.log10(amp / 0.447);
            if(db >= 71.5) {
                mouthImage.setBackgroundResource(R.drawable.open_mouth);
            }
            else {
                mouthImage.setBackgroundResource(R.drawable.close_mouth);
            }
            MouthAnimation = (AnimationDrawable) mouthImage.getBackground();
            MouthAnimation.start();

            h2.postDelayed(this, 30);
        }
    };
    //---------------------------------------------------------------------------------
    //-----------------------ANIMATION FUNCTIONS---------------------------------------
    Runnable formant = new Runnable() {

        @Override
        public void run() {
            ImageView mouthImage = findViewById(R.id.img_mouth);


            short[] buff = buffers[i++ % buffers.length];
            int form = getFormant(buff);

            if(form == 0) {
                mouthImage.setBackgroundResource(R.drawable.close_mouth);
            }
            else if(form == 1)
            {
                mouthImage.setBackgroundResource(R.drawable.aei_animation);
            }
            else if(form == 2)
            {
                mouthImage.setBackgroundResource(R.drawable.bmp_animation);
            }
            else if(form == 3)
            {
                mouthImage.setBackgroundResource(R.drawable.cdknstxyz_formation);
            }
            else if(form == 4)
            {
                mouthImage.setBackgroundResource(R.drawable.e_formation);
            }
            else if(form == 5)
            {
                mouthImage.setBackgroundResource(R.drawable.f_animation);
            }
            else if(form == 6)
            {
                mouthImage.setBackgroundResource(R.drawable.l_animation);
            }
            else if(form == 7)
            {
                mouthImage.setBackgroundResource(R.drawable.o_animation);
            }
            else if(form == 8)
            {
                mouthImage.setBackgroundResource(R.drawable.qw_animation);
            }
            else if(form == 9)
            {
                mouthImage.setBackgroundResource(R.drawable.r_animation);
            }
            else if(form == 10)
            {
                mouthImage.setBackgroundResource(R.drawable.sh_ch_j_formation);
            }
            else if(form == 11)
            {
                mouthImage.setBackgroundResource(R.drawable.th_animation);
            }
            else if(form == 12)
            {
                mouthImage.setBackgroundResource(R.drawable.m_animation);
            }
            else if(form == 13)
            {
                mouthImage.setBackgroundResource(R.drawable.u_formation);
            }
            else if(form == 14)
            {
                mouthImage.setBackgroundResource(R.drawable.v_animation);
            }
            MouthAnimation = (AnimationDrawable) mouthImage.getBackground();
            MouthAnimation.start();

            h2.postDelayed(this, 20);
        }
    };
    //---------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------RESTORE STATE AFTER ROTATION---------------------
        if (savedInstanceState != null) {
//            mCounter = savedInstanceState.getInt(STATE_COUNTER, 0);
            System.out.println("Restore State");

            fileName = savedInstanceState.getString("fileName", null);
            permissionToRecordAccepted = savedInstanceState.getBoolean("permissionToRecordAccepted",false);
            permissions = savedInstanceState.getStringArray("permissions");

            recording = savedInstanceState.getBoolean("recording",false);
            formants = savedInstanceState.getBoolean("formants",false);

            if(h2 == null) h2 = new Handler();

            if(recording){
                if(formants){
                    startFormantRecording();
                }else{
                    startVolumeRecording();
                }
            }

        }else{
            // Record to the external cache directory for visibility
            fileName = getExternalCacheDir().getAbsolutePath();
            fileName += "/voiceprofile_audio.3gp";

            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        }
        //---------------------------KEEP SCREEN ON------------------------------
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //---------------------------ANIMATION INIT------------------------------
        ImageView mouthImage = findViewById(R.id.img_mouth);
        mouthImage.setBackgroundResource(R.drawable.open_mouth);
        MouthAnimation = (AnimationDrawable) mouthImage.getBackground();

        //---------------------------RECORDING SYSTEM-----------------------------
        Button volume_based_btn = findViewById(R.id.btn_record);
        //Click on Volume button to start recording
        volume_based_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!recording) {//click to record
                    startVolumeRecording();
                } else {//click again to stop recording
                    stopRecording();
                }
            }
        });

        Button formant_based_btn = findViewById(R.id.btnFormant);
        //Click on Formant button to start recording
        formant_based_btn.setOnClickListener(new View.OnClickListener() {
            boolean mStartRecording = true;
            public void onClick(View view) {

                if ( !(recording && formants) ) {//click to record
                    startFormantRecording();
                } else {//click again to stop recording
                    stopFormantRecording();
                }
                mStartRecording = !mStartRecording;
            }
        });

        //-------------------------TESTING--------------------------------
        //Click On image to check animation
        mouthImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });

    }

    //--------------------------AUTO-ROTATE-------------------------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now

        System.out.println("Save State");

        outState.putString("fileName",fileName);
        outState.putBoolean("permissionToRecordAccepted",permissionToRecordAccepted);
        outState.putStringArray("permissions",permissions);

        outState.putBoolean("recording",recording);
        outState.putBoolean("formants",formants);

        if(recording){
            if(formants){
                stopFormantRecording();
            }else{
                stopRecording();
            }
        }
    }

}
