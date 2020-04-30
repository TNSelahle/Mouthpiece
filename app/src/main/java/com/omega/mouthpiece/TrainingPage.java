package com.omega.mouthpiece;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;


        import android.Manifest;
        import android.content.Context;
        import android.graphics.Color;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
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
    private Button EnDisTrain;
    private Button DisableTrainer;
    private static String outputF = null;
    private MediaRecorder trainerAudio;
    /*
    private static final String LOG_TAG = "AudioRecTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    //-----------------------------------------------------------------------

    //------------------------HANDLER VAR--------------------------------------
   // Handler h2 = new Handler();
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
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_page);

        EnDisTrain = findViewById(R.id.enableTrainingButton);
        EnDisTrain.setEnabled(true);
        EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));
        DisableTrainer = findViewById(R.id.stopVoiceRecording);
        DisableTrainer.setEnabled(false);
        DisableTrainer.setTextColor(Color.parseColor("#FF424242"));

        /*mp3/ogg/wav*/
        outputF = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.mp3";

        //ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        /*Commented out due to functionality not working
        trainerAudio = new MediaRecorder();
        trainerAudio.setAudioSource(MediaRecorder.AudioSource.MIC);
        trainerAudio.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        trainerAudio.setOutputFile(outputF);
        trainerAudio.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        */
        EnDisTrain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                try {
                    /*Commented out due to functionality not working
                    trainerAudio.prepare();
                    trainerAudio.start();
                    */
                    TextView textview= /*(TextView)*/findViewById(R.id.phonetic_Pangrams_Read_Text);
                    TextView prText= /*(TextView)*/findViewById(R.id.tvPleaseRead);
                    textview.setVisibility(View.VISIBLE);
                    prText.setVisibility(View.VISIBLE);
                    DisableTrainer.setEnabled(true);
                    DisableTrainer.setTextColor(Color.parseColor("#FFFFFFFF"));
                    EnDisTrain.setEnabled(false);
                    EnDisTrain.setTextColor(Color.parseColor("#FF424242"));
                    Toast.makeText(getApplicationContext(), "Recording your voice...", Toast.LENGTH_SHORT).show();
                } catch (IllegalStateException ie) {
                    Toast.makeText(getApplicationContext(), "Illegal state", Toast.LENGTH_SHORT).show();
                    ie.printStackTrace();

                }
                /*Commented out due to functionality not working
                catch (IOException ioe) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    //Log.e(LOG_TAG, "prepare() failed");
                    ioe.printStackTrace();
                }*/
                //trainerAudio.start();

            }
        });

        DisableTrainer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                TextView textview=/*(TextView)*/ findViewById(R.id.phonetic_Pangrams_Read_Text);
                TextView prText=/*(TextView)*/ findViewById(R.id.tvPleaseRead);
                textview.setVisibility(View.INVISIBLE);
                prText.setVisibility(View.INVISIBLE);
                DisableTrainer.setEnabled(false);
                DisableTrainer.setTextColor(Color.parseColor("#FF424242"));
                EnDisTrain.setEnabled(true);
                EnDisTrain.setTextColor(Color.parseColor("#FFFFFFFF"));
                //problem with recording audio
                /*Commented out due to functionality not working
                trainerAudio.stop();
                trainerAudio.release();
               */
                trainerAudio = null;
                Toast.makeText(getApplicationContext(), "Voice recording disabled", Toast.LENGTH_SHORT).show();
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            changeTextStatus(true);
        } else {
            changeTextStatus(false);
        }

    }
    public void changeTextStatus(boolean isConnected) {
        if (!isConnected) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }
}
