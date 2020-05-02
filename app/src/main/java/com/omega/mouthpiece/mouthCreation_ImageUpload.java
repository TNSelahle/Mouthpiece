package com.omega.mouthpiece ;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class mouthCreation_ImageUpload extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;

    private Button btnUpload;
    private Button btnNext;
    //private Button btnConfirm;
    private Button btnCancel;

    private TextView mouthShapeNumber;
    private ImageView egImage;

    public ImageView userImage;
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

    //
    imageConfirmation var1 = new imageConfirmation();
    Bundle bundle = new Bundle();
    ArrayList<Bitmap> arrIMG = new ArrayList<>();
    public Uri imageUri, imageUriL, imageUriO, imageUriCDGKNSTXYZ, imageUriFV, imageUriQW;
    Uri imageUriBMP, imageUriU, imageUriEe, imageUriR, imageUriTh, imageUriChJSh;

    Intent intentImage;
    Bitmap bitmapImage;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap bSelectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                switch (i) {
                    case 1:
                        userImage.setImageURI(selectedImage);
                        userImage.setImageBitmap(bSelectedImage);
                        userImage.setDrawingCacheEnabled(true);

                        //get uri for image
                        imageUri = data.getData();
                        Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        int nh = (int) (bitmapImage.getHeight() * (1024.0 / bitmapImage.getWidth()));
                        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 1024, nh, true);
                        userImage.setImageBitmap(scaled);
                        //var1.mouth1_AEI(imageUri);
                        //userImage.crea

                        break;
                    case 2:
                        userImage2.setImageURI(selectedImage);
                        imageUriL = data.getData();
                        Bitmap bitmapImage2 = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        int nh2 = (int) (bitmapImage2.getHeight() * (1024.0 / bitmapImage2.getWidth()));
                        Bitmap scaled2 = Bitmap.createScaledBitmap(bitmapImage2, 1024, nh2, true);
                        userImage2.setImageBitmap(scaled2);
                        break;
                    case 3:
                        userImage3.setImageURI(selectedImage);
                        imageUriO = data.getData();
                        break;
                    case 4:
                        userImage4.setImageURI(selectedImage);
                        imageUriCDGKNSTXYZ = data.getData();
                        break;
                    case 5:
                        userImage5.setImageURI(selectedImage);
                        imageUriFV = data.getData();
                        break;
                    case 6:
                        userImage6.setImageURI(selectedImage);
                        imageUriQW = data.getData();
                        break;
                    case 7:
                        userImage7.setImageURI(selectedImage);
                        imageUriBMP = data.getData();
                        break;
                    case 8:
                        userImage8.setImageURI(selectedImage);
                        imageUriU = data.getData();
                        break;
                    case 9:
                        userImage9.setImageURI(selectedImage);
                        imageUriEe = data.getData();
                        break;
                    case 10:
                        userImage10.setImageURI(selectedImage);
                        imageUriR = data.getData();
                        break;
                    case 11:
                        userImage11.setImageURI(selectedImage);
                        imageUriTh = data.getData();
                        break;
                    case 12:
                        userImage12.setImageURI(selectedImage);
                        imageUriChJSh = data.getData();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouth_creation_image_upload);

        InputStream open = null;

        btnUpload = findViewById(R.id.btn_choose_image);
        btnNext = findViewById(R.id.btn_nxt);
        btnNext.bringToFront();
        //btnConfirm = findViewById(R.id.btn_click_to_confirm);
        //btnConfirm.setVisibility(btnConfirm.INVISIBLE);
        btnCancel = findViewById(R.id.btn_cancel);

        mouthShapeNumber = findViewById(R.id.textView2);

        egImage = findViewById(R.id.imageView2);
        userImage = findViewById(R.id.uImage1);
        userImage2 = findViewById(R.id.uImage2);
        userImage3 = findViewById(R.id.uImage3);
        userImage4 = findViewById(R.id.uImage4);
        userImage5 = findViewById(R.id.uImage5);
        userImage6 = findViewById(R.id.uImage6);
        userImage7 = findViewById(R.id.uImage7);
        userImage8 = findViewById(R.id.uImage8);
        userImage9 = findViewById(R.id.uImage9);
        userImage10 = findViewById(R.id.uImage10);
        userImage11= findViewById(R.id.uImage11);
        userImage12 = findViewById(R.id.uImage12);

        userImage.setVisibility(userImage.VISIBLE);
        userImage.bringToFront();
        userImage2.setVisibility(userImage2.INVISIBLE);
        userImage3.setVisibility(userImage3.INVISIBLE);
        userImage4.setVisibility(userImage4.INVISIBLE);
        userImage5.setVisibility(userImage5.INVISIBLE);
        userImage6.setVisibility(userImage6.INVISIBLE);
        userImage7.setVisibility(userImage7.INVISIBLE);
        userImage8.setVisibility(userImage8.INVISIBLE);
        userImage9.setVisibility(userImage9.INVISIBLE);
        userImage10.setVisibility(userImage10.INVISIBLE);
        userImage11.setVisibility(userImage11.INVISIBLE);
        userImage12.setVisibility(userImage12.INVISIBLE);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), RESULT_LOAD_IMAGE);

            }
        });

        mouthShapeNumber.setText("Mouth Shape " + i + "/12");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(i == 12) {
                    intentImage = new Intent( mouthCreation_ImageUpload.this, imageConfirmation.class);
                    intentImage.putExtra("imageAEI", imageUri.toString());
                    intentImage.putExtra("imageL", imageUriL.toString());
                    intentImage.putExtra("imageO", imageUriO.toString());
                    intentImage.putExtra("imageCDGKNSTXYZ", imageUriCDGKNSTXYZ.toString());
                    intentImage.putExtra("imageFV", imageUriFV.toString());
                    intentImage.putExtra("imageQW", imageUriQW.toString());
                    intentImage.putExtra("imageBMP", imageUriBMP.toString());
                    intentImage.putExtra("imageU", imageUriU.toString());
                    intentImage.putExtra("imageEe", imageUriEe.toString());
                    intentImage.putExtra("imageR", imageUriR.toString());
                    intentImage.putExtra("imageTh", imageUriTh.toString());
                    intentImage.putExtra("imageChJSh", imageUriChJSh.toString());
                    startActivity(intentImage);
                    //btnNext.setVisibility(btnNext.INVISIBLE);
                    //btnConfirm.setVisibility(btnConfirm.VISIBLE);
                }
                else {
                    i = i + 1;
                    mouthShapeNumber.setText("Mouth Shape " + i + "/12");
                    switch(i)
                    {
                        case 1:
                            //Intent iAEI = new Intent(view.getContext(), imageConfirmation.class);
                            //iAEI.putExtra
                            //Bitmap bitmapImages = BitmapFactory.decodeResource(getResources(), R.);
                            //drawVarImage = userImage.getDrawable();
                            //intentImage.putExtra("imageUri", imageUri.toString());
                            //var1.mouth1_AEI();
                            break;
                        case 2:
                            //var1.mouth1_AEI(userImage.);
                            egImage.setImageResource(R.drawable.mouth_formants_2);
                            userImage.setVisibility(userImage.INVISIBLE);
                            userImage2.setVisibility(userImage2.VISIBLE);
                            userImage2.bringToFront();
                            //intentImage.putExtra("imageUri", imageUri.toString());
                            break;
                        case 3:
                            egImage.setImageResource(R.drawable.mouth_formants_3);
                            userImage2.setVisibility(userImage2.INVISIBLE);
                            userImage3.setVisibility(userImage3.VISIBLE);
                            userImage3.bringToFront();
                            break;
                        case 4:
                            egImage.setImageResource(R.drawable.mouth_formants_4);
                            userImage3.setVisibility(userImage3.INVISIBLE);
                            userImage4.setVisibility(userImage4.VISIBLE);
                            userImage4.bringToFront();
                            break;
                        case 5:
                            egImage.setImageResource(R.drawable.mouth_formants_5);
                            userImage4.setVisibility(userImage4.INVISIBLE);
                            userImage5.setVisibility(userImage5.VISIBLE);
                            userImage5.bringToFront();
                            break;
                        case 6:
                            egImage.setImageResource(R.drawable.mouth_formants_6);
                            userImage5.setVisibility(userImage5.INVISIBLE);
                            userImage6.setVisibility(userImage6.VISIBLE);
                            userImage6.bringToFront();
                            break;
                        case 7:
                            egImage.setImageResource(R.drawable.mouth_formants_7);
                            userImage6.setVisibility(userImage6.INVISIBLE);
                            userImage7.setVisibility(userImage7.VISIBLE);
                            userImage7.bringToFront();
                            break;
                        case 8:
                            egImage.setImageResource(R.drawable.mouth_formants_8);
                            userImage7.setVisibility(userImage7.INVISIBLE);
                            userImage8.setVisibility(userImage8.VISIBLE);
                            userImage8.bringToFront();
                            break;
                        case 9:
                            egImage.setImageResource(R.drawable.mouth_formants_9);
                            userImage8.setVisibility(userImage8.INVISIBLE);
                            userImage9.setVisibility(userImage9.VISIBLE);
                            userImage9.bringToFront();
                            break;
                        case 10:
                            egImage.setImageResource(R.drawable.mouth_formants_10);
                            userImage9.setVisibility(userImage9.INVISIBLE);
                            userImage10.setVisibility(userImage10.VISIBLE);
                            userImage10.bringToFront();
                            break;
                        case 11:
                            egImage.setImageResource(R.drawable.mouth_formants_11);
                            userImage10.setVisibility(userImage10.INVISIBLE);
                            userImage11.setVisibility(userImage11.VISIBLE);
                            userImage11.bringToFront();
                            break;
                        case 12:
                            egImage.setImageResource(R.drawable.mouth_formants_12);
                            userImage11.setVisibility(userImage11.INVISIBLE);
                            userImage12.setVisibility(userImage12.VISIBLE);
                            userImage12.bringToFront();
                            btnNext.setText("Click to Confirm");
                            break;
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mouthCreation_ImageUpload.this, UploadMouthsFrontPage.class);
                startActivity(intent);
            }
        });

    }
}
