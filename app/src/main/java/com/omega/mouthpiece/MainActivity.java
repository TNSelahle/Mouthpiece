package com.omega.mouthpiece;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.NoiseSuppressor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.Random;



import static android.os.Build.VERSION_CODES.N;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable MouthAnimation;
    //------------------------RECORDING VAR----------------------------------
    public static int RECORDER_SAMPLERATE = 8000;
    public static int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    public static int RECORDER_AUDIO_ENDCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord recorder = null;

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


    int bufferSizeInBytes, bufferSizeInShorts;
    short[] audioBuffer;
    private void startVolumeRecording()
    {
        if(recording) stopRecording();


        bufferSizeInBytes = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENDCODING)*10;

        bufferSizeInShorts = (bufferSizeInBytes / 4);

        recorder = new AudioRecord(MediaRecorder.AudioSource.VOICE_RECOGNITION, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENDCODING, bufferSizeInBytes);

        audioBuffer = new short[bufferSizeInShorts];


        try
        {
            recorder.startRecording();
            recording = true;
            configureffects();
            if(h2 == null) h2 = new Handler();
            h2.postDelayed(measure,100);


        }
    catch(java.lang.IllegalStateException e){
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

    private void configureffects()
    {



        if(AcousticEchoCanceler.isAvailable())
        {
            AcousticEchoCanceler echoCanceler = AcousticEchoCanceler.create(recorder.getAudioSessionId());

        }

        if(NoiseSuppressor.isAvailable())
        {
            NoiseSuppressor noiseSuppressor = NoiseSuppressor.create(recorder.getAudioSessionId());

        }
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
            h2.postDelayed(formant,5);
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

    long v = 0;
    public double getAmplitude()
    {
        if(recorder != null) {
            int r = recorder.read(audioBuffer, 0, bufferSizeInShorts);

            v = audioBuffer[i] * audioBuffer[i];
            double mean = v / (double) r;
            double volume = 10 * Math.log10(mean);


            return v;
        }
        else
            return 10;
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

            if(db >= 130.5) {
                mouthImage.setBackgroundResource(R.drawable.open_mouth);
            }
            else {
                mouthImage.setBackgroundResource(R.drawable.close_mouth);
            }
            MouthAnimation = (AnimationDrawable) mouthImage.getBackground();
            MouthAnimation.start();

            h2.postDelayed(this, 0);
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

    //-----------------------------FULLSCREEN------------------------------------------
    private GestureDetectorCompat mDetector;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
            showSystemUI();
            return true;
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        Button formant_based_btn = findViewById(R.id.btnFormant);
        Button volume_based_btn = findViewById(R.id.btn_record);
        formant_based_btn.setVisibility(View.GONE);
        volume_based_btn.setVisibility(View.GONE);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        Button formant_based_btn = findViewById(R.id.btnFormant);
        Button volume_based_btn = findViewById(R.id.btn_record);
        formant_based_btn.setVisibility(View.VISIBLE);
        volume_based_btn.setVisibility(View.VISIBLE);

    }
    //---------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------GESTURE DETECTOR-------------------------------
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
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
                hideSystemUI();
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
                    hideSystemUI();
                } else {//click again to stop recording
                    stopRecording();
                    ImageView mouthImage = findViewById(R.id.img_mouth);
                    mouthImage.setBackgroundResource(R.drawable.m1);
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
                    hideSystemUI();
                } else {//click again to stop recording
                    stopFormantRecording();
                    ImageView mouthImage = findViewById(R.id.img_mouth);
                    mouthImage.setBackgroundResource(R.drawable.m1);
                }
                mStartRecording = !mStartRecording;
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
