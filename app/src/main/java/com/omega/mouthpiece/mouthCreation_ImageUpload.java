package com.omega.mouthpiece ;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class mouthCreation_ImageUpload extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    private Button btnUpload;
    private Button btnNext;
    private Button btnCancel;
    private TextView mouthShapeNumber;
    private ImageView egImage;
    private int i = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouth_creation_image_upload);

        btnUpload = findViewById(R.id.btn_choose_image);
        btnNext = findViewById(R.id.btn_nxt);
        btnCancel = findViewById(R.id.btn_cancel);
        mouthShapeNumber = findViewById(R.id.textView2);
        egImage = findViewById(R.id.imageView2);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivity(galleryIntent, savedInstanceState);

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
                            break;
                        case 3:
                            egImage.setImageResource(R.drawable.mouth_formants_3);
                            break;
                        case 4:
                            egImage.setImageResource(R.drawable.mouth_formants_4);
                            break;
                        case 5:
                            egImage.setImageResource(R.drawable.mouth_formants_5);
                            break;
                        case 6:
                            egImage.setImageResource(R.drawable.mouth_formants_6);
                            break;
                        case 7:
                            egImage.setImageResource(R.drawable.mouth_formants_7);
                            break;
                        case 8:
                            egImage.setImageResource(R.drawable.mouth_formants_8);
                            break;
                        case 9:
                            egImage.setImageResource(R.drawable.mouth_formants_9);
                            break;
                        case 10:
                            egImage.setImageResource(R.drawable.mouth_formants_10);
                            break;
                        case 11:
                            egImage.setImageResource(R.drawable.mouth_formants_11);
                            break;
                        case 12:
                            egImage.setImageResource(R.drawable.mouth_formants_12);
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
