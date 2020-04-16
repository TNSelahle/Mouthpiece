package com.omega.mouthpiece ;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class mouthCreation_ImageUpload extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    private Button btnUpload;
    private Button btnNext;
    private Button btnCancel;
    private TextView mouthShapeNumber;
    private ImageView egImage;
    private ImageView userImage;
    private ImageView userImage2;
    private ImageView userImage3;
    private ImageView userImage4;
    private ImageView userImage5;
    private ImageView userImage6;
    private ImageView userImage7;
    private ImageView userImage8;
    private ImageView userImage9;
    private ImageView userImage10;
    private ImageView userImage11;
    private ImageView userImage12;
    private int i = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            userImage.setImageURI(selectedImage);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouth_creation_image_upload);

        btnUpload = findViewById(R.id.btn_choose_image);
        btnNext = findViewById(R.id.btn_nxt);
        btnCancel = findViewById(R.id.btn_cancel);
        mouthShapeNumber = findViewById(R.id.textView2);
        egImage = findViewById(R.id.imageView2);
        userImage = findViewById(R.id.imageView5);
        userImage2 = findViewById(R.id.imageView4);
        userImage3 = findViewById(R.id.imageView6);
        userImage4 = findViewById(R.id.imageView7);
        userImage5 = findViewById(R.id.imageView8);
        userImage6 = findViewById(R.id.imageView9);
        userImage7 = findViewById(R.id.imageView10);
        userImage8 = findViewById(R.id.imageView11);
        userImage9 = findViewById(R.id.imageView12);
        userImage10 = findViewById(R.id.imageView13);
        userImage11= findViewById(R.id.imageView14);
        userImage12 = findViewById(R.id.imageView15);

        //Base64 file = Base64.Convert(userImage12.getDrawable());
        //file uploaded to API
        userImage.onVisibilityAggregated(true);
        userImage.bringToFront();
        userImage2.onVisibilityAggregated(false);
        userImage3.onVisibilityAggregated(false);
        userImage4.onVisibilityAggregated(false);
        userImage5.onVisibilityAggregated(false);
        userImage6.onVisibilityAggregated(false);
        userImage7.onVisibilityAggregated(false);
        userImage8.onVisibilityAggregated(false);
        userImage9.onVisibilityAggregated(false);
        userImage10.onVisibilityAggregated(false);
        userImage11.onVisibilityAggregated(false);
        userImage12.onVisibilityAggregated(false);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

            }
        });

        mouthShapeNumber.setText("Mouth Shape " + i + "/12");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 12) {
                    Toast.makeText(getApplicationContext(),"You have reached the end of the uploading process", Toast.LENGTH_LONG).show();
                }
                else {
                    i = i + 1;
                    mouthShapeNumber.setText("Mouth Shape " + i + "/12");
                    switch(i)
                    {
                        case 2:
                            egImage.setImageResource(R.drawable.mouth_formants_2);
                            userImage.onVisibilityAggregated(false);
                            userImage2.onVisibilityAggregated(true);
                            userImage2.bringToFront();
                            break;
                        case 3:
                            egImage.setImageResource(R.drawable.mouth_formants_3);
                            userImage2.onVisibilityAggregated(false);
                            userImage3.onVisibilityAggregated(true);
                            userImage3.bringToFront();
                            break;
                        case 4:
                            egImage.setImageResource(R.drawable.mouth_formants_4);
                            userImage3.onVisibilityAggregated(false);
                            userImage4.onVisibilityAggregated(true);
                            userImage4.bringToFront();
                            break;
                        case 5:
                            egImage.setImageResource(R.drawable.mouth_formants_5);
                            userImage4.onVisibilityAggregated(false);
                            userImage5.onVisibilityAggregated(true);
                            userImage5.bringToFront();
                            break;
                        case 6:
                            egImage.setImageResource(R.drawable.mouth_formants_6);
                            userImage5.onVisibilityAggregated(false);
                            userImage6.onVisibilityAggregated(true);
                            userImage6.bringToFront();
                            break;
                        case 7:
                            egImage.setImageResource(R.drawable.mouth_formants_7);
                            userImage6.onVisibilityAggregated(false);
                            userImage7.onVisibilityAggregated(true);
                            userImage7.bringToFront();
                            break;
                        case 8:
                            egImage.setImageResource(R.drawable.mouth_formants_8);
                            userImage7.onVisibilityAggregated(false);
                            userImage8.onVisibilityAggregated(true);
                            userImage8.bringToFront();
                            break;
                        case 9:
                            egImage.setImageResource(R.drawable.mouth_formants_9);
                            userImage8.onVisibilityAggregated(false);
                            userImage9.onVisibilityAggregated(true);
                            userImage9.bringToFront();
                            break;
                        case 10:
                            egImage.setImageResource(R.drawable.mouth_formants_10);
                            userImage9.onVisibilityAggregated(false);
                            userImage10.onVisibilityAggregated(true);
                            userImage10.bringToFront();
                            break;
                        case 11:
                            egImage.setImageResource(R.drawable.mouth_formants_11);
                            userImage10.onVisibilityAggregated(false);
                            userImage11.onVisibilityAggregated(true);
                            userImage11.bringToFront();
                            break;
                        case 12:
                            egImage.setImageResource(R.drawable.mouth_formants_12);
                            userImage11.onVisibilityAggregated(false);
                            userImage12.onVisibilityAggregated(true);
                            userImage12.bringToFront();
                            break;
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mouthCreation_ImageUpload.this, mouthCreation.class);
                startActivity(intent);
            }
        });
    }


}
