package com.omega.mouthpiece;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GestureDetectorCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioSource;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;


public class VoiceProfile_Trainer extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_profile);

        //---------------------------KEEP SCREEN ON------------------------------
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //---------------------------ANIMATION INIT------------------------------
        ImageView mouthImage = findViewById(R.id.img_mouth);
        mouthImage.setBackgroundResource(R.drawable.open_mouth);
        MouthAnimation = (AnimationDrawable) mouthImage.getBackground();


        requestPermissions();
        setUp();
    }
    /* prepare for us */

    public void blackOut() {
        Button saveButton = findViewById(R.id.save_recording);
        saveButton.setBackgroundColor(Color.rgb(215, 215, 215));
        saveButton.setOnClickListener(null);

        ImageView startRecord = findViewById(R.id.start_recording);
        startRecord.setColorFilter(Color.rgb(215, 215, 215));
        startRecord.setOnClickListener(null);

        ImageView playAudio = findViewById(R.id.play_recording);
        playAudio.setColorFilter(Color.rgb(215, 215, 215));
        playAudio.setOnClickListener(null);
    }

     private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 200);
    }

    private void setUp() {
        displayProfileCount();

        ImageView deleteRecordings = findViewById(R.id.delete_all);
        deleteRecordings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllProfiles();
            }
        });

        ImageView startRecording = findViewById(R.id.start_recording);
        startRecording.setColorFilter(Color.rgb(0, 0, 0));
        startRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording)
                    stopRecording();
                else
                    recordAudio();
            }
        });

        ImageView playRecording = findViewById(R.id.play_recording);
        playRecording.setColorFilter(Color.rgb(0, 0, 0));
        playRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing)
                    stopAudio();
                else
                    playAudio();
            }
        });

        Button saveRecording = findViewById(R.id.save_recording);
        saveRecording.setBackgroundColor(Color.rgb(0, 0, 0));
        saveRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecording();
            }
        });

        if (numberOfProfiles >= 4)
            blackOut();
    }

    /* functionality functions */

    private void deleteAllProfiles() {
        File tempFile = new File(Environment.getExternalStorageDirectory() + "/VoiceProfiles");
        if (tempFile.exists()) {
            File[] children = tempFile.listFiles();
            for (File f : children) {
                if (f.getName().endsWith(".mp3"))
                    f.delete();
            }
        }

        setUp();
    }

    private void displayProfileCount() {
        numberOfProfiles = 0;
        File tempFile = new File(Environment.getExternalStorageDirectory() + "/VoiceProfiles");
        if (tempFile.exists()) {
            File[] children = tempFile.listFiles();
            for (File f : children) {
                if (f.getName().endsWith(".mp3"))
                    numberOfProfiles++;
            }
        }

        TextView noProfiles = findViewById(R.id.profile_count);
        noProfiles.setText(String.valueOf(numberOfProfiles));
    }

    private MediaPlayer player = null;
    private boolean playing = false;

    public void playAudio() {
        stopRecording();

        playing = true;
        if (recordedAudio != null) {
            player = new MediaPlayer();
            try {
                player.setDataSource(String.valueOf(recordedAudio));
                player.prepare();
                player.start();
            } catch (IOException e) {
                Log.e(null, "Process failed");
            }
        }

        ImageView setIcon = findViewById(R.id.play_recording);
        setIcon.setColorFilter(Color.rgb(219, 0, 0));
    }

    NN_Handler nn_handler = new NN_Handler();
    private boolean formants = false;
    Handler h2 = new Handler();
    private boolean recording = false;
    private static File recordedAudio = null;
    private AudioRecord recorder = null;
    AnimationDrawable MouthAnimation;       //iniatilize
    public int i = 0;
    public short[][]   buffers  = new short[256][160];


    //----------------------------------AUDIO SEGMENT------------------------------
    public int getFormant(float[] buffer) {
        int form = 0;
        SegmentNode node = new SegmentNode(buffer);
        node = nn_handler.getPhonetic(node);
        return node.getLabel();
    }

    //---------------------------------------------------------------------------------
    //-----------------------ANIMATION FUNCTIONS---------------------------------------
    Runnable formant = new Runnable() {

        @Override
        public void run() {
            ImageView mouthImage = findViewById(R.id.img_mouth);

            //Get buffer values
            short[] buff_short = buffers[i++ % buffers.length];
            float[] buff_float = new float[buff_short.length];
            int index = 0;
            //convert short to float
            for (short x: buff_short) {
                buff_float[index] = x;
                index++;
            }
            int form = getFormant(buff_float);

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

    public void recordAudio() {
        stopAudio();

        if (recordedAudio != null) {
            if (recordedAudio.delete())
                recordedAudio = null;
        }

        File storageDir = new File(Environment.getExternalStorageDirectory() + "/VoiceProfiles");
        if (!storageDir.exists())
            storageDir.mkdir();
        File audioFile = new File(storageDir.getPath() + "/" + System.currentTimeMillis() + ".mp3");
        recordedAudio = audioFile;

        if (recorder == null) {
            /*recorder = new MediaRecorder();
            recorder.setAudioSource(AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setOutputFile(audioFile.getPath());
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                recorder.prepare();
            } catch (IOException e) {
                Log.e(null, "Process failed");
            }
*/
            int source = MediaRecorder.AudioSource.MIC;
            int rate = 8000;
            int config = AudioFormat.CHANNEL_IN_MONO;
            int format = AudioFormat.ENCODING_PCM_FLOAT;
            int buffSize = AudioRecord.getMinBufferSize(rate, config, format) * 10;

            recorder = new AudioRecord(source, rate, config, format, buffSize);

            if(h2 == null) h2 = new Handler();

            if (!recording) {
                try {
                    recorder.startRecording();
                    h2.postDelayed(formant,0);
                    recording = true;
                    formants = true;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                recording = true;

                TextView tv = findViewById(R.id.action_status);
                tv.setText(R.string.while_recording);
            }
        }

        ImageView setIcon = findViewById(R.id.start_recording);
        setIcon.setColorFilter(Color.rgb(219, 0, 0));
    }

    private int numberOfProfiles = 0;
    public void saveRecording() {
        if (recordedAudio != null && numberOfProfiles < 4) {
            sendForProcessing();
            recordedAudio = null;
            displayProfileCount();
        }

        if (numberOfProfiles >= 4)
            blackOut();
    }

    public void sendForProcessing () {
        process(recordedAudio);
    }

    /* Mock neural network function - needs revision - input and segmentation */
    @SuppressLint("WrongConstant")
    public void process(File f) {
        if (f != null && f.exists())
            Log.println(0, "NN Transfer", "Successful");
        else
            Log.println(0, "NN Transfer", "Failed");
    }

    public void stopRecording() {
      /*  if (recording && recorder != null) {
            try {
                recorder.stop();
            }
            catch (IllegalStateException e) {
                e.printStackTrace();
            }

            recorder.release();
            recorder = null;
            recording = false;

            TextView tv = findViewById(R.id.action_status);
            tv.setText(R.string.action_status_text);
        }*/

        if(recorder != null) {
            try {
                recorder.stop();
                recorder.release();
                if(h2 != null) h2.removeCallbacks(formant);
            }catch(java.lang.IllegalStateException e){
//                e.printStackTrace();
            }
            recorder = null;
            recording = false;

            TextView tv = findViewById(R.id.action_status);
            tv.setText(R.string.action_status_text);
        }
        formants = false;

        ImageView setIcon = findViewById(R.id.start_recording);
        setIcon.setColorFilter(Color.rgb(0, 0, 0));
    }

    public void stopAudio() {
        if (player != null) {
            player.release();
            player = null;
            playing = false;

            ImageView setIcon = findViewById(R.id.play_recording);
            setIcon.setColorFilter(Color.rgb(0, 0, 0));
        }
    }
}



