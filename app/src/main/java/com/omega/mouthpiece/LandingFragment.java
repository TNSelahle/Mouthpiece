package com.omega.mouthpiece;

import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.omega.mouthpiece.R;

import java.io.IOException;


public class LandingFragment extends Fragment {

    private ConstraintLayout landingLayout;
    private Button record;
    private Button formant;

    AnimationDrawable MouthAnimation;
    //------------------------RECORDING VAR----------------------------------
    private MediaRecorder recorder = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private static String fileName = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

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
        if (!permissionToRecordAccepted ) getActivity().finish();

    }

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

    private void stopRecording()
    {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    //----------------------------------MEASURE AMP------------------------------
    public double getAmplitude() {
        if (recorder != null)
            return  (recorder.getMaxAmplitude());
        else
            return 0;

    }
    //-----------------------------------------------------------------------------
    //-----------------------TIMER FUNCTIONS---------------------------------------
    Runnable measure = new Runnable() {

        @Override
        public void run() {
            ImageView mouthImage = getView().findViewById(R.id.img_mouth);
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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_landing, container, false);


        record = root.findViewById(R.id.btn_record);
        formant = root.findViewById(R.id.btnFormant);
        landingLayout = root.findViewById(R.id.landingLayout);
        setTheme();
        //---------------------------KEEP SCREEN ON------------------------------
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //---------------------------ANIMATION INIT------------------------------
        ImageView mouthImage = root.findViewById(R.id.img_mouth);
        mouthImage.setImageURI(Uri.parse(getActivity().getFilesDir() + "/Mouthpieces/f0.jpg"));
        mouthImage.setBackgroundResource(R.drawable.open_mouth);
        MouthAnimation = (AnimationDrawable) mouthImage.getBackground();

        //---------------------------RECORDING SYSTEM-----------------------------
        // Record to the external cache directory for visibility
        fileName = getActivity().getExternalCacheDir().getAbsolutePath();
        fileName += "/voiceprofile_audio.3gp";

        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        Button volume_based_btn = root.findViewById(R.id.btn_record);
        //Click on Volume button to start recording
        volume_based_btn.setOnClickListener(new View.OnClickListener() {
            boolean mStartRecording = true;
            public void onClick(View view) {

                if (mStartRecording) {//click to record

                    startRecording();
                    h2.postDelayed(measure,0);
                } else {//click again to stop recording

                    stopRecording();
                    h2.removeCallbacks(measure);
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

        return root;
    }

    public void setTheme() {
        if(GlobalVariableMode.mode == true){

            landingLayout.setBackgroundColor(Color.parseColor("#000000"));

            record.setBackgroundColor(Color.parseColor("#FFFFFF"));
            record.setTextColor(Color.parseColor("#000000"));
            //button colours
            formant.setBackgroundColor(Color.parseColor("#FFFFFF"));
            formant.setTextColor(Color.parseColor("#000000"));

        }
        else{

            landingLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //button colours
            record.setBackgroundColor(Color.parseColor("#000000"));
            record.setTextColor(Color.parseColor("#FFFFFF"));
            //button colours
            formant.setBackgroundColor(Color.parseColor("#000000"));
            formant.setTextColor(Color.parseColor("#FFFFFF"));

        }

    }

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